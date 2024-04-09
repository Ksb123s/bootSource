//  중복 이벤트 감지
document.querySelectorAll("[name='completed']").forEach((list) => {
  list.addEventListener("click", (e) => {
    console.log(e.target.value);
  });
});

// 이벤트 전파 => 부모요소 감지
document.querySelector(".list-group").addEventListener("click", (e) => {
  console.log("이벤트가 발생한 대상 :" + e.target);
  console.log("이벤트가 발생한 대상 value :" + e.target.value);
  console.log("이벤트를 감지한 대상 :" + e.currentTarget);

  //   get 방식
  //   location.href = "/todo/update?id="+ e.target.value;

  // post 방식
  const form = document.querySelector("#completedForm");
  form.querySelector("[name='id']").value = e.target.value;
  form.submit();
});
