<%--
  Created by IntelliJ IDEA.
  User: DreamKite
  Date: 2019/5/31
  Time: 0:48
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String appPath = request.getContextPath(); %>
<html>
<head>
    <title>Paper列表</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 引入 Bootstrap -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    基于SSM框架的管理系统：简单实现增、删、改、查。
                </h1>
            </div>
        </div>
    </div>

    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    <small>论文列表 —— 显示所有论文</small>
                </h1>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-4 column">
            <a class="btn btn-primary" href="${path}/paper/toAddPaper">新增</a>
        </div>
    </div>
    <div class="row clearfix">
        <div class="col-md-12 column">
            <table class="table table-hover table-striped">
                <thead>
                <tr>
                    <th>论文编号</th>
                    <th>论文名字</th>
                    <th>论文数量</th>
                    <th>论文详情</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="paper" items="${requestScope.get('list')}" varStatus="status">
                    <tr>
                        <td>${paper.paperId}</td>
                        <td>${paper.paperName}</td>
                        <td>${paper.paperNum}</td>
                        <td>${paper.paperDetail}</td>
                        <td>
                            <a href="${path}/paper/toUpdatePaper?id=${paper.paperId}">更改</a> |
                            <a href="<%=appPath%>/paper/del/${paper.paperId}">删除</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <div class="paging">
        <div class="aleft" style="display: inline-block; float: left;">
            <span>共"${requestScope.get('list').size()}"行，</span> </span><span><select
                onchange="location.href=changeUrlArg(changeUrlArg(location.href,'${pagerItem.paramPageSize }', this.options[this.options.selectedIndex].value),'${pagerItem.paramPageNum }', '1');">
				<c:forEach var="i" begin="1" end="100" step="1">
                    <option value="${i }" ${pagerItem.pageSize==i?"selected":""}>${i }</option>
                </c:forEach>
		</select>行/页</span>
        </div>
        <div class="aright" style="display: inline-block; float: right;">
            <c:set value="${pagerItem.pageNum == 1}" var="isFisrtPage"
                   scope="request"/>
            <c:set value="${pagerItem.pageNum == pagerItem.pageCount}"
                   var="isLastPage" scope="request"/>
            <c:choose>
                <c:when test="${isFisrtPage}">
                    &nbsp;<span style="color:#000000">首页</span>
                    &nbsp;<span style="color:#000000">上一页</span>
                </c:when>
                <c:otherwise>
                    &nbsp;<span><a href="${pagerItem.firstPageUrl }">首页</a></span>
                    &nbsp;<span><a href="${pagerItem.prevPageUrl }">上一页</a></span>
                </c:otherwise>
            </c:choose>

            <c:choose>
                <c:when test="${isLastPage}">
                    &nbsp;<span style="color:#000000">下一页 </span>
                    &nbsp;<span style="color:#000000">尾页</span>
                </c:when>
                <c:otherwise>
                    &nbsp;<span><a href="${pagerItem.nextPageUrl }">下一页</a></span>
                    &nbsp;<span><a href="${pagerItem.lastPageUrl }">尾页</a></span>
                </c:otherwise>
            </c:choose>


            <span>&nbsp;&nbsp;&nbsp;&nbsp;跳到第 <select
                    onchange="location.href=changeUrlArg(location.href,'${pagerItem.paramPageNum }', this.options[this.options.selectedIndex].value);">

				<c:forEach var="i" begin="1" end="${pagerItem.pageCount }">

                    <option value="${i }"${pagerItem.pageNum==i?"selected":""}>${i }</option>
                </c:forEach>

		</select>页 /共${pagerItem.pageCount }页
        </div>
    </div>
</div>

