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

            <c:when test="${key=='OBJECTIVE'||key=='PERSONAL'}">
            <a name="key.name">${key.title}</a>
                <%=((SimpleTextSection)section).getPosition()%><br>
            </c:when>

            <c:when test="${key=='QUALIFICATIONS'|| key=='ACHIEVEMENT'}">
            <a name="key.name">${key.title}</a><br>
                <c:set var="list" value="<%=((MarkedListSection)section).getPerformanceList()%>"/>
            <c:forEach var="value" items="${list}">
                <c:out value="${value}"/><br>
            </c:forEach>
            </c:when>

            <c:when test="${key=='EXPERIENCE'|| key=='EDUCATION'}">
            <a name="key.name">${key.title}</a><br>
                <c:set var="organisationList" value="<%=((OrganizationSection)section).getWorkStudyStringDates()%>"/>

            <c:forEach var="organisation" items="${organisationList}">
                <c:out value="${organisation.homePage.name}"/><br>
                <c:out value="${organisation.homePage.url}"/><br>

                <c:forEach var="position" items="${organisation.positionList}">
                   <jsp:useBean id="position" type="ru.javawebinar.basejava.model.Organization.Position"/>
                      <c:out value="<%=position.getTitle()%>"/><br>
                      <c:out value="<%=position.getDateOfEntry()%>"/><br>
                      <c:out value="<%=position.getDateOfExit()%>"/><br>
                      <c:out value="<%=position.getDescription()%>"/><br>

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