package cn.edu.szu.cs.quickmonomer.util.cache;


import java.util.concurrent.TimeUnit;

/**
 * @author Whitence
 * @date 20221008
 */
public enum CacheTimeInterval {

    /**
     * 30s
     */
    THIRTY_SECOND(30,TimeUnit.SECONDS,5),

    /**
     * 1min
     */
    ONE_MIN(60,TimeUnit.SECONDS,10),

    /**
     * 3min
     */
    THREE_MIN(180,TimeUnit.SECONDS,20),

    /**
     * 5min
     */
    FIVE_MIN(5,TimeUnit.MINUTES,1),

    /**
     * 10min
     */
    TEN_MIN(10,TimeUnit.MINUTES,2),

    /**
     * 30min
     */

    THIRTY_MIN(30,TimeUnit.MINUTES,5),

    /**
     * 1h
     */
    AN_HOUR(60,TimeUnit.HOURS,10),

    /**
     * 1d
     */
    A_DAY(24,TimeUnit.HOURS,1);

    private final long time;

    private final TimeUnit timeUnit;

    private final long diff;


    CacheTimeInterval(long time, TimeUnit timeUnit, long diff) {
        this.time=time;
        this.timeUnit=timeUnit;
        this.diff=diff;
    }

    public long getTime() {
        return time+(long) ((Math.random()-0.5)*diff);
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

}

