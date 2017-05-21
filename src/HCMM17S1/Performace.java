package HCMM17S1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Performace {
	private List<Member> members;

	public void doPerformace(String instructionFile, String reportFile) {
		BufferedReader reader = null;
		FileWriter writer = null;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(instructionFile)));
			writer = new FileWriter(reportFile);
			String line = null;
			boolean isFirst = true;
			while ((line = reader.readLine()) != null) {
				if (line.startsWith("add"))
					add(line.replace("add ", ""));
				else if (line.startsWith("query")) {
					String str = query(line.replace("query ", ""));
					if(isFirst){
						str += "\n";
						isFirst = false;
					}
					writer.append(str);
					//writer.append("\n");
					writer.flush();
				} else if (line.startsWith("delete"))
					delete(line.replace("delete ", ""));
				else if (line.startsWith("sort"))
					sort(line.replace("start ", ""));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (reader != null)
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}

	private void add(String addStr) {
		String[] strs = addStr.split("; ");
		Member member = new Member();
		for(String str : strs){
			if(str.startsWith("name"))
				member.setName(str.replace("name ", ""));
			else if(str.startsWith("birthday"))
				member.setBirthday(str.replace("birthday ", ""));
			else if(str.startsWith("mobile"))
				member.setMobile(str.replace("mobile ", ""));
			else if(str.startsWith("pass"))
				member.setPass(str.replace("pass ", ""));
			else if(str.startsWith("fee")){
				String fee = str.replace("fee ", "");
				String[] temps = fee.split("\\.");
//				for(String s : strs)
//					System.out.println(s);
				if(temps.length == 1)
					fee = fee +".00";
				member.setFree(fee);
			}
			else if(str.startsWith("address"))
				member.setAddress(str.replace("address ", ""));
			else if(str.startsWith("email"))
				member.setEmail(str.replace("email ", ""));
			else {
				member.setAddress(member.getAddress()+str.trim());
			}
		}
		
		int i=0;
		for(i=0; i<members.size(); i++){
			Member m = members.get(i);
			if(m.getName().equals(member.getName()) && m.getMobile().equals(member.getMobile())){
				if(member.getName() != null)
					m.setName(member.getName());
				if(member.getBirthday() != null)
					m.setBirthday(member.getBirthday());
				if(member.getMobile() != null)
					m.setMobile(member.getMobile());
				if(member.getPass() != null)
					m.setPass(member.getPass());
				if(member.getFree() != null)
					m.setFree(member.getFree());
				if(member.getAddress() != null)
					m.setAddress(member.getAddress());
				if(member.getEmail() != null)
					m.setEmail(member.getEmail());
				
				break;
			}
		}
		if(i == members.size())
			members.add(member);

	}

	private void delete(String str) {

	}

	private String query(String str){
		String[] strs = str.split(" ");
		StringBuilder sb = new StringBuilder();
		if(strs[0].equals("pass")){
			String q = strs[1];
			List<Member> list = new ArrayList<>();
			for(Member m : members){
				if(m.getPass() != null && m.getPass().equals(q)){
					list.add(m);
				}
			}
			list.sort(new Comparator<Member>() {
				@Override
				public int compare(Member o1, Member o2) {
					if(o1.getName().equals(o2.getName())){
						return o1.getMobile().compareTo(o2.getMobile());
					}else{
						return o1.getName().compareTo(o2.getName());
					}
				}
				
			});
			sb.append("----query pass Gold----").append("\n");
			double total = 0;
			for(Member member : list){
				if(member.getName() != null)
					sb.append("name ").append(member.getName()).append("\n");
				if(member.getBirthday() != null)
					sb.append("birthday ").append(member.getBirthday()).append("\n");
				if(member.getMobile() != null)
					sb.append("mobile ").append(member.getMobile()).append("\n");
				if(member.getPass() != null)
					sb.append("pass ").append(member.getPass()).append("\n");
				if(member.getFree() != null){
					sb.append("fee ").append(member.getFree()).append("\n");
					total += Double.parseDouble(member.getFree().substring(1));
				}
				if(member.getAddress() != null)
					sb.append("address ").append(member.getAddress()).append("\n");
				if(member.getEmail() != null)
					sb.append("email ").append(member.getEmail()).append("\n");
				sb.append("\n");
			}
			
			sb.append("Total Fee: $").append(new DecimalFormat("#.00").format(total)).append("\n").append("-------------------------\n");
			
		}
		return sb.toString();
	}

	private void sort(String str) {

	}

	public List<Member> getList() {
		return members;
	}

	public void setList(List<Member> members) {
		this.members = members;
	}

}
