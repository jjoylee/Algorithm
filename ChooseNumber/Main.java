package algorithm.ChooseNumber;

import java.util.Scanner;

// 코드그라운드 숫자 골라내기
public class Main {
	static int N;
	static int Answer;
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int test_case = 0; test_case < T; test_case++) {
			Answer = 0;
			N = sc.nextInt();
			for(int n = 0 ; n < N ; n++){
				Answer = Answer ^ sc.nextInt();
			}
			System.out.println("Case #"+(test_case+1));
			System.out.println(Answer);
		}
	}
}
