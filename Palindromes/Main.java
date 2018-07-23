package algorithm.Palindromes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//https://www.acmicpc.net/problem/13235
public class Main {
	static String vocabulary;
	public static void main(String[] args) throws IOException{
		getVocabulary();
		System.out.println(isPalindrome());
	}
	
	private static boolean isPalindrome() {
		if(vocabulary.length() == 1) return true;
		int start = 0;
		int end = vocabulary.length() - 1;
		while(start < end){
			if(vocabulary.charAt(start++) != vocabulary.charAt(end--)){
				return false;
			}
		}
		return true;
	}

	private static void getVocabulary() throws IOException {
		BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
		vocabulary = br.readLine();
		br.close();
	}
}