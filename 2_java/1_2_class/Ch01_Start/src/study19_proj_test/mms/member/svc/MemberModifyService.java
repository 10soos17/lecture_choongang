package study19_proj_test.mms.member.svc;

import study19_proj_test.mms.member.vo.Member;
import study19_proj_test.mms.member.db.jdbcUtil;
import study19_proj_test.mms.member.dao.MemberDAO;
import study19_proj_test.mms.member.util.ConsoleUtil;

//8-3. 회원정보 수정 요청을 처리하는 Business Logic이 구현되는 Service 클래스 구현
public class MemberModifyService {

	public void modifyMember(Member m) {
		MemberDAO mdao = new MemberDAO(jdbcUtil.getConnection());

		int resCount = mdao.updateMember(m);
		ConsoleUtil cu = new ConsoleUtil();

		if (resCount != 0)
			cu.printModifySuccessMessage(m);
		else
			cu.printModifyFailMessage(m);
	}
}
