<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page session="false"%>
<html>
<head>
    <title>Board</title>

    <style type="text/css">
        li {list-style: none; float: left; padding: 6px;}
    </style>
</head>



<%--@elvariable id="board" type=""--%>
<form:form modelAttribute="board" method="POST" action="search">
<h2>LIST</h2>
TITLE : <form:input path="title" /><input type="submit" value="Search" />
<a href="register">New</a>
<table border="1">
    <tr>
        <th align="center" width="60">NO</th>
        <th align="center" width="300">TITLE</th>
        <th align="center" width="100">WRITER</th>
        <th align="center" width="180">REGDATE</th>
    </tr>
    <c:choose>
        <c:when test="${empty list}">
            <tr>
                <td colspan="4">
                    List is empty.
                </td>
            </tr>
        </c:when>
        <c:otherwise>
            <c:forEach items="${list}" var="board">
                <tr>
                    <td align="center">${board.boardNo}</td>
                    <td align="left"><a href="/board/read?boardNo=${board.boardNo}">${board.title}</a></td>
                    <td align="right">${board.writer}</td>
                    <td align="center"><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${board.regDate}" /></td>
                </tr>
            </c:forEach>
        </c:otherwise>
    </c:choose>
</table>
</form:form>


<div>
    <ul>
        <c:if test="${pageMaker.prev}">
            <li><a href="list${pageMaker.makeQuery(pageMaker.startPage - 1)}">이전</a></li>
        </c:if>

        <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
            <li><a href="list${pageMaker.makeQuery(idx)}">${idx}</a></li>
        </c:forEach>

        <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
            <li><a href="list${pageMaker.makeQuery(pageMaker.endPage + 1)}">다음</a></li>
        </c:if>
    </ul>
</div>


</body>
</html>
