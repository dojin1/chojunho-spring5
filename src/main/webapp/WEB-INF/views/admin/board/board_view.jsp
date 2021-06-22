<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <div class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1 class="m-0">{게시판변수명} 상세보기</h1>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="#">Home</a></li>
              <li class="breadcrumb-item active">{게시판변수명}</li>
            </ol>
          </div><!-- /.col -->
        </div><!-- /.row -->
      </div><!-- /.container-fluid -->
    </div>
    <!-- /.content-header -->

    <!-- Main content -->
    <section class="content">
      <div class="container-fluid">
        <!-- 컨텐츠 내용 -->
        <div class="card card-primary">
          <div class="card-header">
            <h3 class="card-title">보기</h3>
          </div>
          <!-- /.card-header -->
          <!-- form start -->
          <form name="form_view" action="board_write.html" enctype="multipart/form-data">
            <!-- get방식은 검색, post방식은 글쓰기 로그인 등.(get방식하면 입력한 비밀번호가 주소에 뜸) -->
            <!-- enctype="multipart/form-data": 첨부파일을 전송할 때 필수로 들어가야 함. -->
            <div class="card-body">
              <div class="form-group">
                <label for="exampleInputEmail1">제목</label>
                <br>
                {변수출력}
              </div>
              <div class="form-group">
                <label for="exampleInputPassword1">내용</label>
                <br>
                {변수출력}
              </div>
              <div class="form-group">
                <label for="exampleInputPassword1">작성자</label>
                <br>
                {변수출력}
              </div>
              <div class="form-group">
                <label for="exampleInputPassword1">조회수</label>
                <br>
                {변수출력}
              </div>
              <div class="form-group">
                <label for="exampleInputPassword1">작성일</label>
                <br>
                {변수출력}
              </div>
              <div class="form-group">
                <label for="exampleInputFile">첨부파일</label>
                <div class="input-group">
                  <div class="custom-file">
                    {첨부파일 다운로드}
                  </div>
                </div>
              </div>
              
            </div>
            <!-- /.card-body -->
            <div class="card-footer text-right">
              <button type="submit" class="btn btn-primary">수정</button>
              <button type="button" class="btn btn-danger">삭제</button>
              <a href="board_list.html" class="btn btn-default">목록</a>
            </div>
          </form>
        </div>
        <!-- 댓글입력폼 -->
        <div class="col-md-12">
          <div class="card-default">
              <div class="card-header">
                <h3 class="card-title">댓글 쓰기</h3>
              </div>
              <div class="card-body p-0">
                <div class="bs-stepper linear">
                  <div class="bs-stepper-header" role="tablist">
                    <div class="line"></div>
                  </div>
                  <div class="bs-stepper-content">
                    <!-- your steps content here -->
                    <div id="logins-part" class="content active dstepper-block" role="tabpanel" aria-labelledby="logins-part-trigger">
                      <div class="form-group">
                        <label for="replyer">작성자</label>
                        <input type="text" class="form-control" id="replyer" placeholder="작성자를 입력하세요.">
                      </div>
                      <div class="form-group">
                        <label for="reply_text">댓글내용</label>
                        <input type="text" class="form-control" id="reply_text" placeholder="댓글 내용을 입력하세요.">
                      </div>
                    </div>
                    <div id="information-part" class="content" role="tabpanel" aria-labelledby="information-part-trigger">
                      <button type="button" class="btn btn-warning" id="btn_reply_write">댓글등록</button>
                    </div>
                  </div>
                </div>
              </div>
              <!-- /.card-body -->
              <div class="card-footer">
                  아래 댓글리스트 버튼을 클릭하시면 댓글 목록이 출력됩니다.
              </div>
            </div>
        </div>
        <!-- //댓글입력폼 -->
        <!-- 댓글 타임라인 -->
        <div class="col-md-12">
          <!-- The time line -->
          <div class="timeline">
            <!-- timeline time label -->
            <div class="time-label">
              <span class="bg-red" data-toggle="collapse" href="#collapseReply" role="button" id="btn_reply_list">
                댓글리스트[<span>1</span>]
              </span>
            </div>
            <!-- 콜랩스 시작 -->
            <div class="collapse timeline" id="collapseReply">
            <!-- time-label 이후 .after(html) 추가 -->
            <!-- /.timeline-label -->
            <!-- timeline item -->
            <!-- 댓글리스트를 사바스크립트의 빵틀(template)로 만든다. -->
            <!-- 고전append함수를 사용하지 않고, handlebars라는 확장프로그램 임포트(아래) -->
            <!-- 장점은 퍼블리셔가 만든 태그를 그대로 사용가능 -->
            <script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
            <script id="template" type="text/x-handlebars-template">
            {{#each .}}
            <div class="div_template" data-rno="{{rno}}">
              <i class="fas fa-envelope bg-blue"></i>
              <div class="timeline-item">
                <h3 class="timeline-header">{{replyer}}</h3>
                <div class="timeline-body">{{reply_text}}</div>
                <div class="timeline-footer">
                  <a class="btn btn-primary btn-sm" data-toggle="modal" data-target="#modal-reply">수정</a>
                </div>
              </div>
            </div>
            {{/each}}
            </script>
            
            <!-- 페이징처리 -->
            <div class='row'>
              <ul class="pagination" style="margin: 0 auto;">
                <!-- <li class="paginate_button page-item previous disabled" id="example2_previous">
                  <a href="#" aria-controls="example2" data-dt-idx="0" tabindex="0" 
                  class="page-link">Previous</a>
                </li>
                <li class="paginate_button page-item active">
                  <a href="#" aria-controls="example2" data-dt-idx="1" tabindex="0" 
                  class="page-link">1</a>
                </li>
                <li class="paginate_button page-item ">
                  <a href="#" aria-controls="example2" data-dt-idx="2" tabindex="0" 
                  class="page-link">2</a>
                </li>
                <li class="paginate_button page-item ">
                  <a href="#" aria-controls="example2" data-dt-idx="3" tabindex="0" 
                  class="page-link">3</a>
                </li>
                <li class="paginate_button page-item ">
                  <a href="#" aria-controls="example2" data-dt-idx="4" tabindex="0" 
                  class="page-link">4</a>
                </li>
                <li class="paginate_button page-item ">
                  <a href="#" aria-controls="example2" data-dt-idx="5" tabindex="0" 
                  class="page-link">5</a>
                </li>
                <li class="paginate_button page-item ">
                  <a href="#" aria-controls="example2" data-dt-idx="6" tabindex="0" 
                  class="page-link">6</a>
                </li>
                <li class="paginate_button page-item next" id="example2_next">
                  <a href="#" aria-controls="example2" data-dt-idx="7" tabindex="0" 
                  class="page-link">Next</a>
                </li> -->
              </ul>
            </div>
            <!-- //페이징처리 -->
            </div>
            <!-- //콜랩스 끝 -->
          </div>
          <!-- //타임라인 끝 -->
        </div>
        <!-- //댓글 타임라인 -->

        <!-- //컨텐츠 내용 -->
      </div>
      <!-- /.container-fluid -->
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->

<%@ include file="../include/footer.jsp"%>
