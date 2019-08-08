package com.amit.alogs.hr.medium;

import java.util.LinkedList;
import java.util.Queue;

public class HR6ActivityNotifications {
    static int activityNotifications(int[] expenditure, int d) {
        if(expenditure==null || expenditure.length==0 || d >= expenditure.length){
            return 0;
        }
        int notfCount = 0;
        int[] counts = new int[201];//<-- expecting no expenditure exceeds $200 in any day, dangerous assumption???? 
        Queue<Integer> q = new LinkedList<>();
        for(int i=0; i<d; i++){
            q.add(expenditure[i]);
            counts[expenditure[i]] = counts[expenditure[i]]+1; 
        }
        for(int i=d; i<expenditure.length; i++){
            //Calculate median
//            medianArray = q.toArray(medianArray);
//            Arrays.sort(medianArray);
        	double median = 0;
            if(d%2==0){
            	int count = 0;
            	int firstVal = -1;
            	int secondVal = -1;
            	for(int j=0;j<counts.length; j++){
            		count = count + counts[j];
                    if(count == d/2)
                    {
                        firstVal = j;
                    }else if(count > d/2)
                    {
                        if(firstVal < 0 ) firstVal = j;
                        secondVal = j;
                        median = ((double)firstVal + (double)secondVal)/2;
                        break;
                    }
            	}
            }else{
            	int count = 0;
            	for(int j=0;j<counts.length; j++){
            		count = count + counts[j];
                    if(count > d/2)
                    {
                    	median = ((double)j);
                    	break;
                    }    
            	}
            }
            int spending = expenditure[i];
            if(spending>=(median*2)){
                notfCount++;
            }
            int val = q.remove();
            counts[val] = counts[val]-1;
            q.add(spending);
            counts[spending] = counts[spending]+1;
        }
        return notfCount;
    }
    
    static int activityNotifications2(int[] expenditure, int d) {

        int count = 0;
        // create freq array as exp <= 200 always
        //maintain a queue to find ontgoing and incoming exp
        //get median from freq array

        int freq[] = new int[201];
        Queue<Integer> q = new LinkedList<Integer>();

        for(int i=0;i<expenditure.length;i++)
        {
            while(i<d)
            {
                q.add(expenditure[i]);
                freq[expenditure[i]] = freq[expenditure[i]]+1;
                i++;
            }


            float median = getMedian(freq,d);

            if(expenditure[i] >= 2*median)
            {
                count++;
            }


            // removing outing going element freq
            int elem = q.remove();
            freq[elem] = freq[elem] - 1;

            // adding incoming element to freq
            q.add(expenditure[i]);
            freq[expenditure[i]] = freq[expenditure[i]]+1;
        }

        return count;
    }

    private static float getMedian(int[] freq,int d)
    {
        if(d%2 == 1)
        {
            int center = 0;
            for(int i=0;i<freq.length;i++)
            {
                center = center + freq[i];

                if(center > d/2)
                {
                    return i;
                }
            }
        }else{
            int count = 0;
            int first = -1;
            int second = -1;
            for(int i=0;i<freq.length;i++)
            {
                count = count + freq[i];

                if(count == d/2)
                {
                    first = i;
                }else if(count > d/2)
                {
                    if(first < 0 ) first = i;
                    second = i;

                    return ((float)first + (float)second)/2; 
                }
            }
        }
        return 0f;
    }    

    public static void main(String[] args){
//    	int d = 5;
//    	int[] expenditure = {2, 3, 4, 2, 3, 6, 8, 4, 5};
    	
    	int d = 3;
    	int[] expenditure = {10, 20, 30, 40, 50};

    	System.out.println(activityNotifications(expenditure, d));
    }
}
