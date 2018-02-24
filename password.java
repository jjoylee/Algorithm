package algorithm;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
public class password {
	public static ArrayList chars;
	public static ArrayList<StringBuilder> results;
	public static int length;
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		length = sc.nextInt();
		int times = sc.nextInt();
		chars = new ArrayList();
		results = new ArrayList();
		List<List<Integer>> graph = new ArrayList<>();
		for(int i = 0 ; i < times; i++){
			chars.add(sc.next().charAt(0));
		}
		chars.sort(new comp());
		for(int i = 0 ; i < times; i ++){
			graph.add(new ArrayList<>());
			for(int j = 0 ; j < times; j++){
				if(j > i) graph.get(i).add(j);
			}
		}
		
		for(int i = 0; i < times - length+1; i++){
			bfs(i,graph, new StringBuilder());
		}
		
		for(StringBuilder result : results){
				System.out.println(result);
		}
	}
	public static int Consonanats(StringBuilder str){
		int count = 0;
		if(str.indexOf("a") != -1) count++;
		if(str.indexOf("e") != -1) count ++;
		if(str.indexOf("i")!= -1) count++;
		if(str.indexOf("u") != -1) count++;
		if( str.indexOf("o") != -1) count++;
		return count;
	}
	public static void bfs(int idx,List<List<Integer>> graph, StringBuilder before){
		Queue<Integer> q = new LinkedList<>();
		StringBuilder a = new StringBuilder();
		a.append(before);
		a.append(chars.get(idx));
		q.offer(idx);
		while(!q.isEmpty()){
			int nowNum = q.poll();
			
			if(!graph.get(nowNum).isEmpty()){
				for(int nextNum : graph.get(nowNum)){
						bfs(nextNum,graph, a);
				}
			}
		}
		if(a.length() == length && length - Consonanats(a) >= 2 && Consonanats(a) != 0)results.add(a);
	}
}
class comp implements Comparator<Character>{

	@Override
	public int compare(Character o1, Character o2) {
		// TODO Auto-generated method stub
		return o1.compareTo(o2);
	}
}

