/**
 * 
 */

function infoConfirm() {
	if(document.reg_frm.id.value.length == 0) {
		alert("아이디는 필수 사항입니다.");
		reg_frm.id.focus();
		return false;
	}
	
	if(document.reg_frm.id.value.length < 4) {
		alert("아이디는 4글자 이상이어야 합니다.");
		reg_frm.id.focus();
		return false; 
	}
	
	if(document.reg_frm.pwd.value.length == 0) {
		alert("비밀번호는 필수 사항입니다.");
		reg_frm.pw.focus();
		return false;
	}
	
	if(document.reg_frm.name.value.length == 0) {
		alert("이름은 필수 사항입니다.");
		reg_frm.name.focus();
		return false;
	}
	
	if(document.reg_frm.id.value.length == 0) {
		alert("아이디는 필수 사항입니다.");
		reg_frm.id.focus();
		return false;
	}
	
	if(document.reg_frm.email.value.length == 0) {
		alert("메일은 필수 사항입니다.");
		reg_frm.eMail.focus();
		return false;
	}

	
	document.reg_frm.submit();
}

function updateInfoConfirm() {
	if(document.reg_frm.pwd.value == "") {
		alert("패스워드를 입력하세요.");
		document.reg_frm.pwd.focus();
		return false;
	}
	
	if(document.reg_frm.email.value.length == 0) {
		alert("메일은 필수 사항입니다.");
		reg_frm.email.focus();
		return false;
	}

	document.reg_frm.submit();
	
}