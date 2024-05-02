const reviewList = document.querySelector(".reviewList");
const reviewsLoad = () => {
  fetch(`/reviews/${mno}/all`)
    .then((respone) => respone.json())
    .then((data) => {
      console.log(data);
      document.querySelector(".review-cnt").innerHTML = data.length;
      const index = data.length;
      document.querySelector("#review-cnt").value = index;

      let result = "";
      data.forEach((review) => {
        result += `<div class="d-flex justify-content-between my-2 border-bottom py-2 review-row" data-rno="${review.reviewNo}">`;
        result += `<div class="flex-grow-1 align-self-center">`;
        result += `<div><span class="font-semibold">리뷰 내용 : ${review.text}</span></div>`;
        result += `<div class="small text-muted">`;
        result += `<span class="d-inline-block mr-3">리뷰 작성자  : ${review.nickname}</span>평점 : ${review.grade}<span class="grade"></span></div>`;
        result += `<div class="text-muted"><span class="small">${formatDate(review.createdDate)}</span></div></div>`;
        result += `<div class="d-flex flex-column align-self-center">`;
        result += `<div class="mb-2"><button class="btn btn-outline-danger btn-sm">삭제</button></div>`;
        result += `<div><button class="btn btn-outline-success btn-sm">수정</button></div>`;
        result += `</div></div>`;
      });

      reviewList.innerHTML = result;
      if (index) {
        reviewList.classList.remove("hidden");
      }
    });
};

const formatDate = (data) => {
  const date = new Date(data);

  return date.getFullYear() + "/" + (date.getMonth() + 1) + "/" + date.getDate() + " " + date.getHours() + ":" + date.getMinutes();
};
reviewsLoad();
const review_form = document.querySelector(".review-form");

review_form.addEventListener("submit", (e) => {
  e.preventDefault();
  const reviewNum = document.querySelector("#reviewNo").value;
  const mid = document.querySelector("#mid").value;
  const data1 = {
    mid: mid,
    text: document.querySelector("#text").value,
    mno: mno,
    grade: grade || 0,
    reviewNo: reviewNum,
  };
  console.log(data);
  if (!reviewNum) {
    fetch(`/reviews/${mno}`, {
      method: "post",
      headers: {
        "content-type": "application/json",
      },
      body: JSON.stringify(data1),
    })
      .then((respone) => respone.text())
      .then((data) => {
        console.log(data);
        document.querySelector("#text").value = "";
        document.querySelector("#nickname").value = "";
        review_form.querySelector(".starrr a:nth-child(" + grade + ")").click();
        if (data) {
          alert(data + "번 리뷰가 입력되었습니다.");
        }
        reviewsLoad();
      });
  } else {
    fetch(`/reviews/${mno}/${reviewNum}`, {
      method: "put",
      headers: {
        "content-type": "application/json",
      },
      body: JSON.stringify(data1),
    })
      .then((respone) => respone.text())
      .then((data) => {
        console.log(data);
        document.querySelector("#text").value = "";
        document.querySelector("#nickname").value = "";
        document.querySelector("#reviewNo").value = "";
        review_form.querySelector("button").innerHTML = "리뷰 등록";
        review_form.querySelector(".starrr a:nth-child(" + grade + ")").click();
        if (data) {
          alert(data + "번 리뷰가 수정되었습니다.");
        }
        reviewsLoad();
      });
  }
});

// 삭제 클릭시 review no 가저오기

reviewList.addEventListener("click", (e) => {
  console.log(e.target);
  const btn = e.target;
  const reviewNo = btn.closest(".review-row").dataset.rno;
  if (btn.innerHTML == "삭제") {
    if (confirm("삭제 하시겠습니까?") == true) {
      fetch(`/reviews/${mno}/${reviewNo}`, {
        method: "delete",
      })
        .then((response) => response.text())
        .then((data) => {
          if (data == `${reviewNo}`) {
            alert("삭제 성공");
            reviewsLoad();
          }
        });
    }
  }
  if (btn.innerHTML == "수정") {
    fetch(`/reviews/${mno}/${reviewNo}`, {
      method: "get",
    })
      .then((response) => response.json())
      .then((data) => {
        document.querySelector("#text").value = data.text;
        document.querySelector("#nickname").value = data.nickname;
        document.querySelector("#reviewNo").value = data.reviewNo;
        review_form.querySelector(".starrr a:nth-child(" + data.grade + ")").click();
        review_form.querySelector("button").innerHTML = "리뷰 수정";
      });
  }
});
