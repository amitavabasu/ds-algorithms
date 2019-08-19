package com.amit.alogs.hr.medium;

import java.util.LinkedList;
import java.util.Queue;

	/*
	 * Inform bank about activity notification when current day expense exceeds previous d day expenses median 
	 */
public class HR6ActivityNotifications {
    static int activityNotifications(int[] expenditure, int d) {
    	/*
    	 * the problem here is calculating the median.
    	 * a mechanism called index sort can be used for this assuming a limit for the daily expenses. Choose this limit carefully
    	 * a queue is also needed to keep track of the elements going out and falling in the range.
    	 * interesting and a good solution. 
    	 */
        if(expenditure==null || expenditure.length==0 || d >= expenditure.length) {
            return 0;
        }
        int notfCount = 0;
        int[] counts = new int[201];//<-- expecting no expenditure exceeds $200 in any day, dangerous assumption???? 
        Queue<Integer> q = new LinkedList<>();
        //for first d expenditures
        for(int i=0; i<d; i++){
            q.add(expenditure[i]); //<-- add that expenditure to queue
            counts[expenditure[i]] = counts[expenditure[i]]+1; //<-- put extra count (i.e. count+1) at the count array
        }
        for(int i=d; i<expenditure.length; i++){ //<-- this loop calculates median
            //Calculate median
        	double median = 0;
            if(d%2==0){ //<-- if d even number
            	int count = 0;
            	int firstVal = -1;
            	int secondVal = -1;
            	for(int j=0;j<counts.length; j++){//<-- iterate entire counts array
            		count += counts[j]; //<-- increment count by counts array 
                    if(count == d/2) //<-- when count reaches d/2 it's the first value
                    {
                        firstVal = j;//<-- set first value to j
                    }else if(count > d/2)//<-- when count exceeds d/2 it's the second value
                    {
                        if(firstVal < 0 ) firstVal = j; //<-- if first value is not set yet by previous check set it to j
                        secondVal = j;//<-- set second value to j
                        median = ((double)firstVal + (double)secondVal)/2; //<--calculate median
                        break; //<-- break no need to continue, median is found
                    }
            	}
            }else{
            	int count = 0;
            	for(int j=0;j<counts.length; j++){//<-- iterate over entire counts array 
            		count = count + counts[j];//<-- increment count by counts array
                    if(count > d/2)//<-- when count reaches d/2 it's the middle of the d array and median
                    {
                    	median = ((double)j); //<-- set median to j
                    	break;//<-- break, median is found
                    }    
            	}
            }
            int spending = expenditure[i];//<-- get current spending/expenditure
            if(spending>=(median*2)){//<-- check if expenditure exceeds median or not based on that take action
                notfCount++;//<-- the action in this case to increase notification count
            }
            int val = q.remove();//<-- remove the last element from queue;
            counts[val] = counts[val]-1;//<-- decrement last element count from counts array
            q.add(spending);//<-- now add current spending in queue
            counts[spending] = counts[spending]+1;//<-- increment spending count by 1
        }
        return notfCount;//<-- finally return notification count
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
