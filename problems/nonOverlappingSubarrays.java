/***

Problem Statement:
Given size of array (say n), return:
1. Find the total number of subarrays present in an array.
2. Find the total number of pairs of non-overlapping subarrays present in an array.

Source: https://leetcode.com/discuss/interview-question/1531250/Google-Phone-Screen
***/

public class Main {
    public static void main(String[] args) {
      
        int size = 3; //input
        System.out.println(nonOverlappingSubarrays(size));
    }
    
    /* O(1) */
    private static int totalSubarrays(int n) {
        return n*(n+1)/2;
    }

    /* O(n) */
    private static int nonOverlappingSubarrays(int n) {
        int count = 0;
        for(int i=0;i<n;++i) {
            // count += totalSubarrays(i) * totalSubarrays(n-i);    /* repetitive */
            count += (i+1) * totalSubarrays(n-i-1);
            System.out.println("count= "+(i+1)+" * "+ totalSubarrays(n-i) +" = "+ count);
        }
        return count;
    }

}
