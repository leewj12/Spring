<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset = "UTF-8">
        <title>글목록</title>
    </head>
    <body>
    <div class ="wrap">
        <h1>Spring Board List</h1>
        <c:if test="${paging.findKeyword != ''}">
              <h3 style="text-align:center;">검색어 : <c:out value="${paging.findKeyword}" /></h3>
        </c:if>
        <br>
            <a href = "/board/form">글쓰기</a>|<a href = "/board/list">글목록</a>
        <br>
        <!-- 검색 form ---------------------------------------->
        <div id = "divFind">
            <form name = "findF" id = "findF" action = "list"
            onsubmit = "return find_check()">
                <select name = "findType" id = "findType">
                    <option value= "0">::검색 유형::</option>
                    <option value= "1" <c:if test = "${param.findType==1}">selected</c:if>>제 목</option>
                    <option value= "2" <c:if test = "${param.findType==2}">selected</c:if>>작성자</option>
                    <option value= "3" <c:if test = "${param.findType==3}">selected</c:if>>글내용</option>
                </select>
                <input type = "text" name = "findKeyword" id = "findKeyword"
                placeholder = "검색어를 입력하세요" required>
                <button class = "btnSearch">검 색</button>
            </form>
        </div>
        <!---------------------------------------------------->
        <br>
        <ul class = "boardList">
            <li>글번호</li>
            <li>글제목</li>
            <li>작성자</li>
            <li>작성일</li>
            <li>조회수</li>
            <!------------------------->
            <c:if test = "${boardList == null || empty boardList}">
                <li style = "width: 100%">
                    데이터가 없습니다
                </li>
            </c:if>
            <c:if test = "${boardList != null && not empty boardList}">
                <c:forEach var = "board" items = "${boardList}">
                    <li>
                        <c:out value = "${board.num}"/>
                    </li>
                    <li class = "title">
                    <c:if test = "${board.newImg < 1}">
                    <!-- 1일 이내 쓴 글이라면 New 문자열 붙여주기 -->
                        <span class = "new">New</span>
                    </c:if>
                        <a href = "/board/view?num=<c:out value='${board.num}'/>">
                        <c:out value = "${board.title}"/></a>
                        <c:if test = "${board.fileName != null}">
                            <img src = "/images/attach.png" style = "width:16px"/>
                        </c:if>
                    </li>
                    <li>
                        <c:out value = "${board.userId}"/>
                    </li>
                    <li>
                        <fmt:formatDate value = "${board.wdate}" pattern = "yy-MM-dd" />
                        <%-- yy: 년도, MM: 월, dd: 일, HH: 시(24), mm: 분, ss: 초 --%>
                    </li>
                    <li>
                        <c:out value = "${board.readnum}"/>
                    </li>
                </c:forEach>
            </c:if>
            <!------------------------->
        </ul>
    <div class = "clear"></div>
    <div class = "divTotal">
        <span>총 게시글 수: ${totalCount}개</span> &nbsp;&nbsp;&nbsp;&nbsp;
        <span> ${paging.pageNum} page / ${paging.pageCount} pages</span>
    </div>
    <!--페이지 네비게이션(페이지 블럭처리 안할 경우)------------------------>
    <%--
    <c:forEach var = "i" begin = "1" end = "${paging.pageCount}">
        <a href = "list?pageNum=${i}">[<c:out value = "${i}"/>]</a>
    </c:forEach>
    --%>
    <!-------------------------------------->
    <!--페이지 네비(페이징 블럭처리를 할 경우)------------------>
    <c:set var="qstr" value="&findType=${paging.findType}&findKeyword=${paging.findKeyword}"/>
    <%-- <h1>${qstr}</h1> --%>
    <c:if test="${paging.prevBlock>0}">
       <a href="list?pageNum=${paging.prevBlock}${qstr}">◀</a>
    </c:if>
    <c:forEach var="i" begin="${paging.prevBlock+1}" end="${paging.nextBlock-1}">
        <c:if test = "${i <= paging.pageCount}">
            <a href="list?pageNum=${i}${qstr}" <c:if test = "${i==paging.pageNum}">class="active"</c:if>>[<c:out value="${i}"/>]</a>
        </c:if>
    </c:forEach>
    <c:if test="${paging.nextBlock<=paging.pageCount}">
       <a href="list?pageNum=${paging.nextBlock}${qstr}">▶</a>
    </c:if>
    </div>

    <script>
        const find_check = () => {
            if (!findF.findType.value || findF.findType.value === "0") {
                alert('검색 유형을 선택하세요');
                findF.findType.focus();
                return false;
            }
            if (!findF.findKeyword.value || !findF.findKeyword.value.trim()) {
                alert('검색어를 입력하세요');
                findF.findKeyword.focus();
                return false;
            }
            return true;
        };
    </script>

    </body>
</html>