package srm234_div1;

import java.util.ArrayList;

public class BusinessTasks{
	public String getTask(String[] list, int n){
		ArrayList<String> alist = new ArrayList<String>();
		for(int cnt = 0; cnt < list.length; cnt++){
			alist.add(list[cnt]);
		}		
		
		int cnt = 0;
		while(alist.size()!=1){
			if(cnt == 0){
				int cycles = n / alist.size();
				n -= cycles * alist.size();
			}
			if(cnt == n-1){
				alist.remove(cnt);
			}else{
				cnt++;
			}
			if(cnt >= alist.size()){
				cnt = 0;
			}
		}
		return (String)alist.get(0);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BusinessTasks bt = new BusinessTasks();
		
		String[] list = {"a", "b", "c", "d"};
		int n = 2;
		String str = bt.getTask(list, n);
		System.out.println(str);
	}

}
