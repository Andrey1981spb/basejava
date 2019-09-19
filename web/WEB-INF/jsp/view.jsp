<%@ page import="javax.swing.*" %>
<%@ page import="ru.javawebinar.basejava.model.*" %>
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
    <h2>${resume.fullName}&nbsp;<a href="resume?uuid=${resume.uuid}&action=edit"><img src="img/pencil.png"></a></h2>
    <p>
        <c:forEach var="contactEntry" items="${resume.contactInfoMap}">
            <jsp:useBean id="contactEntry"
                         type="java.util.Map.Entry<ru.javawebinar.basejava.model.ContactType, java.lang.String>"/>
                <%=contactEntry.getKey().toHtml(contactEntry.getValue())%><br/>
        </c:forEach>
    <p>
    <p>
        <c:forEach var="sectionEntry" items="${resume.resumeSections}">
            <jsp:useBean id="sectionEntry"
                         type="java.util.Map.Entry<ru.javawebinar.basejava.model.SectionType,
                         ru.javawebinar.basejava.model.AbstractSection>"/>
                <c:set var="key" value="${sectionEntry.key}"/>
                <c:set var="section" value="${sectionEntry.value}"/>
                <jsp:useBean id="section" type="ru.javawebinar.basejava.model.AbstractSection"/>
        <c:choose>

            <c:when test="${key==OBJECTIVE}">
                <%=((SimpleTextSection)sectionEntry).getPosition()%>
            </c:when>

            <c:when test="${key==PERSONAL}">
                    <%=((SimpleTextSection)sectionEntry).getPosition()%>
            </c:when>

            <c:when test="${key==ACHIEVEMENT}">
                <c:set var="list" value="<%=((MarkedListSection)sectionEntry).getPerformanceList()%>"/>
            <c:forEach var="value" items="list">
                <c:out value="${value}"/>
            </c:forEach>
            </c:when>

            <c:when test="${key==QUALIFICATIONS}">
                <c:set var="list" value="<%=((MarkedListSection)sectionEntry).getPerformanceList()%>"/>
            <c:forEach var="value" items="list">
                <c:out value="${value}"/>
            </c:forEach>
            </c:when>

            <c:when test="${key==EXPERIENCE}">
                <c:set var="organisationList" value="<%=((OrganizationSection)sectionEntry).getWorkStudyStringDates()%>"/>
                <jsp:useBean id="organisation" type="ru.javawebinar.basejava.model.Organization"/>
            <c:forEach var="organisation" items="organisationList">
                <c:out value="<%=organisation.getHomePage().getName()%>"/>
                <c:out value="<%=organisation.getHomePage().getUrl()%>"/>

                <c:forEach var="position" items="<%=organisation.getPositionList()%>">
                   <jsp:useBean id="position" type="ru.javawebinar.basejava.model.Organization.Position"/>
                      <c:out value="<%=position.getTitle()%>"/>
                      <c:out value="<%=position.getDateOfEntry()%>"/>
                      <c:out value="<%=position.getDateOfExit()%>"/>
                      <c:out value="<%=position.getDescription()%>"/>

               </c:forEach>
            </c:forEach>
            </c:when>

        </c:choose>
        <br/>
        </c:forEach>
    <p>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>