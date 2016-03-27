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

  <script src="${root}/static/highstock/js/highstock.js"></script>
  <script src="${root}/static/highstock/js/modules/exporting.js"></script>

  <script type="text/javascript">

    $(function(){

      $( ".myDatepicker" ).datepicker({defaultDate: "+1w", changeMonth: true, numberOfMonths: 1, dateFormat: "yy-mm-dd", showButtonPanel: true});

      /*参数传递*/
      $("#stockCode").val('${stockCode}');

      /*查询*/
      showKLine = function(){
        var stockCode = $("#stockCode").val();
        if ((stockCode==undefined||stockCode=="")){
          window.alert("股票代码不能为空!");
          return;
        }

        //获取数据并画图
        $.ajax({
          url: "${root}/dayQuote/queryQuoteData.do",
          type: 'POST',
          data: "stockCode=" + stockCode,
          dataType: 'json',
          success: function (result) {
            var data=result.list;
            // split the data set into ohlc and volume
            var ohlc = [],
              volume = [],
              dataLength = data.length,
              // set the allowed units for data grouping
              groupingUnits =
                      [[
                        'week',                         // unit name
                        [1]                             // allowed multiples
                      ], [
                        'month',
                        [1, 2, 3, 4, 6]
                      ]];


            for (i=0; i < dataLength; i += 1) {
              var dateStr = data[i]["tradeDate"];
              var dt = new Date(dateStr.substr(0, 4) + "/" + dateStr.substr(4,2) + "/" + dateStr.substr(6,2) + " 00:00:00");
              var tradeDate = dt.getTime();
              ohlc.push([
                tradeDate, // the date
                data[i]["openPrice"], // open
                data[i]["highPrice"], // high
                data[i]["lowPrice"], // low
                data[i]["closePrice"] // close
              ]);

              volume.push([
                tradeDate, // the date
                data[i]["tradeQty"] // the volume
              ]);
            }

            // create the chart
            $('#container').highcharts('StockChart', {

              rangeSelector: {
                selected: 1
              },

              title: {
                text: stockCode + ' Historical'
              },

              yAxis: [{
                labels: {
                  align: 'right',
                  x: -3
                },
                title: {
                  text: 'OHLC'
                },
                height: '60%',
                lineWidth: 2
              }, {
                labels: {
                  align: 'right',
                  x: -3
                },
                title: {
                  text: 'Volume'
                },
                top: '65%',
                height: '35%',
                offset: 0,
                lineWidth: 2
              }],

              series: [{
                type: 'candlestick',
                name: stockCode,
                data: ohlc,
                dataGrouping: {
                  units: groupingUnits
                }
              }, {
                type: 'column',
                name: 'Volume',
                data: volume,
                yAxis: 1,
                dataGrouping: {
                  units: groupingUnits
                }
              }]
            });

          },
          error: function (data) {
            window.alert(data);
          }
        });
      };

      /**重置*/
      $("#cleanBtn").click(function(){
        $("#stockCode").val("");
      });
    });

  </script>
</head>

<body>
<div class="box col-md-12" id = "contentDiv">
  <div class="box-inner">
    <div class="box-header well">
      <h2><i class="glyphicon glyphicon-user"></i>K线图</h2>
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
        <form id="sform" >
          <table border="0" cellpadding="2" cellspacing="1" style="width:100%">
            <tr class="row">
              <td class="col-md-2" style="text-align: right">股票代码：</td>
              <td class="col-md-4">
                <input name="stockCode" type="text" id="stockCode" class="text"  value="${stockCode}"/>
              </td>

              <td class="col-md-2" style="text-align: right">
                <input class="right-button08" type="button" id="queryBtn" style="margin-left:20px"  value="查询" onclick="showKLine()" />
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

      <div class="row col-md-12">
        <div class="box-header well" data-original-title="">
          <h2><i class="glyphicon glyphicon-edit"></i>K线图</h2>

          <div class="box-icon">
            <a href="#" class="btn btn-minimize btn-round btn-default"><i
                    class="glyphicon glyphicon-chevron-up"></i></a>
          </div>
        </div>

        <div id="container" style="height: 400px; min-width: 310px">

        </div>
      </div>
  </div>
</div>
</body>
</html>
