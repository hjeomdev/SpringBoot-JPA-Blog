let index = {
	init: function() {
		$("#btn-save").on("click", () => { // this를 바인딩하기 위해서 화살표 함수를 사용하였다. 
			this.save();
		});
		$("#btn-login").on("click", () => { // this를 바인딩하기 위해서 화살표 함수를 사용하였다. 
			this.login();
		});
	},
	
	save: function() {
		//alert("user의 save 함수 호출됨.");
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		};
		
		//console.log(data);
		
		// ajax 호출시 default가 비동기 호출
		// ajax 통신으로 3개의 데이터를 json으로 변경하여 insert 요청 
		$.ajax({
			// 회원가입 수행 요청 
			type: "POST",
			url: "/blog/api/user",
			data: JSON.stringify(data), // http body데이터 
			contentType: "application/json; charset=utf-8", // body 데이터 타입(MIME)
			dataType: "json" // 요청이 서버로해서 응답이 왔을 때 기본적으로 모든것이 문자열(생긴게 json이라면) => js오브젝트로 변경 
		}).done(function(resp){
			alert("회원가입이 완료");
			location.href = "/blog";
		}).fail(function(error){
			alert(JSON.stringify(error));
		});  
	},
	login: function() {
		//alert("user의 save 함수 호출됨.");
		let data = {
			username: $("#username").val(),
			password: $("#password").val()
		};
		
		$.ajax({
			// 회원가입 수행 요청 
			type: "POST",
			url: "/blog/api/user/login",
			data: JSON.stringify(data), // http body데이터 
			contentType: "application/json; charset=utf-8", // body 데이터 타입(MIME)
			dataType: "json" // 요청이 서버로해서 응답이 왔을 때 기본적으로 모든것이 문자열(생긴게 json이라면) => js오브젝트로 변경 
		}).done(function(resp){
			alert("로그인이 완료");
			location.href = "/blog";
		}).fail(function(error){
			alert(JSON.stringify(error));
		});  
	}
}
index.init();