package org.quartz;

import java.util.Calendar;

import org.quartz.DateBuilder.IntervalUnit;

/**
 * <p>A concrete <code>{@link Trigger}</code> that is used to fire a <code>{@link org.quartz.JobDetail}</code>
 * based upon repeating calendar time intervals.</p>
 * 
 * <p>The trigger will fire every N (see {@link #setRepeatInterval(int)} ) units of calendar time
 * (see {@link #setRepeatIntervalUnit(IntervalUnit)}) as specified in the trigger's definition.  
 * This trigger can achieve schedules that are not possible with {@link SimpleTrigger} (e.g 
 * because months are not a fixed number of seconds) or {@link CronTrigger} (e.g. because
 * "every 5 months" is not an even divisor of 12).</p>
 * 
 * <p>If you use an interval unit of <code>MONTH</code> then care should be taken when setting
 * a <code>startTime</code> value that is on a day near the end of the month.  For example,
 * if you choose a start time that occurs on January 31st, and have a trigger with unit
 * <code>MONTH</code> and interval <code>1</code>, then the next fire time will be February 28th, 
 * and the next time after that will be March 28th - and essentially each subsequent firing will 
 * occur on the 28th of the month, even if a 31st day exists.  If you want a trigger that always
 * fires on the last day of the month - regardless of the number of days in the month, 
 * you should use <code>CronTrigger</code>.</p> 
 * 
 * @see TriggerBuilder
 * @see DateIntervalScheduleBuilder
 * @see SimpleScheduleBuilder
 * @see CronScheduleBuilder
 * 
 * @author James House
 */
public interface DateIntervalTrigger {

    /**
     * <p>
     * Instructs the <code>{@link Scheduler}</code> that upon a mis-fire
     * situation, the <code>{@link DateIntervalTrigger}</code> wants to be 
     * fired now by <code>Scheduler</code>.
     * </p>
     */
    public static final int MISFIRE_INSTRUCTION_FIRE_ONCE_NOW = 1;
    /**
     * <p>
     * Instructs the <code>{@link Scheduler}</code> that upon a mis-fire
     * situation, the <code>{@link DateIntervalTrigger}</code> wants to have it's
     * next-fire-time updated to the next time in the schedule after the
     * current time (taking into account any associated <code>{@link Calendar}</code>,
     * but it does not want to be fired now.
     * </p>
     */
    public static final int MISFIRE_INSTRUCTION_DO_NOTHING = 2;

    /**
     * <p>Get the interval unit - the time unit on with the interval applies.</p>
     */
    public IntervalUnit getRepeatIntervalUnit();

    /**
     * <p>
     * Get the the time interval that will be added to the <code>DateIntervalTrigger</code>'s
     * fire time (in the set repeat interval unit) in order to calculate the time of the 
     * next trigger repeat.
     * </p>
     */
    public int getRepeatInterval();

    /**
     * <p>
     * Get the number of times the <code>DateIntervalTrigger</code> has already
     * fired.
     * </p>
     */
    public int getTimesTriggered();

}