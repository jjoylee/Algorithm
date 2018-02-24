package algorithm;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class KakaoCache {
	static Queue cache = new LinkedList();
	static int searchTime = 0;
	static int cacheSize;
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		cacheSize = Integer.parseInt(sc.nextLine());
		String city = sc.nextLine();
		StringTokenizer cities = new StringTokenizer(city ,"[, , ,]", false);
		while(cities.hasMoreTokens()){
			if(cacheSize == 0) cacheSize += 5;
			else searchCacahe(cities.nextToken());
		}
		System.out.println(searchTime);
	}
	
	private static void searchCacahe(String nextToken) {
		if(!cache.contains(nextToken)){
			if(cache.size() == cacheSize){	cache.poll();	}
			cache.add(nextToken.toLowerCase());
			searchTime += 5;
		}
		else{
			cache.remove(nextToken);
			cache.add(nextToken);
			searchTime += 1;
		}
	}
}
