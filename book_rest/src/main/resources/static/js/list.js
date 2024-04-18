// 제목 클릭시 a테그 기능 중지
// href 값을 변경
document.querySelectorAll(".text-decoration-none").forEach((form) => {
  form.addEventListener("click", (e) => {
    e.preventDefault();

    console.log(e.target.dataset.id);

    fetch(`http://localhost:8080/read/${e.target.dataset.id}`)
      .then((respone) => respone.json())
      .then((data) => {
        console.log(data);
        document.querySelector("#category").value = data.categoryName;
        document.querySelector("#title").value = data.title;
        document.querySelector("#publisher").value = data.publisherName;
        document.querySelector("#writer").value = data.writer;
        document.querySelector("#price").value = data.price;
        document.querySelector("#salePrice").value = data.salePrice;
        document.querySelector("#book_id").value = data.id;
      });
  });
});

// document.querySelector("tbody").addEventListener("click", (e) => {
//   e.preventDefault();

//   console.log(e.target.dataset.id);

//   fetch(`http://localhost:8080/read/${e.target.dataset.id}`)
//     .then((respone) => respone.json())
//     .then((data) => {
//       console.log(data);

//       //   디자인 영역 가져오기
//       document.querySelector("#category").value = data.categoryName;
//       document.querySelector("#title").value = data.title;
//       document.querySelector("#publisher").value = data.publisherName;
//       document.querySelector("#writer").value = data.writer;
//       document.querySelector("#price").value = data.price;
//       document.querySelector("#salePrice").value = data.salePrice;
//     });
// });