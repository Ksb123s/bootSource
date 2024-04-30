// 삭제 버튼 클릭
// actionForm 보내기
const actionForm = document.querySelector("#actionForm");

document.querySelector(".btn-danger").addEventListener("click", () => {
  if (!confirm("삭제하시겠습니까?")) {
    return;
  }

  actionForm.submit();
});

document.querySelector(".uploadResult").addEventListener("click", (e) => {
  e.preventDefault();
  console.log("x 버튼", e.target);
  console.log("x 버튼", e.currentTarget);

  const curentLi = e.target.closest("li");

  if (confirm("삭제하시겠습니까?")) {
    curentLi.remove();
  }
});
