package algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Virus {
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int t = sc.nextInt();
		List<List<Integer>> ad = new ArrayList<>();
		for(int i = 0 ; i <= n ; i++){
			ad.add(new ArrayList<>());
		}
		for(int i = 0 ; i< t; i++){
			int x = sc.nextInt();
			int y = sc.nextInt();
			ad.get(x).add(y);
			ad.get(y).add(x);
		}
		System.out.println(bfs(n, ad));
	}
	public static int bfs(int n, List<List<Integer>> ad){
		boolean isVisit[] = new boolean[n + 1];
		Queue<Integer> q = new LinkedList<>();
		int count = 0;
		q.offer(1);
		isVisit[1] = true;
		while(!q.isEmpty()){
			int nowNum = q.poll();
			for(int nextNum : ad.get(nowNum)){
				if(!isVisit[nextNum]){
					q.offer(nextNum);
					isVisit[nextNum] = true;
					count++;
				}		
			}
		}
		return count;
	}
}
