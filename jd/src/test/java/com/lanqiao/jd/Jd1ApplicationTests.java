package com.lanqiao.jd;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayDeque;
import java.util.Deque;

@SpringBootTest
class Jd1ApplicationTests {

    @Test
    void contextLoads() {

        int search = search(nums, 6);
        System.out.println(search);
    }
    int nums[]={5,7,7,8,8,10};
    public int search(int[] nums, int target) {
            int n=nums.length;

            int l=0,r=nums.length-1;
            while (l<r)
            {   int now=(l+r)/2;
                if(nums[now]>=target) r=now;
                else l=now+1;
            }
        System.out.println(l+" "+r+" "+nums[l]+" "+nums[r]);
            int ans=0;
            int p=l+1;
            while (l>=0){
                if(nums[l]==target)
                    ans++;
                else
                    break;
                l-=1;
            }

            while (p<n){
                if(nums[p]==target)
                    ans++;
                else
                    break;
                p++;
            }
                return ans;

    }

}
