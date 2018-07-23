package algorithm.ChooseNumber;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class Main2 {
	static int Answer;
	static int N;
	static Map<Integer,Integer> map = new HashMap<Integer,Integer>();
	public static void main(String args[]) throws Exception	{
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int test_case = 0; test_case < T; test_case++) {
			createNumberMap(sc);
			calcXOR();
			printResult(test_case);
		}
	}
	
	private static void printResult(int test_case) {
		System.out.println("Case #"+(test_case+1));
		System.out.println(Answer);
	}
	
	private static void calcXOR() {
		Iterator mapIterator = map.keySet().iterator();
		Answer = 0;
		while(mapIterator.hasNext()){
			int key = (int)mapIterator.next();
			if(map.get(key) % 2 == 1)
				Answer = Answer ^ key;
		}
	}
	
	private static void createNumberMap(Scanner sc) {
		N = sc.nextInt();
		for(int n = 0 ; n < N ; n++){
			int num = sc.nextInt();
			if(map.get(num) == null){
				map.put(num, 1);
				continue;
			}
			map.put(num, map.get(num) + 1);
		}
	}
}
