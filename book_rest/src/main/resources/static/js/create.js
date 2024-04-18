// formsubmit 시 기능 중지
//  form 안에 내용 가져오기

document.querySelector("#form").addEventListener("submit", (e) => {
  e.preventDefault();
  const data = {
    categoryName: document.querySelector("[name='categoryName']").value,
    title: document.querySelector("[name='title']").value,
    publisherName: document.querySelector("[name='publisherName']").value,
    writer: document.querySelector("[name='writer']").value,
    price: document.querySelector("[name='price']").value,
    salePrice: document.querySelector("[name='salePrice']").value,
  };

  console.log(data);

  //메소드 지정 안하면 get() 방식
  fetch(`http://localhost:8080/book/new`, {
    method: "post",
    headers: {
      "content-type": "application/json",
    },
    body: JSON.stringify(data), //JSON.stringify() javascript 객체 => JSON 객체 변경
  })
    .then((response) => response.text())
    .then((data) => {
      if (data == "success") {
        alert("입력 성공");
        location.href = "/book/list?page=1&type=&keyword=";
      }
    });
});
