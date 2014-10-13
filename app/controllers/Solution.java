package controllers;

import java.util.HashSet;

public class Solution {
	static int getNumberOfPrimes(int N) {
	    HashSet<Integer> nonPrimary = new HashSet<Integer>();
	    for (int i = 2; i < N / 2; i = i + 2) {
	        int j = 3;
	        int tmp = (i + 1) * j;
	        while (tmp < N) {
	            nonPrimary.add(tmp);
	            j += 2;
	            tmp += 2 * (i + 1);
	        }
	    }
	    return N - nonPrimary.size() - (N / 2 - 1) - 1;
	}

	public static void main(String[] args) {
		int N = 1000000;
		System.out.println(getNumberOfPrimes(N));
	}
}