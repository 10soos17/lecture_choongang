package study19_projMMS.member.action;

import study19_projMMS.member.svc.MemberListService;

//7-2 회원등록 보기 요청 처리하는 Action 클래스 구현
public class MemberListAction implements Action {
	
	@Override
	public void execute() throws Exception {

		MemberListService mls = new MemberListService();
		mls.getMemberList();
		
	}

}
