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
