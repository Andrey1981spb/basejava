package ru.javawebinar.basejava.web;

import ru.javawebinar.basejava.model.*;
import ru.javawebinar.basejava.storage.Storage;
import ru.javawebinar.basejava.Config;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ResumeServlet extends HttpServlet {

    private Storage storage; // = Config.get().getStorage();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        storage = Config.get().getStorage();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String uuid = request.getParameter("uuid");
        String fullName = request.getParameter("fullName");
        Resume resume = storage.get(uuid);
        resume.setFullName(fullName);
        for (ContactType type : ContactType.values()) {
            String value = request.getParameter(type.name());
            if (value != null && value.trim().length() != 0) {
                resume.addContact(type, value);
            } else {
                resume.getContactInfoMap().remove(type);
            }
        }

        for (SectionType type : SectionType.values()) {
            String value = request.getParameter(type.name());
            String[] values = request.getParameterValues(type.name());
            if (value != null || value.trim().length() != 0) {
                resume.getSections().remove(type);
            } else {
                switch (type) {
                    case OBJECTIVE:
                    case PERSONAL:
                        resume.addSection(type, new SimpleTextSection(value));
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        resume.addSection(type, new MarkedListSection(value));
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        List<Organization> organizations = new ArrayList<>();
                        for (int i = 0; i < value.length(); i++) {
                            List<Organization.Position> positions = new ArrayList<>();
                            for (Organization organization : new OrganizationSection().getWorkStudyStringDates()) {
                                organization.getHomePage().setUrl(value);
                                organization.getHomePage().setName(value);
                                positions.add(new Organization.Position(value, LocalDate.parse(value, DateTimeFormatter.ofPattern("MM/yyyy")),
                                        LocalDate.parse(value, DateTimeFormatter.ofPattern("MM/yyyy")), value));
                            }
                            organizations.add(new Organization(new Link (value, value),positions));
                        }
                        resume.addSection(type,new OrganizationSection(organizations));
                }
            }
        }

        storage.update(resume);
        response.sendRedirect("resume");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String uuid = request.getParameter("uuid");
        String action = request.getParameter("action");
        if (action == null) {
            request.setAttribute("resumes", storage.getAllSorted());
            request.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(request, response);
            return;
        }
        Resume resume;
        switch (action) {
            case "delete":
                storage.delete(uuid);
                response.sendRedirect("resume");
                return;
            case "view":
            case "edit":
                resume = storage.get(uuid);
                break;
            default:
                throw new IllegalArgumentException("Action " + action + " is illegal");
        }
        request.setAttribute("resume", resume);
        request.getRequestDispatcher(
                ("view".equals(action) ? "/WEB-INF/jsp/view.jsp" : "/WEB-INF/jsp/edit.jsp")
        ).forward(request, response);
    }
}
