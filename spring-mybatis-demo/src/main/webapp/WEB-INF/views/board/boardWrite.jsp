<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <mata charset = "utf-8">
        <title> 글쓰기 </title>
    <!-- CKEditor5-------------------------------------------------->

    <!-- CKEditor5-------------------------------------------------->
        <link rel="stylesheet" href="https://cdn.ckeditor.com/ckeditor5/43.3.0/ckeditor5.css" />
            <script type="importmap">
                {
                    "imports": {
                        "ckeditor5": "https://cdn.ckeditor.com/ckeditor5/43.3.0/ckeditor5.js",
                        "ckeditor5/": "https://cdn.ckeditor.com/ckeditor5/43.3.0/"
                    }
                }
            </script>

            <script type="module">
                import {
                    ClassicEditor,
                    Essentials,
                    Bold,
                    Italic,
                    Font,
                    Paragraph
                } from 'ckeditor5';

                ClassicEditor
                    .create( document.querySelector( '#content' ), {
                        plugins: [ Essentials, Bold, Italic, Font, Paragraph ],
                        toolbar: [
                            'undo', 'redo', '|', 'bold', 'italic', '|',
                            'fontSize', 'fontFamily', 'fontColor', 'fontBackgroundColor'
                        ]
                    } )
                    .then( /* ... */ )
                    .catch( /* ... */ );
            </script>

    </head>
    <body>
    <div class = "boardWrap">
        <h1> Spring Board </h1>
        <!-- board/form
            파일 업로드시 주의사항
            method="post"로 지정
            enctype = "multipart/form-data" ==>첨부 파일데이터가 서버에 전송됨

            post방식일 때 인코딩 타입
                -application/www-form-urlencoded(디폴트): 파일데이터를 서버에
                 전송하지 못함. 파일명(파라미터 값)만 전송함
                -multipart/form-data: 파일 데이터도 함께 전송함
                    여러 파트로 폼 데이터르 구분(구분선)해서 보낸다.
                    첨부된 파일 데이터 시작과 끝을 구분선으로 구분해준다.
        -->

        <form name = "boardF" action = "../user/write" method = "post"
            enctype = "multipart/form-data">
            <!-- mode값 : 글쓰기(write) 글수정(edit) -->
            <input type="hidden" name="mode" value = "write">
            <ul class= "boardUl">
                <li> 제목 </li>
                <li>
                    <input type="text" name = "title" id= "title"
                    placeholder = "Title">
                </li>

                <li> 글쓴이 </li>
                <li>
                    <input type="text" name = "userId" id= "userId"
                    placeholder = "User ID" readonly value=${loginUser.userId} >
                </li>

                <li> 글 내용 </li>
                <li>
                    <textarea rows="10" cols = "50"
                    name="content" id="content" placeholder="Content"></textarea>
                </li>

                <li> 첨부파일 </li>
                <li>
                    <input type="file" name = "mfileName">
                    <!--
                        BoardDTO의 프로퍼티명(fileName : String)과 다르게 이름을 주어야한다.
                        (mfileName : MultipartFile) 업로드 처리를 위해서
                    -->
                </li>

                <li> 비밀번호 </li>
                <li>
                    <input type="password" name = "passwd" id="passwd"
                    placeholder="Password">
                </li>
            </ul>
            <div class = "clear"></div>     <!-- float 속성 해제를 위해 .clear -->
            <div class = "divBtn">
                <button type = "submit"> 글쓰기 </button>
                <button type = "reset"> 다시쓰기 </button>
            </div>
        </form>
    </div>
    </body>
</html>