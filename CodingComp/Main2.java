package algorithm.CodingComp;

import java.util.Arrays;
import java.util.Scanner;

public class Main2 {
	static int Answer;
	public static void main(String args[]) throws Exception	{
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int test_case = 0; test_case < T; test_case++) {
			int N = sc.nextInt();
			int[] totalScores = new int[N];
			int[] next = new int[N];
			Answer = 0;
			for(int n = 0 ; n < N ; n++){
				totalScores[n] = sc.nextInt();
			}
			
			Arrays.sort(totalScores);
			int max = 0;
			for(int n = 0 ; n < N ; n++){
				next[n] = totalScores[n] + N-n;
				max = Math.max(next[n], max);
			}
			
			for(int n = 0 ; n < N ; n++){
				if(totalScores[n] + N >= max)
					Answer++;
			}
			
			System.out.println("Case #"+(test_case+1));
			System.out.println(Answer);
		}
	}
}
