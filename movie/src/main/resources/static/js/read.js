const reviewList = document.querySelector(".reviewList");
const reviewsLoad = () => {
  fetch(`/reviews/${mno}/all`)
    .then((respone) => respone.json())
    .then((data) => {
      console.log(data);

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
    });
};

const formatDate = (data) => {
  const date = new Date(data);

  return date.getFullYear() + "/" + (date.getMonth() + 1) + "/" + date.getDate() + " " + date.getHours() + ":" + date.getMinutes();
};
reviewsLoad();

// 작은 포스트 클릭하면 큰 포스터 보여주기
const imgModal = document.getElementById("imgModal");

if (imgModal) {
  imgModal.addEventListener("show.bs.modal", (e) => {
    // 모달을 뜨게 만든 li 가져오기
    const posterLi = e.relatedTarget;

    const file = posterLi.getAttribute("data-file");
    console.log("file", file);

    // 타이틀 영역 영화명 삽입
    imgModal.querySelector(".modal-title").textContent = `${title}`;

    // 이미지
    const modlaBody = imgModal.querySelector(".modal-body");

    modlaBody.innerHTML = `<img src="/upload/display?fileName=${file}" style="width :100%"/>`;
  });
}
