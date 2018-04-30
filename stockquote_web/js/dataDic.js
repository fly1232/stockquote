/**
 * 数据字典
 */
//运行状态 1-正在运作；2-准备就绪；3-运行成功；4-运行失败; 5-程序关闭
var dic_taskStatus=[{"key":"1","value":"运行中..."},
					  {"key":"2","value":"准备就绪"},
					  {"key":"3","value":"运行成功"},
					  {"key":"4","value":"运行失败"},
					  {"key":"5","value":"程序关闭"}];
//应用状态 1-活跃; 2: 不活跃
var dic_appStatus=[{"key":"1","value":"活跃"},	{"key":"2","value":"关闭"}];
//任务组
var dic_taskGroups=[{"key":"1","value":"数据同步"}, {"key":"2","value":"数据抓取"},{"key":"3","value":"数据清洗"},{"key":"4","value":"数据归并"},{"key":"5","value":"监视任务"}];
//日志级别
var dic_logLevels=[{"key":"1","value":"信息"}, {"key":"2","value":"警告"},{"key":"3","value":"错误"},{"key":"4","value":"恢复"}];

//数据源组
var dic_dataSourceGroups=[{"key":"1","value":"生产数据"}, {"key":"2","value":"抓取数据"},{"key":"3","value":"清洗数据"},{"key":"4","value":"归并数据"},{"key":"5","value":"其他数据"}];

//监控目标组
var dic_monitorTargetGroups=[{"key":"1","value":"第三方接口"}, {"key":"2","value":"千寻接口"}];

//监控目标对象的状态集合
var dic_monitorTargetStatus = [{"key":"2","value":"准备就绪"}, {"key":"3","value":"正常"}, {"key":"4","value":"失败"}, {"key":"5","value":"程序关闭"}];

//告警方向
var dic_alarmDirections =[{"key":"1","value":"告警通知"}, {"key":"2","value":"告警恢复"}];


//jquery方法   
//domId表示select控件的id \
//data表示数据字典的数据
//value 可不传，表示默认选中的值
jQuery.forEachDataSelect=function(domId,data,value,isAll){
	var str='';
	if(isAll==null||isAll==true){
		str +='<option value="">全部</option>';
	}
	
	$.each(data,function(i,entry){
		if(entry.key==value){
			str += '<option value="'+entry.key+'" selected="selected">'+entry.value+'</option>';
		}else{
			str += '<option value="'+entry.key+'">'+entry.value+'</option>';
		}
	});
    $('#'+domId).html(str);
};

/**
 * 翻译数据字典常量
 * 
 * 例如：$dicGetVal('dic_curCode','156'),返回‘人民币’
 * @param data字典名
 * @param key 字典关键字
 * @returns value 字典值
 * 
 */
jQuery.dicGetVal=function(data,key){
	var val=key;
	$.each(data,function(i,entry){
		if(entry.key==key){
			val= entry.value;
			return
		}
	});
	return val;
};


