package algorithm.CodingComp;

import java.util.Arrays;
import java.util.Scanner;

//코드 그라운드 프로그래밍 경진대회
public class Main {
	static int Answer;
	static int max;
	static int[] totalScores;
	public static void main(String args[]) throws Exception	{
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int test_case = 0; test_case < T; test_case++) {
			int N = sc.nextInt();
			totalScores = new int[N];
			for(int n = 0 ; n < N ; n++){
				totalScores[n] = sc.nextInt();
			}
			
			Arrays.sort(totalScores);
			for(int n = 0 ; n < N ; n++){
				// n번째가 우승을 할 수 있는가?
				int max = totalScores[n] + N;
				Answer++;
				for(int j = n + 1, k = 1 ; j < N ; j++, k++){
					if(totalScores[j] + N - k > max) {
						Answer--;
						break;
					}
				}
			}
			System.out.println("Case #"+(test_case+1));
			System.out.println(Answer);
		}
	}
}
