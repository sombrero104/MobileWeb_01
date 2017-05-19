<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="/js/jquery-1.11.3.js"></script>
<script type="text/javascript">

	// 상품 상페 페이지 링크 이동
	function goGoodsDetail(goodId) {
		var url = "/goods/goodsDetail.do"; // 상품 상세 URL
		
		window.sessionStorage.setItem("goodId", goodId); // 현재 상품 아이디 위치 저장
		window.sessionStorage.setItem("pageSize", $("#pageSize").val()); // 현재 로드된 페이지 사이즈 저장
		window.sessionStorage.setItem("isBack", "Y"); // 상세페이지에서 뒤로가기 구분값 Y로 저장
		
		document.location.href = url; // 상세페이지 이동
	}
	
	// 상품 리스트 추가
	function addGoodsDiv(initYn) {
		
        $.ajax({ 
            url: "/goods/goodsDiv.do" // 추가될 상품 리스트
            , data: {currentPage:$("#currentPage").val(), pageSize:$("#pageSize").val()}
            , type: "POST" 
            , dataType: "html"
            , success: function(data) {
           		$("#goodsDiv").html(data); // 상품 리스트 붙임
            }
            , error: function(xhr, error, code) {}
        });
        
        if(initYn == "Y") { // 최초 상품 리스트 추가인 경우
        	$(document).ajaxComplete(moveScroll);
        }
        
	}
	
	// 이전 상품 아이디 위치로 이동
	function moveScroll() {
        if(window.sessionStorage.getItem("goodId")) { // 이전 상품 아이디 goodId가 저장되어 있는 경우
   			var goodId = window.sessionStorage.getItem("goodId"); // 이전 상품 아이디 가져오기
   			var tempGoodId = "#g_" + goodId; // 이전 상품 아이디를 element 이름으로 저장
   			$(tempGoodId)[0].scrollIntoView(); // 해당 상품 아이디 위치로 이동
        }
	}
	
	$(window).bind("pageshow", function (event) {
		if (event.originalEvent.persisted) {
			// 뒤로가기로 페이지 로드 시
			console.log("isBack: " + event.originalEvent.persisted);
		}
		else {
			// 새로운 페이지 로드 시
			console.log("new page!! :D");
		}
	});
	
	// 최초 실행
	$(function() {
		
		// 뒤로가기인 경우
		if(window.sessionStorage.getItem("isBack")) { // 뒤로가기 여부인 isBack 값이 있는 경우
			if(window.sessionStorage.getItem("isBack") == "Y") { // 뒤로가기인 경우				
				$("#pageSize").val(window.sessionStorage.getItem("pageSize")); // 이전 페이지 사이즈 저장
			}
		}
		
		addGoodsDiv("Y"); // 최초 상품 리스트 추가
		
        var scrollCurrentTop = 0; // 현재 scrollTop
     	$(window).scroll(function(){ // 스크롤바 스크롤 시 실행
     		if(($(window).scrollTop() >= $(document).height() - $(window).height())
     				&& ($(window).scrollTop() != scrollCurrentTop)) { // 하위로 스크롤하는 경우
     			scrollCurrentTop = $(window).scrollTop(); // 현재 scrollTop 저장
     			
     			$("#pageSize").val(parseInt($("#pageSize").val()) + 5); // pageSize 더하기
     			$("#displayCurrentPage").html("* currentPage: " + $("#currentPage").val() + " / pageSize: " + $("#pageSize").val()); // test
     			
     			addGoodsDiv("N"); // pageSize 더한 후 상품 리스트 추가
     		}
     	});
     	
	});
</script>
<style type="text/css">
	body { font-family: 맑은 고딕, Serif, "Times New Roman", arial, 바탕체; font-size: 12px; }
	.goods_table { border: 1px solid #D4D4FF; padding: 5px; margin: 5px; width: 750px; height: 150px; }
	.goods_table td { padding: 22px; }
</style>
</head>
<body>
	<%@ include file="../include/header.jsp" %>
	<form id="goodsForm" name="goodsForm">
		<input type="hidden" id="currentPage" name="currentPage" value="${currentPage}" />
		<input type="hidden" id="pageSize" name="pageSize" value="${pageSize}" />
	</form>

	<div style="text-align: center; padding-top: 30px; padding-bottom: 10px;">${test}</div>
	<div id="displayCurrentPage" style="text-align: center; padding-bottom: 20px;">* currentPage: ${currentPage} / pageSize: ${pageSize}</div>

	<!-- 상품 리스트 출력 start -->
	<div id="goodsDiv"></div>
	<!-- 상품 리스트 출력 end -->

</body>
</html>