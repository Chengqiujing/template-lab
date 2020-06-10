package com.chengqj.study.redisdistributionlock.lock;

import javax.validation.constraints.NotNull;
import java.util.concurrent.TimeUnit;

public interface BasicRedisLock {

    /**
     * 尝试加锁
     *
     * 尝试加锁成功 true 失败 false
     */
    public boolean tryLock();

    /**
     * 尝试加锁 带有过期时间
     * @param time
     * @param unit
     * @return
     */
    public boolean tryLock(long time,@NotNull TimeUnit unit);
    /**
     *  加锁
     *
     *  阻塞 知道获取到锁
     * @return
     */
    public boolean lock(String value);

    /**
     * 解锁
     * @return
     */
    public boolean unlock();

}
