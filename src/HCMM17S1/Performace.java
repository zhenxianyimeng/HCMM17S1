package HCMM17S1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Performace {
	private List<Member> members;
	
	public void doPerformace(String instructionFile){
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(instructionFile)));
			String line = null;
			while((line = reader.readLine())!=null){
				if(line.startsWith("add"))
					add(line.replace("add ", ""));
				else if(line.startsWith("query"))
					query(line.replace("query ", ""));
				else if(line.startsWith("delete"))
					delete(line.replace("delete ", ""));
				else if(line.startsWith("sort"))
					sort(line.replace("start ", ""));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void add(String str){
		
	}
	
	private void delete(String str){
		
	}
	
	private void query(String str){
		
	}
	
	private void sort(String str){
		
	}
	
	public List<Member> getList() {
		return members;
	}

	public void setList(List<Member> members) {
		this.members = members;
	}
	
}
