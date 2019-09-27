<%@ page import="ru.javawebinar.basejava.model.ContactType" %>
<%@ page import="ru.javawebinar.basejava.model.SectionType" %>
<%@ page import="ru.javawebinar.basejava.model.MarkedListSection" %>
<%@ page import="ru.javawebinar.basejava.model.OrganizationSection" %>
<%@ page import="ru.javawebinar.basejava.util.DateUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <jsp:useBean id="resume" type="ru.javawebinar.basejava.model.Resume" scope="request"/>
    <title>Резюме ${resume.fullName}</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <form method="post" action="resume" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="uuid" value="${resume.uuid}">
        <dl>
            <dt>Имя:</dt>
            <dd><input type="text" name="fullName" size=50 value="${resume.fullName}"></dd>
        </dl>
        <h3>Контакты:</h3>
        <c:forEach var="type" items="<%=ContactType.values()%>">
            <dl>
                <dt>${type.title}</dt>
                <dd><input type="text" name="${type.name()}" size=30 value="${resume.getContact(type)}"></dd>
            </dl>
        </c:forEach>
        <h3>Секции:</h3>
        <c:forEach var="type" items="<%=SectionType.values()%>">
            <c:set var="section" value="${resume.getSection(type)}"/>
            <jsp:useBean id="section" type="ru.javawebinar.basejava.model.AbstractSection"/>
            <c:choose>
                <c:when test="${type=='OBJECTIVE'||type=='PERSONAL'}">
                    <dt>${type.title}</dt>
                        <textarea name='${type}' cols=35 rows=5><%=section%></textarea>
                </c:when>

                <c:when test="${type=='ACHIEVEMENT' || type=='QUALIFICATIONS'}">
                    <dt>${type.title}</dt>
                        <textarea name='${type}' cols=115
                                  rows=9><%=String.join("\n", ((MarkedListSection) section).getPerformanceList())%></textarea>
                </c:when>

                <c:when test="${type=='EXPERIENCE'|| type=='EDUCATION'}">
                    <dt>${type.title}</dt><br>
                    <c:forEach var="organisation" items="<%=((OrganizationSection) section).getWorkStudyStringDates()%>"
                               varStatus="count">
                        <input type="text" name='${type}' size=100 value="${organisation.homePage.name}"><br>
                        <input type="text" name='${type}url' size=100 value="${organisation.homePage.url}"><br>

                        <c:forEach var="position" items="${organisation.positionList}">
                            <jsp:useBean id="position" type="ru.javawebinar.basejava.model.Organization.Position"/>
                            'Название организации'<br>
                            <textarea name='${type}${count.index}title' cols=40 rows=1>${position.title}</textarea><br>
                            Дата поступления<br>
                            <input type="text" name='${type}${count.index}dateOfEntry' size=10
                                   value="<%=DateUtil.format(position.getDateOfEntry())%>"><br>
                            Дата окончания<br>
                            <input type="text" name='${type}${count.index}dateOfExist' size=10
                                   value="<%=DateUtil.format(position.getDateOfExit())%>"><br><br>
                            'Описание'<br>
                            <textarea name='${type}${count.index}description' cols=40 rows=8>${position.description}</textarea><br><br>
                        </c:forEach>

                    </c:forEach>

                </c:when>

            </c:choose>
        </c:forEach>




        <hr>
        <button type="submit">Сохранить</button>
        <button onclick="window.history.back()">Отменить</button>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>