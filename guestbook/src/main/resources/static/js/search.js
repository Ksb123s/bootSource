const form = document.querySelector("#searchForm");
const keyword = document.querySelector("#keyword");
const type = document.querySelector("#type");
form.addEventListener("submit", (e) => {
  e.preventDefault();

  if (!type.value) {
    alert("타입을 선택해 주세요");
    type.focus();
    return;
  }

  if (!keyword.value) {
    alert("키워드를 입력해 주세요");
    keyword.focus();
    return;
  }
  e.target.submit();
});
