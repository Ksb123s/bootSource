const input = document.querySelector("#fileInput");

const uploadResult = document.querySelector(".uploadResult ul");

function checkExtension(fileName) {
  // 정규식 사용
  const regex = /(.*?).(png|gif|jpg|jpeg)$/;
  console.log(regex.test(fileName));

  return regex.test(fileName);
}

function showUploadImages(arr) {
  console.log("showUploadImages ", arr);

  let tages = "";
  arr.forEach((obj) => {
    tages += `<li data-name="${obj.fileName}" data-path="${obj.folderPath}" data-uuid="${obj.uuid}">`;
    tages += `<div>`;
    tages += `<a href=""><img src="/upload/display?fileName=${obj.thumbImageURL}" class="block"></a>`;
    tages += `<span class="text-sm d-inline-block mx-1">${obj.fileName}</span>`;
    tages += ` <a href="#" data-file="${obj.imageURL}">`;
    tages += ` <i class="fa-solid fa-xmark" ></i>`;
    tages += `</a></div></li>`;
  });
  uploadResult.insertAdjacentHTML("beforeend", tages);
}

// fileInput change event

// 이미지 파일이라면 formdata 객체 생성후 어펜드
input.addEventListener("change", (e) => {
  console.log(e.target);

  const files = e.target.files;

  const formData = new FormData();

  for (let index = 0; index < files.length; index++) {
    if (checkExtension(files[index].name)) {
      formData.append("uploadFiles", files[index]);
    }
  }
  for (const value of formData.values()) {
    console.log(value);
  }

  fetch("/upload/uploadAjax", {
    method: "post",
    body: formData,
  })
    .then((response) => response.json())
    .then((data) => {
      console.log(data);
      showUploadImages(data);
    });
});

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

  form.submit();
});
