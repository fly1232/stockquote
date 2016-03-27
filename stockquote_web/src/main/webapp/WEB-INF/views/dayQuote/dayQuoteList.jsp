<%@ page language="java" import="java.text.SimpleDateFormat" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/base.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/WEB-INF/tld/JSTLFunction.tld" prefix="dic" %>
<%@ include file="/WEB-INF/dic/dataDic.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <title>日行情历史数据查询</title>

  <link href="${root}/static/charisma/bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet" id="bs-css">

  <link href="${root}/static/charisma/css/charisma-app.css" rel="stylesheet">
  <link href='${root}/static/charisma/bower_components/fullcalendar/dist/fullcalendar.css' rel='stylesheet'>
  <link href='${root}/static/charisma/bower_components/fullcalendar/dist/fullcalendar.print.css' rel='stylesheet' media='print'>
  <link href='${root}/static/charisma/bower_components/chosen/chosen.min.css' rel='stylesheet'>
  <link href='${root}/static/charisma/bower_components/colorbox/example3/colorbox.css' rel='stylesheet'>
  <link href='${root}/static/charisma/bower_components/responsive-tables/responsive-tables.css' rel='stylesheet'>
  <link href='${root}/static/charisma/bower_components/bootstrap-tour/build/css/bootstrap-tour.min.css' rel='stylesheet'>
  <link href='${root}/static/charisma/css/jquery.noty.css' rel='stylesheet'>
  <link href='${root}/static/charisma/css/noty_theme_default.css' rel='stylesheet'>
  <link href='${root}/static/charisma/css/elfinder.min.css' rel='stylesheet'>
  <link href='${root}/static/charisma/css/elfinder.theme.css' rel='stylesheet'>
  <link href='${root}/static/charisma/css/jquery.iphone.toggle.css' rel='stylesheet'>
  <link href='${root}/static/charisma/css/uploadify.css' rel='stylesheet'>
  <link href='${root}/static/charisma/css/animate.min.css' rel='stylesheet'>
  <!-- end: CSS -->

  <!-- start: JavaScript-->
  <script src="${root}/static/charisma/bower_components/jquery/jquery.min.js"></script>

  <script src="${root}/static/owner/js/base.js"></script>

  <script src="${root}/static/charisma/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

  <!-- library for cookie management -->
  <script src="${root}/static/charisma/js/jquery.cookie.js"></script>
  <!-- calender plugin -->
  <script src='${root}/static/charisma/bower_components/moment/min/moment.min.js'></script>
  <script src='${root}/static/charisma/bower_components/fullcalendar/dist/fullcalendar.min.js'></script>
  <!-- data table plugin -->
  <script src='${root}/static/charisma/js/jquery.dataTables.min.js'></script>

  <!-- select or dropdown enhancer -->
  <script src="${root}/static/charisma/bower_components/chosen/chosen.jquery.min.js"></script>
  <!-- plugin for gallery image view -->
  <script src="${root}/static/charisma/bower_components/colorbox/jquery.colorbox-min.js"></script>
  <!-- notification plugin -->
  <script src="${root}/static/charisma/js/jquery.noty.js"></script>
  <!-- library for making tables responsive -->
  <script src="${root}/static/charisma/bower_components/responsive-tables/responsive-tables.js"></script>
  <!-- tour plugin -->
  <script src="${root}/static/charisma/bower_components/bootstrap-tour/build/js/bootstrap-tour.min.js"></script>
  <!-- star rating plugin -->
  <script src="${root}/static/charisma/js/jquery.raty.min.js"></script>
  <!-- for iOS style toggle switch -->
  <script src="${root}/static/charisma/js/jquery.iphone.toggle.js"></script>
  <!-- autogrowing textarea plugin -->
  <script src="${root}/static/charisma/js/jquery.autogrow-textarea.js"></script>
  <!-- multiple file upload plugin -->
  <script src="${root}/static/charisma/js/jquery.uploadify-3.1.min.js"></script>
  <!-- history.js for cross-browser state change on ajax -->
  <script src="${root}/static/charisma/js/jquery.history.js"></script>
  <!-- application script for Charisma demo -->
  <script src="${root}/static/charisma/js/charisma.js"></script>

  <link href="${root}/css/jquery-ui.css" rel="stylesheet" type="text/css" />
  <link href="${root}/css/bigpage.css" rel="stylesheet" type="text/css" />
  <script type="text/javascript" src="${root}/js/jquery-ui.js"></script>
  <script type="text/javascript" src="${root}/js/jquery-openwindow.js"></script>
  <script type="text/javascript" src="${root}/js/dataDic.js"></script>

  <script type="text/javascript">

    $(function(){

      $( ".myDatepicker" ).datepicker({defaultDate: "+1w", changeMonth: true, numberOfMonths: 1, dateFormat: "yy-mm-dd", showButtonPanel: true});

      /*分页*/
      $("#bigpage").update_page({"total_page":"${totalPages}", "current_page":"${pageNum}", callbackfunc:doQuerySubmit});

      /*参数传递*/
      $("#stockCode").val('${paramVo.stockCode}');

      /*查询*/
      $("#queryBtn").click(function(){
        var stockCode = $("#stockCode").val();
        if ((stockCode==undefined||stockCode=="")){
          window.alert("股票代码不能为空!");
          return;
        }
        doQuerySubmit(1);
      });

      /**重置*/
      $("#cleanBtn").click(function(){
        $("#stockCode").val("");
      });
    });

    /*分页查询*/
    function doQuerySubmit(page){
      $("#pageNum").val(page*1);
      $("#sform").submit();
    }
  </script>
