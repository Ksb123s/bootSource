// 검색창에서 enter 입력 이벤트 발생
// searchForm 전송

const searchForm = document.getElementById("searchForm");

document.querySelector("[name='keyword']").addEventListener("keyup", (e) => {
  if (e.keyCode == 13) {
    // alert("enter");
    const keyword = e.target.value;
    if (!keyword) {
      alert("검색어를 입력해 주세요");
      return;
    }
    searchForm.querySelector("[name='keyword']").value = keyword;
    console.log(searchForm);

    searchForm.submit();
  }
  //
});
