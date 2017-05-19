package HCMM17S1;

import java.util.List;

public class HCMM {
	public static void main(String[] args) {
		try {
			String membersFile = args[0];
			String instructionFile = args[1];
			String resultFile = args[2];
			String reportFile = args[3];
			MemberService memberService = new MemberService();
			List<Member> members = memberService.readMembers(membersFile);
			memberService.printMembers();
			
		} catch (Exception e) {
			System.out.println("Input command is not correct");
		}
	}
}
