package algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class KakaoDart {
	static int thisVal = 0;
	static int result = 0;
	static List<Integer> values = new ArrayList<>();
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		String eq = sc.nextLine();
		StringTokenizer st = new StringTokenizer(eq, "*,#,S,D,T", true);
		while(st.hasMoreTokens()){
			calc(st.nextToken());
		}
		System.out.println(values.get(0)+values.get(1)+values.get(2));
	}

	private static void calc(String nextToken) {
		switch(nextToken){
			case "*":
				values.set(values.size()-1,values.get(values.size()-1)*2);
				if(values.size() >= 2)values.set(values.size()-2,values.get(values.size()-2)*2);
				break;
			case "#":
				values.set(values.size()-1, values.get(values.size()-1)*-1);
				break;
			case "S":
				break;
			case "D":
				values.set(values.size()-1, (int)Math.pow(values.get(values.size()-1), 2));
				break;
			case "T":
				values.set(values.size()-1, (int)Math.pow(values.get(values.size()-1), 3));
				break;
			default:
				values.add(Integer.parseInt(nextToken));
		}
	}
}
