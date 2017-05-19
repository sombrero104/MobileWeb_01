<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div style="padding-top: 20px; padding-bottom: 5px;">* currentPage: ${currentPage} / pageSize: ${pageSize}</div>
<c:forEach var="goods" items="${goodsList}" varStatus="status">
	<div id="g_${goods.goodsId}">
		<table class="goods_table">
			<thead>
				<tr>
					<th style="width:130px;"></th>
					<th style="width:500px;"></th>
					<th style="width:120px;"></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<c:choose>
						<c:when test="${goods.goodsId == '3103141'}">
							<td style="background-color: #D04C3E;"><a href="javascript:goGoodsDetail('${goods.goodsId}')">${goods.goodsId}</a></td>
						</c:when>
						<c:otherwise>
							<td style="background-color: #CBCBCB;"><a href="javascript:goGoodsDetail('${goods.goodsId}')">${goods.goodsId}</a></td>
						</c:otherwise>
					</c:choose>
					<td style="background-color: #ECECEC;"><a href="javascript:goGoodsDetail('${goods.goodsId}')">${goods.goodsName}</a></td>
					<td style="background-color: #DBDBDB;"><a href="javascript:goGoodsDetail('${goods.goodsId}')">${goods.goodsPrice}</a></td>
				</tr>
			</tbody>
		</table>
	</div>	
</c:forEach>