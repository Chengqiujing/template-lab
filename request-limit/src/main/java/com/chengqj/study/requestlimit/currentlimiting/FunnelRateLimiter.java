package com.chengqj.study.requestlimit.currentlimiting;

public class FunnelRateLimiter {
    static class Funnel{
        int capacity;
        float leakingRate;
        int leftQuota;
        long leakingTs;

        public Funnel(int capacity,float leakingRate){
            this.capacity = capacity; // 漏斗容量
            this.leakingRate = leakingRate; // 漏出率
            this.leftQuota = capacity; // 左限额
            this.leakingTs = System.currentTimeMillis(); // 漏出时间戳
        }

        void makeSpace(){
            long nowTs = System.currentTimeMillis();
            long deltaTs = nowTs - leakingTs; //
            int deltaQuota = (int)(deltaTs * leakingRate);

        }

    }



}
