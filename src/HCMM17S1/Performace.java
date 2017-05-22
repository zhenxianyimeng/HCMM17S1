package HCMM17S1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
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
					sort(line.replace("sort ", ""));
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
			if(str.startsWith("name")){
				if(!ValidateUtil.ValidateName(str.replace("name ", "")))
					return;
				member.setName(str.replace("name ", ""));
			}
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
				if(ValidateUtil.ValidateDate(bir))
					member.setBirthday(bir);
			}
			else if(str.startsWith("mobile")){
				if(!ValidateUtil.ValidateMobile(str.replace("mobile ", "")))
					return;
				member.setMobile(str.replace("mobile ", ""));
			}
			else if(str.startsWith("pass")){
				if(ValidateUtil.ValidatePass(str.replace("pass ", "")))
					member.setPass(str.replace("pass ", ""));
			}
			else if(str.startsWith("fee")){
				String fee = str.replace("fee ", "");
				if(fee.startsWith("$"))
					fee = fee.substring(1);
				member.setFree("$"+new DecimalFormat("#0.00").format(Double.parseDouble(fee)));
			
			}
			else if(str.startsWith("address"))
				member.setAddress(str.replace("address ", ""));
			else if(str.startsWith("email")){
				if(ValidateUtil.ValidateEmail(str.replace("email ", "")))
					member.setEmail(str.replace("email ", ""));
			}
			else {
				member.setAddress(member.getAddress()+str.trim());
			}
		}
		
		if(member.getAddress() != null){
			if(!ValidateUtil.ValidateAddress(member.getAddress()))
				member.setAddress(null);
		}
		
		int i=0;
		for(i=0; i<members.size(); i++){
			Member m = members.get(i);
			if(m.getName().equals(member.getName()) && m.getMobile().equals(member.getMobile())){
				if(member.getName() != null)
					m.setName(member.getName());
				if(member.getBirthday() != null){
					m.setBirthday(member.getBirthday());
				}
				if(member.getMobile() != null)
					m.setMobile(member.getMobile());
				if(member.getPass() != null)
					m.setPass(member.getPass());
				if(member.getFree() != null){
					m.setFree(member.getFree());
				}
				if(member.getAddress() != null)
					m.setAddress(member.getAddress());
				if(member.getEmail() != null)
					m.setEmail(member.getEmail());
				
				break;
			}
		}
		if(i >= members.size())
			members.add(member);

	}

	private void delete(String str) {
		String[] dels = str.split("; ");
		for(int i=0; i<members.size(); i++){
			Member m = members.get(i);
			if(m.getName().equals(dels[0]) && m.getMobile().equals(dels[1]))
				members.remove(i);
		}
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
			
			sb.append("Total Fee: $").append(new DecimalFormat("#.00").format(total)).append("\n").append("-------------------------\n\n");
			
		}else if(strs[0].equals("age")){
			sb.append("----query age fee----\n");
			sb.append("Total Club Member size: ").append(members.size()).append("\n");
			double one = 0;
			double two = 0;
			double three = 0;
			double four = 0;
			double other = 0;
			for(Member m : members){
				double fee = 0;
				if(m.getFree() != null)
					fee = Double.parseDouble(m.getFree().substring(1));
				if(m.getBirthday() == null){
					other += fee;
				}else{
					String[] bs = m.getBirthday().split("/");
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Date date = null;
					try {
						date = sdf.parse(bs[2]+"-"+bs[1]+"-"+bs[0]);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					int age = getAgeByBirthday(date);
					if(age<=8)
						one += fee;
					else if(age <= 18)
						two += fee;
					else if(age <= 65)
						three += fee;
					else
						four += fee;
				}
				
			}
			sb.append("Age based fee income distribution\n");
			sb.append("(0,8]: $").append(new DecimalFormat("#0.00").format(one)).append("\n");
			sb.append("(8,18]: $").append(new DecimalFormat("#0.00").format(two)).append("\n");
			sb.append("(18,65]: $").append(new DecimalFormat("#0.00").format(three)).append("\n");
			sb.append("(65,-): $").append(new DecimalFormat("#0.00").format(four)).append("\n");
			sb.append("Unknown: $").append(new DecimalFormat("#0.00").format(other)).append("\n");
			sb.append("---------------------\n");
		}
		return sb.toString();
	}

	private void sort(String str) {
		if(str.equals("ascending")){

			
			members.sort(new Comparator<Member>() {
				@Override
				public int compare(Member o1, Member o2) {
					if(o1.getName().equals(o2.getName())){
						return o1.getMobile().compareTo(o2.getMobile());
					}else{
						return o1.getName().compareTo(o2.getName());
					}
				}
				
			});
		}else if(str.equals("descending")){
			members.sort(new Comparator<Member>() {
				@Override
				public int compare(Member o1, Member o2) {
					if(o1.getName().equals(o2.getName())){
						return o2.getMobile().compareTo(o1.getMobile());
					}else{
						return o2.getName().compareTo(o1.getName());
					}
				}
				
			});
		}
	}


	private static int getAgeByBirthday(Date birthday) {
		Calendar cal = Calendar.getInstance();
		if (cal.before(birthday)) {
			throw new IllegalArgumentException(
					"The birthDay is before Now.It's unbelievable!");
		}
		int yearNow = cal.get(Calendar.YEAR);
		int monthNow = cal.get(Calendar.MONTH) + 1;
		int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
		cal.setTime(birthday);
		int yearBirth = cal.get(Calendar.YEAR);
		int monthBirth = cal.get(Calendar.MONTH) + 1;
		int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
		int age = yearNow - yearBirth;
		if (monthNow <= monthBirth) {
			if (monthNow == monthBirth) {
				// monthNow==monthBirth 
				if (dayOfMonthNow < dayOfMonthBirth) {
					age--;
				}
			} else {
				// monthNow>monthBirth 
				age--;
			}
		}
		return age;
	}

	
	public List<Member> getList() {
		return members;
	}

	public void setList(List<Member> members) {
		this.members = members;
	}
	
}
