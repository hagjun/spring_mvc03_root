package com.ict.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements AsyncHandlerInterceptor{

	// Controller가 호출되기 전에 실행된다.
	// 컨트롤러 이전에 처리해야 하는 전처리 작업이나 요청 정보를 가공하거나 추가하는 경우에 사용할 수 있다.
	// Controller로 가기전 구동됨
	// 결과는 true이면 Controller, 결과가 false이면 특정 JSP로 이동
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		// 로그인 체크를 해서 만약에 로그인이 안된 상태이면 value에 false 저장.
		
		// 만약 session이 삭제된 상태라면 새로운 session을 생성해 준다.
		// session이 삭제가 안된 상태라면 사용하고 있는 session을 그대로 전달해 준다.
		HttpSession session = request.getSession(true);
		Object obj =  session.getAttribute("loginChk");
		if(obj == null) { // 여기서 새로 만들어지면...null
			// 로그인 하지 않는 상태일 때
			request.getRequestDispatcher("/WEB-INF/views/member/login_error.jsp").forward(request, response);
			return false; // false 일때 특정 JSP로 이동
		} 
		
		return true; // true 일때 Controller로 이동
		
	}
	// Controller에서 리턴되어 뷰 리졸버로 가기전에 구동
//	@Override
//	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
//			ModelAndView modelAndView) throws Exception {
//
//		AsyncHandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
//	}
	
	// 뷰 리졸버가 뷰를 찾아서 보내고 나면 구동
	// 모든 뷰에서 최종 결과를 생성하는 일을 포함해 모든 작업이 완료된 후에 실행된다. ( View 렌더링 후)
//	@Override
//	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
//			throws Exception {
//		AsyncHandlerInterceptor.super.afterCompletion(request, response, handler, ex);
//	}
}
