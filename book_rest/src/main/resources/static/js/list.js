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

// 삭제 버튼 클릭시 id 가져와서 실행
document.querySelector(".btn-primary").addEventListener("click", (e) => {
  e.preventDefault();
  const book_id = document.querySelector("#book_id").value;
  console.log(book_id);
  fetch(`/delete/${book_id}`, {
    method: "delete",
  })
    .then((response) => response.text())
    .then((data) => {
      if (data == "success") {
        alert("삭제 성공");
        location.href = "/book/list?page=1&type=&keyword=";
      }
    });
});
const my_form = document.querySelector("#my_form");
document.querySelector(".btn-secondary").addEventListener("click", (e) => {
  e.preventDefault();
  const id = document.querySelector("#book_id").value;
  const data = {
    id: id,
    price: document.querySelector("#price").value,
    salePrice: document.querySelector("#salePrice").value,
  };

  console.log(data);

  //메소드 지정 안하면 get() 방식
  fetch(`http://localhost:8080/modify/${id}`, {
    method: "put",
    headers: {
      "content-type": "application/json",
    },
    body: JSON.stringify(data), //JSON.stringify() javascript 객체 => JSON 객체 변경
  })
    .then((response) => response.text())
    .then((data) => {
      if (data == "success") {
        alert("수정 성공");
        location.href = "/book/list?page=1&type=&keyword=";
      }
    });
});
