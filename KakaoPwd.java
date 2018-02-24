package algorithm;

import java.util.Scanner;

public class KakaoPwd {
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.nextLine());
		String arr = sc.nextLine().replaceAll(" ", "");
		String[] arr1 = arr.substring(1, arr.length()-1).split(",");
		arr = sc.nextLine().replaceAll(" ", "");
		String[] arr2 = arr.substring(1, arr.length()-1).split(",");
		String[] results = new String[n];
		for(int i = 0 ; i < n ; i++){
			results[i] = String.valueOf((Integer.toBinaryString(Integer.parseInt(arr1[i])|Integer.parseInt(arr2[i]))));
		}
		setResults(results, n);
		printResults(results);
	}

	private static void printResults(String[] results) {
		System.out.print("[");
		for(int i = 0 ; i < results.length ; i++){
			System.out.print("\"" + results[i] + "\"");
			if(i != results.length - 1) System.out.print(",");
		}
		System.out.print("]");
	}

	private static void setResults(String[] results, int n) {
		StringBuilder sb = new StringBuilder();
		for( int i = 0 ; i < n ; i++){
			if(results[i].length() != n){
				for(int j = 0 ; j < n; j++){
					if(j < n - results[i].length())	sb.append(" ");
				}
				sb.append(results[i]);
				results[i] = sb.toString();
			}
			results[i] = results[i].replace('0',' ');
			results[i] = results[i].replace('1','#');
			sb = new StringBuilder();
		}
	}
}
