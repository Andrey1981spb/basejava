package ru.javawebinar.basejava.web;

import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.Storage;
import ru.javawebinar.basejava.util.Config;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class ResumeServlet extends javax.servlet.http.HttpServlet {

    private Storage sqlStorage;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);
        sqlStorage = Config.get().getStorage();
    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        //  response.setHeader("Content-Type", "text/html; charset=UTF-8");

        Writer writer = response.getWriter();
        writer.write(
                "<html>\n" +
                        "<head>\n" +
                        "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
                        "    <link rel=\"stylesheet\" href=\"css/style.css\">\n" +
                        "</head>\n" +
                        "<body>\n");
        String name = request.getParameter("name");

        if (name != null) {
            String content = sqlStorage.get(name).toString();
            writer.write(content);
        } else {
            writer.write("<table border=\"1\" cellpadding=\"10\" cellspacing=\"0\">\n");
            List<Resume> list = sqlStorage.getAllSorted();
            for (Resume resume : list) {
                writer.write("<tr>" +
                        "<td >" + resume.getFullName() + "</td >" +
                        "<td >" + resume.getContactInfoMap().toString() + "</td >" +
                        "<td >" + resume.getResumeSections().toString() + "</td >" +
                        "</tr>"
                );
            }
            writer.write("</table>\n");
        }

        writer.write(
                "</body>\n" +
                        "</html>\n");
    }
}
