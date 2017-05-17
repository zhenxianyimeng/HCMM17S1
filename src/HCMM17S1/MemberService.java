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
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
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
		}
		return null;
	}
	
	private void addMember(List<String> list){
		for(String str : list)
			System.out.println(str.trim());
		System.out.println("++++++++++++++");
	}
}
