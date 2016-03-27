/**
 * 数据字典
 */
//运行状态 1-尚未运作；2-正在运作；3-运行成功；4-运行失败;
//var dic_workNodeStatus=[{"key":"1","value":"尚未运行"},
//						  {"key":"2","value":"正在运行"},
//						  {"key":"3","value":"运行成功"},
//						  {"key":"4","value":"运行失败"}];
var dic_workNodeStatus=[{"key":"NOTRUN","value":"尚未运行"},
	{"key":"RUNNING","value":"正在运行"},
	{"key":"SUCC","value":"运行成功"},
	{"key":"FAIL","value":"运行失败"}];

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


