package algorithm.StudyTest;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int Answer;
	public static void main(String args[]) throws Exception	{
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int test_case = 0; test_case < T; test_case++) {
			int N = sc.nextInt();
			int K = sc.nextInt();
			Answer = 0;
			int[] SCORES = new int[N];
			for(int n = 0 ; n < N ; n++){
				SCORES[n] = sc.nextInt();
			}
			Arrays.sort(SCORES);
			for(int k = 0; k < K; k++){
				Answer += SCORES[N-1-k];
			}
			System.out.println("Case #"+(test_case+1));
			System.out.println(Answer);
		}
	}
}
