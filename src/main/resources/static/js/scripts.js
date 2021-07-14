/*!
* Start Bootstrap - Clean Blog v6.0.3 (https://startbootstrap.com/theme/clean-blog)
* Copyright 2013-2021 Start Bootstrap
* Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-clean-blog/blob/master/LICENSE)
*/
window.addEventListener('DOMContentLoaded', () => {
    let scrollPos = 0;
    const mainNav = document.getElementById('mainNav');
    const headerHeight = mainNav.clientHeight;
    window.addEventListener('scroll', function() {
        const currentTop = document.body.getBoundingClientRect().top * -1;
        if ( currentTop < scrollPos) {
            // Scrolling Up
            if (currentTop > 0 && mainNav.classList.contains('is-fixed')) {
                mainNav.classList.add('is-visible');
            } else {
                console.log(123);
                mainNav.classList.remove('is-visible', 'is-fixed');
            }
        } else {
            // Scrolling Down
            mainNav.classList.remove(['is-visible']);
            if (currentTop > headerHeight && !mainNav.classList.contains('is-fixed')) {
                mainNav.classList.add('is-fixed');
            }
        }
        scrollPos = currentTop;
    });
})


    //본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
    function sample4_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
                // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var roadAddr = data.roadAddress; // 도로명 주소 변수
                var extraRoadAddr = ''; // 참고 항목 변수
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraRoadAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraRoadAddr !== ''){
                    extraRoadAddr = ' (' + extraRoadAddr + ')';
                }
                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample4_postcode').value = data.zonecode;
                document.getElementById("sample4_roadAddress").value = roadAddr;
                document.getElementById("sample4_jibunAddress").value = data.jibunAddress;

                // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
                if(roadAddr !== ''){
                    document.getElementById("sample4_extraAddress").value = extraRoadAddr;
                } else {
                    document.getElementById("sample4_extraAddress").value = '';
                }
                var guideTextBox = document.getElementById("guide");
                // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
                if(data.autoRoadAddress) {
                    var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                    guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
                    guideTextBox.style.display = 'block';
                } else if(data.autoJibunAddress) {
                    var expJibunAddr = data.autoJibunAddress;
                    guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
                    guideTextBox.style.display = 'block';
                } else {
                    guideTextBox.innerHTML = '';
                    guideTextBox.style.display = 'none';
                }
            }
        }).open();
    }

function sign_check() {

    var signupform = document.form;

    if(!signupform.member_id.value){
        alert('아이디를 입력해주세요');
        signupform.member_id.focus();
        return false;
    }

    if(signupform.member_id.value.length < 4 || signupform.member_id.value.length >= 20) {
        alert('영문, 숫자[최소 4글자~20글자] 입력해주세요.');
        signupform.member_id.focus();
        return false;
    }

    // 영문, 숫자만 id로 사용
    var regx = /^[a-zA-Z0-9]*$/; // 자바 스크립트 정규 표현식 (중요)

    if(!regx.test(signupform.member_id.value)) {

        // 현재 정규표현식이 포함되어 있지 않으면
        alert('영문과 숫자로만 입력해주세요.');
        return false;

    }

    if(!signupform.member_pw.value){
        alert('패스워드를 입력해주세요.');
        signupform.member_pw.focus();
        return false;
    }

    if(signupform.member_pw.value != signupform.member_pw2.value){
        alert('패스워드가 일치하지 않습니다.');
        signupform.member_pw2.focus();
        return false;
    }

    if(!signupform.name.value){
        alert('이름을 입력해주세요.');
        signupform.name.focus();
        return false;
    }

    if(!signupform.gender.value) {
        alert('성별을 선택해주세요.');
        return false;
    }

}

function login_check() {

    var loginform = document.loginform;

    if(!loginform.member_id.value) {
        alert('아이디를 입력해주세요.');
        loginform.member_id.focus();
        return false;
    }

    if(!loginform.member_pw.value) {
            alert('패스워드를 입력해주세요.');
            loginform.member_pw.focus();
            return false;
    }
}

// 로그인후 뒤로가기 막기
//window.history.forward();
/* function noBack() {
 window.history.forward();
}*/

// 아이디 중복체크
function id_check() {

	     var signupform = document.form;
         var data = signupform.member_id.value;

          var MemberDto={
              member_id:data
          };
          $.ajax({
              url: "/dataSend",
              data: MemberDto,
              type:"POST",
          }).done(function (fragment) {
              $("#resultDiv").replaceWith(fragment);
          });
}

// 로그인
function info_check() {

    var infoform = document.infoform;

    if(!infoform.member_pw.value) {

        alert('패스워드를 입력해주세요.');
        infoform.member_pw.focus();
        return false;

    }

}

// 비밀번호 변경 페이지 패스워드 검사
function updatepw() {

    var updatepwform = document.updatepwform; // form

    if(!updatepwform.password2.value) {
        alert("기존 비밀번호를 입력해주세요.");
        updatepwform.password2.focus();
        return false;
    }

    if(!updatepwform.member_pw.value) {
        alert("변경할 비밀번호를 입력해주세요.");
        updatepwform.member_pw.focus();
        return false;
    }

    if(!updatepwform.member_pw2.value) {
        alert("변경할 비밀번호 확인란을 입력해주세요.");
        updatepwform.member_pw2.focus();
        return false;
    }

    if(updatepwform.password.value != updatepwform.password2.value) {
        alert("기존 비밀번호가 틀립니다.");
        updatepwform.password2.focus();
        return false;
    }

    if(updatepwform.member_pw.value != updatepwform.member_pw2.value) {
        alert("변경할 패스워드가 다릅니다.");
        updatepwform.member_pw2.focus();
        return false;

    }

}

//  게시판 등록 제목, 내용 유효성 검사
function boardwrite_check() {

    var boardwriteform = document.boardwriteform;

    if(!boardwriteform.title.value) {

        alert("제목을 입력해주세요.");
        boardwriteform.title.focus();
        return false;

    }

    if(!boardwriteform.contents.value) {

            alert("내용이 비어있습니다.");
            boardwriteform.contents.focus();
            return false;

    }

}

function formsubmit() {

    $.ajax({
        url : "cartadd",
        type : 'POST',
        dataType : 'text',
        data : $("#form").serialize(),
        success: function() {
        var result = confirm("OK or cancel?");
        if(result) {
            // User clicked OK
        }
        else {
            // User clicked cancel
                }
    })

}