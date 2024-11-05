<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>글수정</title>
    <link rel="stylesheet" href="/ckeditor/ckeditor.css"/>
    <script src="https://cdn.ckeditor.com/ckeditor5/39.0.1/super-build/ckeditor.js"></script>
    <script src="/ckeditor/ckeditor.js"></script>

</head>
<body>
<div class="boardWrap">
    <h1>Spring Board 글수정</h1>
    <!-- 파일 업로드시 주의사항
        method="post"
        enctype="multipart/form-data" ===>첨부 파일데이터가 서버에 전송됨
     -->
    <form name="boardF" action="../user/write"
     method="post" enctype="multipart/form-data">
        <!-- mode값: 글쓰기=>write, 글수정=>edit ----- -->
        <input type="hidden" name="mode" value="edit">
        <input type="hidden" name="num" value="<c:out value='${board.num}'/>">
        <!-- ------------------------------------- -->
        <ul class="boardUl">
            <li>제목</li>
            <li><input type="text" name="title"
             value="<c:out value='${board.title}'/>"
             id="title" placeholder="Title"></li>
            <li>글쓴이</li>
            <li><input type="text" name="userId"
             id="userId" readonly placeholder="User ID"
              value="${loginUser.userId}"></li>
            <li>글내용</li>
            <li><textarea rows="10" cols="50" name="content" id="content" placeholder="Content">${board.content}</textarea></li>
            <li>첨부파일</li>
            <li>
            <c:out  value="${board.originFilename}" />
            [<c:out value="${board.fileSize}"/> bytes ]
            <!-- hidden data------------ -->
            <input type="hidden" name="old_fileName" value="${board.fileName}">
            <!-- ----------------------- -->
            <input type="file" name="mfileName">
            </li>
            <li>비밀번호</li>
            <li><input type="password" name="passwd" id="passwd" placeholder="Password"></li>
        </ul>
        <div class="clear"></div>
        <div class="divBtn">
            <button> 글 수정 </button>
            <button type="reset">다시쓰기</button>
        </div>
    </form>
</div>
<script>
    CKEDITOR.ClassicEditor.create(document.querySelector("#content"),option);
</script>
</body>
</html>
