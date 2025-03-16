    let sessionId = <%=user_no%>;
    sessionId = sessionId.toString();

    const board = document.querySelector(".board-container");

    if(board.getAttribute("data-user_no") !== sessionId){
        const hiddenBtnContainer = board.querySelector(".hidden-btn-container");
        hiddenBtnContainer.classList.add('invalid');
    }

    const commentContainerArr = document.querySelectorAll(".comment-container");
    commentContainerArr.forEach( (commentContainer) => {
        if(commentContainer.getAttribute("data-user_no") !== sessionId){
            const hiddenBtnContainer = commentContainer.querySelector(".hidden-btn-container");
            hiddenBtnContainer.classList.add('invalid');
        }

    });
    const replyContainerArr = document.querySelectorAll(".reply-container");
    replyContainerArr.forEach( (replyContainer) => {
        if(replyContainer.getAttribute("data-user_no") !== sessionId){
            //userid와 세션아이디가 다른경우 유효하지않은 버튼으로처리
            const hiddenBtnContainer = replyContainer.querySelector(".hidden-btn-container");
            hiddenBtnContainer.classList.add('invalid');
        }
    })
function showReplyForm(elem){
    const replyUpdateBtn = elem;
    let replyWrapper = replyUpdateBtn.closest(".reply-wrapper");
    replyWrapper.style.display = "none";
    let replyUpdateWrapper = replyWrapper.closest(".reply-container").querySelector(".reply-update-wrapper");
    replyUpdateWrapper.style.display = "block";

}
function showCommentForm(elem){
    const commentUpdateBtn = elem;
    let commentWrapper = commentUpdateBtn.closest(".comment-wrapper");
    commentWrapper.style.display = "none";
    let commentUpdateWrapper = commentWrapper.closest(".comment-container").querySelector(".comment-update-wrapper");
    commentUpdateWrapper.style.display = "block";
}

function showBoardForm(elem){
    const boardBtn = elem;
    let boardWrapper = boardBtn.closest(".board-wrapper");
    boardWrapper.style.display = "none";
    let boardUpdateWrapper = boardWrapper.closest(".board-container").querySelector(".board-update-wrapper");
    boardUpdateWrapper.style.display = "block";


}
function cancleReplyForm(elem){
    const replyCancleBtn = elem;
    let replyUpdateWrapper = replyCancleBtn.closest(".reply-update-wrapper");
    replyUpdateWrapper.style.display = "none";
    let replyWrapper = replyUpdateWrapper.closest(".reply-container").querySelector(".reply-wrapper");
    replyWrapper.style.display ="block";
}

function cancleCommentForm(elem){
    const commentCancleBtn = elem;
    let commentUpdateWrapper = commentCancleBtn.closest(".comment-update-wrapper");
    commentUpdateWrapper.style.display = "none";
    let commentWrapper = commentUpdateWrapper.closest(".comment-container").querySelector(".comment-wrapper");
    commentWrapper.style.display ="block";
}

function cancleBoardForm(elem){
    const cancleBtn = elem;
    let boardUpdateWrapper = cancleBtn.closest(".board-update-wrapper");
    boardUpdateWrapper.style.display = "none";
    let boardWrapper = boardUpdateWrapper.closest(".board-container").querySelector(".board-wrapper");
    boardWrapper.style.display ="block";
}

board.querySelector(".update-board-btn").addEventListener("click", (e) => {
        if(!confirm("수정하시겠습니까?")){
            console.log("아니요");


        } else{

            let board_no = e.target.closest(".board-container").getAttribute("data-board_no");
            let user_no = e.target.closest(".board-container").getAttribute("data-user_no");

            let updateTitle = e.target.closest(".board-container").querySelector(".board-update-title").value;
            let updateContent = e.target.closest(".board-container").querySelector(".board-update-content").value;
            let updateBtn = e.target;

            const xhttp = new XMLHttpRequest();
            xhttp.open("POST", "/board/UpdateBoardController", true);
            xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded"); // 보낼 데이터 헤더를 지정

            let postParameter = "board_no=" + board_no + "&user_no=" + user_no + "&updateTitle=" + updateTitle + "&updateContent=" + updateContent;

            xhttp.send(postParameter);

            xhttp.onload = function() {
                if (xhttp.status === 200) {
                    const responseText = xhttp.responseText;
                    const [title, content] = responseText.split("|"); // 구분자로 나눔

                    console.log("제목:", title);
                    console.log("내용:", content);
                    updateBtn.closest(".board-container").querySelector(".title").innerText = title;
                    updateBtn.closest(".board-container").querySelector(".board-content").innerText = content;



                    let boardUpdateWrapper = updateBtn.closest(".board-update-wrapper");
                    boardUpdateWrapper.style.display = "none";
                    let boardWrapper = boardUpdateWrapper.closest(".board-container").querySelector(".board-wrapper");
                    boardWrapper.style.display ="block";
                }
            }
        }});

