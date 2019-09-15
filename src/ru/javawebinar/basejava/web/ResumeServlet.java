package ru.javawebinar.basejava.web;

import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.SqlStorage;
import ru.javawebinar.basejava.storage.Storage;
import ru.javawebinar.basejava.util.Config;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class ResumeServlet extends javax.servlet.http.HttpServlet {

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
      //  response.setHeader("Content-Type", "text/html; charset=UTF-8");

        String name = request.getParameter("name");
        Writer writer = response.getWriter();
        writer.write(
                "<html>\n" +
                        "<head>\n" +
                        "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
                        "    <link rel=\"stylesheet\" href=\"css/style.css\">\n" +
                        "</head>\n" +
                        "<body>\n");

        if (!name.isEmpty()) {
            Storage storage = Config.get().getStorage();
            String content = storage.get(name).toString();
            writer.write(content);

        } else {
            Storage sqlStorage = Config.get().getStorage();
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
