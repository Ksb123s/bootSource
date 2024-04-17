const form = document.querySelector("#actionForm");

document.querySelector(".btn-danger").addEventListener("click", () => {
  // form.action = "/guestbook/delete";
  form.submit();
});