const updateCommentBtnArr = document.querySelectorAll(".update-comment-btn");
updateCommentBtnArr.forEach( (updateCommentBtn) => {
        updateCommentBtn.addEventListener("click",
            (e) => {
            if(!confirm("수정하시겠습니까?")){
                console.log("아니요");
                console.log(e.target);

            } else{
              console.log("예");
              console.log(e.target);

              const updateBtn = e.target;
              let comment_no = updateBtn.closest(".comment-container").getAttribute("data-comment_no");
              let user_no = updateBtn.closest(".comment-container").getAttribute("data-user_no");

              console.log(comment_no,user_no);
              let updatingContent = updateBtn.closest(".comment-container").querySelector(".comment-update-content").value;
              console.log(updatingContent)

              let postParameter = "comment_no=" + comment_no + "&user_no=" + user_no + "&updatingContent=" + updatingContent;
              const xhttp = new XMLHttpRequest();
              console.log(updatingContent);
              xhttp.open("POST", "/board/UpdateCommentController", true);
              xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded"); // 보낼 데이터 헤더를 지정


              console.log("변수를 따로 만들고 출력");
              console.log(postParameter);
              xhttp.send(postParameter);

              xhttp.onload = function() {
                  if (xhttp.status === 200) {
                      const getNewContent = xhttp.responseText;

                      console.log("내용:", getNewContent);
                      updateBtn.closest(".comment-container").querySelector(".comment-content").innerText = getNewContent;



                      let commentUpdateWrapper = updateBtn.closest(".comment-update-wrapper");
                      commentUpdateWrapper.style.display = "none";
                      let commentWrapper = commentUpdateWrapper.closest(".comment-container").querySelector(".comment-wrapper");
                      commentWrapper.style.display ="block";
                  }
              }
        }
      }
  )}
);
const updateReplyBtnArr = document.querySelectorAll(".update-reply-btn");
updateReplyBtnArr.forEach( (updateReplyBtn) => {
        updateReplyBtn.addEventListener("click",
            (e) => {
            if(!confirm("수정하시겠습니까?")){
                console.log("아니요");
                console.log(e.target);

            } else{
              console.log("예");
              console.log(e.target);

              const updateBtn = e.target;
              let comment_no = updateBtn.closest(".reply-container").getAttribute("data-comment_no");
              let user_no = updateBtn.closest(".reply-container").getAttribute("data-user_no");

              console.log(comment_no,user_no);
              let updatingContent = updateBtn.closest(".reply-container").querySelector(".reply-update-content").value;
              console.log(updatingContent)

              let postParameter = "comment_no=" + comment_no + "&user_no=" + user_no + "&updatingContent=" + updatingContent;
              const xhttp = new XMLHttpRequest();
              console.log(updatingContent);
              xhttp.open("POST", "/board/UpdateCommentController", true);
              xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded"); // 보낼 데이터 헤더를 지정


              console.log("변수를 따로 만들고 출력");
              console.log(postParameter);

              xhttp.send(postParameter);

              xhttp.onload = function() {
                  if (xhttp.status === 200) {
                      const getNewContent = xhttp.responseText;

                      console.log("내용:", getNewContent);
                      updateBtn.closest(".reply-container").querySelector(".comment-content").innerText = getNewContent;



                      let replyUpdateWrapper = updateBtn.closest(".reply-update-wrapper");
                      replyUpdateWrapper.style.display = "none";
                      let replyWrapper = replyUpdateWrapper.closest(".reply-container").querySelector(".reply-wrapper");
                      replyWrapper.style.display ="block";
                  }
              }
            }
      }
  )}
);