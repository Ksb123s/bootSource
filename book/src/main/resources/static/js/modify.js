const form = document.querySelector("#actionForm");
document.querySelector(".btn-danger").addEventListener("click", () => {
  if (!confirm("정말로 지우시겠습니까?")) {
    return;
  }
 
  form.action = "/book/delete";
  form.submit();
});
