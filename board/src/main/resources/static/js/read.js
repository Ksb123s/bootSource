// 페이지 로드시 무조건 실행
// 즉시 실행 함수
// fetch()- 함수 작성후 호출

// 함수 작성
//  1. function getReplies() {}  실행 getReplies(); 호이스팅 가능
//  2. const(or let) 이름 = () =>{} 호이스팅 불가

const replyList = document.querySelector(".replyList");
// 호이스팅
// 선언 하지 않고 먼저 호출후 선언

// 단 var 로 선언된 변수는 호이스팅 가능 let, const 불가

// 날짜 포멧 변경 함수
const formatDate = (data) => {
  const date = new Date(data);

  return date.getFullYear() + "/" + (date.getMonth() + 1) + "/" + date.getDate() + " " + date.getHours() + ":" + date.getMinutes();
};

const replies = () => {
  // 댓글 목록 보여줄 영역 가져오기
  const replyList = document.querySelector(".replyList");

  fetch(`/replies/board/${bno}`)
    .then((response) => response.json())
    .then((data) => {
      console.log(data);
      console.log(data.length);
      const index = data.length;

      document.querySelector(".d-inline-block").innerHTML = index;

      let result = "";
      data.forEach((reply) => {
        result += ` <div class="d-flex justify-content-between my-2 border-bottom reply-row" data-rno="${reply.rno}">`;
        result += `<div class="p-3"><img src="/img/default.png" alt="" class="rounded-circle mx-auto d-block" style="width: 60px; height: 60px" /></div>`;
        result += `<div class="flex-grow-1 align-self-center">`;
        result += `<div>${reply.replyer}</div>`;
        result += `<div><span class="fx-5">${reply.text}</span></div>`;
        result += `<div class="text-muted"><span class="small">${formatDate(reply.createDate)}</span></div></div>`;
        result += `<div class="d-flex flex-column align-self-center">`;
        result += `<div class="mb-2"><button class="btn btn-outline-danger btn-sm">삭제</button></div>`;
        result += `<div><button class="btn btn-outline-success btn-sm">수정</button></div></div></div>`;
        replyList.innerHTML = result;
      });
    });
};
replies();
// 새 댓글 등록
const rno1 = document.querySelector("#rno");
document.querySelector("#reply_form").addEventListener("submit", (e) => {
  e.preventDefault();

  const data = {
    bno: bno,
    replyer: document.querySelector("#replyer").value,
    text: document.querySelector("#text").value,
    rno: rno1.value,
  };

  if (!rno.value) {
    //새댓글 등록
    fetch(`/replies/new`, {
      method: "post",
      headers: {
        "content-type": "application/json",
      },
      body: JSON.stringify(data),
    })
      .then((response) => response.text())
      .then((data) => {
        console.log(data);
        if (data) {
          alert(data + "번 댓글 등록 완료");
          document.querySelector("#replyer").value = "";
          document.querySelector("#text").value = "";
          rno1.value = "";
          replies();
          // location.href = `/board/read?bno=${bno}&page=${page}&type=${type}&keyword=${keyword}`;
        }
      });
  } else {
    // 수정
    fetch(`/replies/${rno.value}`, {
      method: "put",
      headers: {
        "content-type": "application/json",
      },
      body: JSON.stringify(data),
    })
      .then((response) => response.text())
      .then((data) => {
        console.log(data);
        if (data) {
          alert(data + "번 댓글 수정 완료");
          document.querySelector("#replyer").value = "";
          document.querySelector("#text").value = "";
          replies();
          // location.href = `/board/read?bno=${bno}&page=${page}&type=${type}&keyword=${keyword}`;
        }
      });
  }
});

// 삭제 버튼 클릭시 rno 가저오기

//

replyList.addEventListener("click", (e) => {
  const btn = e.target;
  // closest("요소").dataset.이름 -> 요소 안에 데이터 가져오기
  const rno = btn.closest(".reply-row").dataset.rno;

  if (btn.innerHTML == "삭제") {
    if (confirm("삭제 하시겠습니까?") == true) {
      fetch(`/replies/${rno}`, { method: "delete" })
        .then((response) => response.text())
        .then((data) => {
          if (data == "success") {
            alert("삭제 성공");
            replies();
          }
        });
    }
  }
  if (btn.innerHTML == "수정") {
    fetch(`/replies/${rno}`, { method: "get" })
      .then((response) => response.json())
      .then((data) => {
        console.log(data);

        document.querySelector("#replyer").value = data.replyer;
        document.querySelector("#text").value = data.text;
        document.querySelector("#bno").value = data.bno;
        rno1.value = data.rno;
      });
  }
});
