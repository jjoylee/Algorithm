package algorithm.Fibonacci;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

//https://www.acmicpc.net/problem/1003
public class Main {
	static int[][] fibonacciMap = new int[2][41];
	public static void main(String[] args) throws NumberFormatException, IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int TEST_CASE = Integer.parseInt(br.readLine());
		defaultMapSetting();
		for(int test = 0 ; test < TEST_CASE ; test++){
			int TARGET = Integer.parseInt(br.readLine());
			if(fibonacciMap[0][TARGET] == 0 && fibonacciMap[1][TARGET] == 0)
				fillMap(TARGET);
			bw.write(fibonacciMap[0][TARGET] + " " + fibonacciMap[1][TARGET]);
			bw.newLine();
		}
		bw.flush();
		bw.close();
		br.close();
	}
	private static void fillMap(int target) {
		for(int row = 2 ; row < target+1 ; row++){
			if(fibonacciMap[0][row] != 0 || fibonacciMap[1][row] != 0) continue;
			fibonacciMap[0][row] = fibonacciMap[0][row-1] + fibonacciMap[0][row-2];
			fibonacciMap[1][row] = fibonacciMap[1][row-1] + fibonacciMap[1][row-2];
		}
	}
	private static void defaultMapSetting() {
		fibonacciMap[0][0] = 1; // fibonacci(0) - 0 È£Ãâ È½¼ö
		fibonacciMap[1][0] = 0; // fibonacci(0) - 1È£Ãâ È½¼ö
		fibonacciMap[0][1] = 0; // fibonacci(1) - 0 È£Ãâ È½¼ö
		fibonacciMap[1][1] = 1; // fibonacci(1) - 1 È£Ãâ È½¼ö
	}
}
