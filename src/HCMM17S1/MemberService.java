package HCMM17S1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class MemberService {
	
	private List<Member> members = new ArrayList<>();

	public List<Member> readMembers(String filePath){
		members.clear();
		BufferedReader reader = null;
		List<String> list = null;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
			String line = new String();
			list = new ArrayList<>();
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
		if(!list.isEmpty()){
			addMember(list);
		}
		list.clear();
		return members;
	}
	
	private void addMember(List<String> list){
		Member member = new Member();
		for(String str : list){
			if(str.startsWith("name"))
				member.setName(str.replace("name ", ""));
			else if(str.startsWith("birthday")){
				String[] birs = str.replace("birthday ", "").replace("-", "/").split("/");
				String bir = new String();
				if(birs[0].length() < 2)
					bir =  bir + "0";
				bir += birs[0]+"/";
				if(birs[1].length() < 2)
					bir = bir + "0";
				bir += birs[1] + "/";
				bir += birs[2];
				member.setBirthday(bir);
			}
			else if(str.startsWith("mobile"))
				member.setMobile(str.replace("mobile ", ""));
			else if(str.startsWith("pass"))
				member.setPass(str.replace("pass ", ""));
			else if(str.startsWith("fee")){
				String fee = str.replace("fee ", "");
				if(fee.startsWith("$"))
					fee = fee.substring(1);
				member.setFree("$"+new DecimalFormat("#0.00").format(Double.parseDouble(fee)));
			}
			else if(str.startsWith("address"))
				member.setAddress(str.replace("address ", ""));
			else if(str.startsWith("email"))
				member.setEmail(str.replace("email ", ""));
			else {
				member.setAddress(member.getAddress()+str.trim());
			}
				
		}
		members.add(member);
	}
	
	public void printMembers(){
		for(Member member : members)
			System.out.println(member);
	}
}
