package ru.javawebinar.basejava.web;

import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.SqlStorage;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class ResumeServlet extends javax.servlet.http.HttpServlet {
    SqlStorage sqlStorage = new SqlStorage();

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String content = null;

        String name = request.getParameter("name");
        Writer writer = response.getWriter();
        writer.write(
                "<html>\n" +
                        "<head>\n" +
                        "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
                        "    <link rel=\"stylesheet\" href=\"css/style.css\">\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "<section>\n" +
                        "<table border=\"1\" cellpadding=\"10\" cellspacing=\"0\">\n");

        if (name != null) {
            writer.write(sqlStorage.get(name).toString());
        } else {
            List<Resume> list = sqlStorage.getAllSorted();
            for (Resume resume : list) {
                writer.write("<tr > <td >" + resume.getContactInfoMap().toString() + "</td > </tr >");
                writer.write("<tr > <td >" + resume.getResumeSections().toString() + "</td > </tr >");
            }
        }



        //    response.getWriter().write(name == null ? "Hello Resumes!" : "Hello " + name + '!');
    }
}
