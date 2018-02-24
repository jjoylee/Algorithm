package algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JComboBox.KeySelectionManager;

public class KakaoNews {
	public static void main(String args[]){
		
		String str1 = "E=M*C^2";
		String str2 = "e=m*c^2";
		
		Map<String, Integer> str1Words = new HashMap<>();
		Map<String, Integer> str2Words = new HashMap<>();
		
		setLists(str1Words, str1.toLowerCase());
		setLists(str2Words, str2.toLowerCase());
		
		int result = calc(str1Words, str2Words);
		System.out.println(result);
	}

	private static int calc(Map<String, Integer> str1Words, Map<String, Integer> str2Words) {
		Map<String,Integer> big;
		Map<String,Integer> small;
		if(str1Words.size() == 0 && str2Words.size() ==0) return 65536;
		
		if(str1Words.size() > str2Words.size()){
			big = str1Words;
			small = str2Words;
		}
		else{
			big = str2Words;
			small = str1Words;
		}
		int contains = 0;
		Iterator it =big.keySet().iterator();
		int smallTotal = 0;
		int bigTotal = 0;
		while(it.hasNext()){
			String key = it.next().toString();
			bigTotal += big.get(key);
			if(small.get(key)!= null)contains += small.get(key);
		}
		
		it = small.keySet().iterator();
		while(it.hasNext()){
			String key = it.next().toString();
			smallTotal += small.get(key);
		}
			

		int total = smallTotal + bigTotal - contains;
		int result = (int)Math.floor(((double)contains/total) * 65536) ;
		return result;
	}

	private static void setLists( Map<String, Integer> strWords, String str) {
		StringBuilder sb1 = new StringBuilder();
		StringBuilder sb2 = new StringBuilder();;
		for(int i = 0; i < str.length() ; i++){
			if(('a' <= str.charAt(i) && str.charAt(i) <= 'z')){
				if(sb1.toString().equals("") && sb2.toString().equals("")) {}
				else sb2.append(str.charAt(i));
				sb1.append(str.charAt(i));
				if(setStringBuilder(sb1, strWords)) sb1 = new StringBuilder();
				if(setStringBuilder(sb2, strWords)) sb2 = new StringBuilder();
			}
			if(!('a' <= str.charAt(i) && str.charAt(i) <= 'z')){
				sb1 = new StringBuilder();
				sb2 = new StringBuilder();
			}
		}
	}

	private static boolean setStringBuilder(StringBuilder sb, Map<String, Integer> strWords) {
		if(sb.length() == 2) {
			if(strWords.get(sb.toString()) != null){
				strWords.replace(sb.toString(), strWords.get(sb.toString()) + 1);
				return true;
			}
			if(strWords.get(sb.toString()) == null){
				strWords.put(sb.toString(), 1);
				return true;
			}
		}
		return false;
	}
}
