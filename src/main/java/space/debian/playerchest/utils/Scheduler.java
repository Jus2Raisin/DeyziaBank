// 
// Decompiled by Procyon v0.5.36
// 

package space.debian.playerchest.utils;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ScheduledExecutorService;

public class Scheduler
{
    private static ScheduledExecutorService scheduler;
    
    public static void runAsync(final Runnable runnable) {
        Scheduler.scheduler.execute(runnable);
    }
    
    public static void runAsync(final Runnable runnable, final long firstDelay, final long delay, final TimeUnit timeUnit) {
        Scheduler.scheduler.scheduleAtFixedRate(runnable, firstDelay, delay, timeUnit);
    }
    
    static {
        Scheduler.scheduler = Executors.newScheduledThreadPool(1);
    }
}
