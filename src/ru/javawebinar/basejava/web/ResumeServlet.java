package ru.javawebinar.basejava.web;

import ru.javawebinar.basejava.model.*;
import ru.javawebinar.basejava.storage.Storage;
import ru.javawebinar.basejava.Config;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.Position;
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
            if (value == null && value.trim().length() == 0) {
                resume.addContact(type, value);
            } else {
                resume.getContactInfoMap().remove(type);
            }
        }

        for (SectionType type : SectionType.values()) {
            String value = request.getParameter(type.name());
            String[] values = request.getParameterValues(type.name());
            if (value == null || value.trim().length() == 0 && values.length < 2) {
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
                        for (int i = 0; i < values.length; i++) {
                            List<Organization.Position> positions = new ArrayList<>();
                            String[] url = request.getParameterValues(type.name() + "url");
                            String[] name = request.getParameterValues(type.name() + "name");
                            Link link = new Link (url[i], name[i]);
                                String typename = type.name() + i;
                                String[] title = request.getParameterValues(typename + "title");
                                String[] dateOfEntry = request.getParameterValues(typename + "dateOfEntry");
                                String[] dateOfExit = request.getParameterValues(typename + "dateOfExit");
                                String[] description = request.getParameterValues(typename + "description");

                                for (int j = 0; j < title.length; j++) {
                                    positions.add(new Organization.Position(title[j], LocalDate.parse(dateOfEntry[j],
                                            DateTimeFormatter.ofPattern("MM/yyyy")),
                                            LocalDate.parse(dateOfExit[j], DateTimeFormatter.ofPattern("MM/yyyy")),
                                            description[j]));
                                }
                            organizations.add(new Organization(link,positions));
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
                for (SectionType type : new SectionType[]{SectionType.EXPERIENCE, SectionType.EDUCATION}) {
                    OrganizationSection organizationSection = (OrganizationSection) resume.getSection(type);
                    List<Organization> organizations = new ArrayList<>();
                    organizations.add(new Organization("", "", Organization.Position.POSITION));
                    if (organizationSection != null) {
                        for (Organization organization : organizationSection.getWorkStudyStringDates()) {
                            List<Organization.Position> positions = new ArrayList<>();
                            positions.add(Organization.Position.POSITION);
                            positions.addAll(organization.getPositionList());
                            organizations.add(new Organization(organization.getHomePage(), positions));
                        }
                    }
                    resume.addSection(type, new OrganizationSection(organizations));
                }
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
