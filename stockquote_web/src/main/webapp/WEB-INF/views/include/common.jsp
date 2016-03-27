<%@ page import="javax.servlet.http.HttpServletRequest"%>
<%!
  public String getSubPath(HttpServletRequest request){
    String url = request.getRequestURI();
    String contextPath = request.getContextPath();
    if ((contextPath==null)||(contextPath.trim().equals(""))) {
      int idx2 = url.indexOf("/",1);
      if (idx2 >= 0){
        return url.substring(1, idx2);
      }
      else {
        return url.substring(1);
      }
    }
    else {
      int idx = url.indexOf(contextPath);
      if (idx >=0) {
        int idx2 = url.indexOf("/",idx+contextPath.length()+1);
        if (idx2 >= 0){
          return url.substring(idx+contextPath.length()+1, idx2);
        }
        else {
          return url.substring(idx+contextPath.length()+1);
        }
      }
    }
    return "";
  }
%>