</head>

<body>
<div class="box col-md-12" id = "contentDiv">
  <div class="box-inner">
    <div class="box-header well">
      <h2><i class="glyphicon glyphicon-user"></i> 日行情历史数据查询</h2>
    </div>

    <div class="row col-md-12">
      <%--<div class="box-header well" data-original-title="">--%>
      <%--<h2><i class="glyphicon glyphicon-edit"></i> 查询条件</h2>--%>

      <%--<div class="box-icon">--%>
      <%--<a href="#" class="btn btn-minimize btn-round btn-default"><i--%>
      <%--class="glyphicon glyphicon-chevron-up"></i></a>--%>
      <%--</div>--%>
      <%--</div>--%>
      <div class="box-content">
        <form action="${root}/dayQuote/listQuoteView.do"  method="post"  id="sform" >
          <table border="0" cellpadding="2" cellspacing="1" style="width:100%">
            <tr class="row">
              <td class="col-md-2" style="text-align: right">股票代码：</td>
              <td class="col-md-4">
                <input name="stockCode" type="text" id="stockCode" class="text"  value="${paramVo.stockCode}"/>
              </td>

              <td class="col-md-2" style="text-align: right">
                <input class="right-button08" type="button" id="queryBtn" style="margin-left:20px"  value="查询"  />
                <input class="right-button08" type="reset" id="cleanBtn" style="margin-left:20px" value="重置"  />
              </td>
              <td class="col-md-4">
                <input id="pageNum" name="pageNum" type="hidden" value="1"/>
                <input id="pageSize"  name="pageSize" type="hidden" value="10" />
              </td>
            </tr>
          </table>
        </form>
      </div>
    </div>

    <c:if test="${list != null }">
      <div class="row col-md-12">
        <div class="box-header well" data-original-title="">
          <h2><i class="glyphicon glyphicon-edit"></i> 查询结果</h2>

          <div class="box-icon">
            <a href="#" class="btn btn-minimize btn-round btn-default"><i
                    class="glyphicon glyphicon-chevron-up"></i></a>
          </div>
        </div>

        <div class="box-content">
          <table class="table table-bordered table-striped table-hover">
            <thead><tr>
              <th style="text-align: center">日期</th>
              <th style="text-align: center">开盘价</th>
              <th style="text-align: center">最高价</th>
              <th style="text-align: center">最低价</th>
              <th style="text-align: center">收盘价</th>
              <th style="text-align: center">成交量</th>
              <th style="text-align: center">成交金额</th>
            </tr></thead>

            <tbody id="dataGrid">
            <c:choose>
              <c:when test="${fn:length(list)>0 }">
                <c:forEach items="${list}" var="vo">
                  <tr align="center">
                    <td>${vo.tradeDate}</td>
                    <td style="text-align: right"><fmt:formatNumber type="number" value="${vo.openPrice}" minFractionDigits="2" maxFractionDigits="2"/></td>
                    <td style="text-align: right"><fmt:formatNumber type="number" value="${vo.highPrice}" minFractionDigits="2" maxFractionDigits="2"/></td>
                    <td style="text-align: right"><fmt:formatNumber type="number" value="${vo.lowPrice}" minFractionDigits="2" maxFractionDigits="2"/></td>
                    <td style="text-align: right"><fmt:formatNumber type="number" value="${vo.closePrice}" minFractionDigits="2" maxFractionDigits="2"/></td>
                    <td style="text-align: right"><fmt:formatNumber type="number" value="${vo.tradeQty}" minFractionDigits="2" maxFractionDigits="2"/></td>
                    <td style="text-align: right"><fmt:formatNumber type="number" value="${vo.tradeMoney}" minFractionDigits="2" maxFractionDigits="2"/></td>
                  </tr>
                </c:forEach>
              </c:when>
              <c:otherwise>
                <tr>
                  <td colspan="25" class="tab_w_c">查无数据</td>
                </tr>
              </c:otherwise>
            </c:choose>
            </tbody>
          </table>
          <c:if test="${fn:length(list)>0 }">
            <table width="95%" border="0" cellpadding="0" cellspacing="0" align="center" class="newfont03">
              <tr>
                <td class="tab_w_r">
                  <div id="bigpage" class="bigpage"></div></td>
              </tr>
            </table>
          </c:if>
        </div>
      </div>
    </c:if>
  </div>
</div>
</body>
</html>
