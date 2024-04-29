const form = document.querySelector("#register-form");

form.addEventListener("submit", (e) => {
  e.preventDefault();

  const liList = document.querySelectorAll(".uploadResult ul li");
  console.log(liList);

  //   수집된 정보를 폼 태그에 붙여넣기
  let result = "";
  liList.forEach((i, idx) => {
    // hidden 3 => MoveImageDto 객체 하나로 변경
    // movieImageDtos dto 이름에 맞춰 설정
    result += `<input type='hidden' value='${i.dataset.path}' name='movieImageDtos[${idx}].path'>`;
    result += `<input type='hidden' value='${i.dataset.uuid}' name='movieImageDtos[${idx}].uuid'>`;
    result += `<input type='hidden' value='${i.dataset.name}' name='movieImageDtos[${idx}].imgName'>`;
  });
  form.insertAdjacentHTML("beforeend", result);
  console.log(form.innerHTML);

  e.target.submit();
});

document.querySelector(".uploadResult").addEventListener("click", (e) => {
  e.preventDefault();
  console.log("x 버튼", e.target);
  console.log("x 버튼", e.currentTarget);

  const curentLi = e.target.closest("li");

  const filePath = e.target.closest("a").dataset.file;
  console.log("filePath", filePath);

  const formData2 = new FormData();

  formData2.append("filePath", filePath);
  console.log(filePath);
  fetch("/upload/remove", {
    method: "post",
    body: filePath,
  })
    .then((response) => response.text())
    .then((data) => {
      console.log(data);
      if (data) {
        curentLi.remove();
      }
    });
});
