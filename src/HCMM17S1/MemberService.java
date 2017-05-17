package HCMM17S1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MemberService {
	
	private List<Member> members = new ArrayList<>();

	public List<Member> readMembers(String filePath){
		members.clear();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
			String line = new String();
			List<String> list = new ArrayList<>();
			while ((line = reader.readLine()) != null){
				if(!line.equals("")){
					list.add(line);
				}else{
					if(!list.isEmpty()){
						addMember(list);
					}
					list.clear();
				}
			}
				
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(reader != null)
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return members;
	}
	
	private void addMember(List<String> list){
		Member member = new Member();
		for(String str : list){
			if(str.startsWith("name"))
				member.setName(str);
			else if(str.startsWith("birthday"))
				member.setBirthday(str);
			else if(str.startsWith("mobile"))
				member.setMobile(str);
			else if(str.startsWith("pass"))
				member.setPass(str);
			else if(str.startsWith("fee"))
				member.setFree(str);
			else if(str.startsWith("address"))
				member.setAddress(str);
			else if(str.startsWith("email"))
				member.setEmail(str);
			else {
				member.setAddress(member.getAddress()+str.trim());
			}
				
		}
		members.add(member);
	}
}
