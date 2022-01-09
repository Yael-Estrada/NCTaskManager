package mx.edu.j2se.estrada.tasks;

import java.io.Serializable;
import java.time.DateTimeException;
import java.time.Duration;
import java.time.Period;
import java.time.chrono.ChronoPeriod;
import java.time.chrono.Chronology;
import java.time.chrono.IsoChronology;
import java.time.temporal.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static java.time.temporal.ChronoUnit.*;

public class Timestamp implements TemporalAmount, Serializable,Comparable<Timestamp> {
    public static Timestamp ZERO=new Timestamp(0,0,0,0,0,0);
    static final int Seconds_x_Minute=60,Minute_x_Hour=60,Hour_x_Day=24;
    private final int years;
    private final int months;
    private final int days;
    private final int hours;
    private final int minutes;
    private final int seconds;

    public static final List<TemporalUnit> SUPPORTED_UNITS=
            Collections.unmodifiableList(Arrays.<TemporalUnit>asList(YEARS, MONTHS, DAYS,HOURS,MINUTES,SECONDS));


    private Timestamp(int years, int months, int days,int hours,int minutes,int seconds) {
        this.years = years;
        this.months = months;
        this.days = days;
        this.hours=hours;
        this.minutes=minutes;
        this.seconds=seconds;
    }

    public static Timestamp ofYears(int years){
        return create(years,0,0,0,0,0);
    }
    public static Timestamp ofMonths(int val){
        return create(0,val,0,0,0,0);
    }
    public static Timestamp ofDays(int val){
        return create(0,0,val,0,0,0);
    }
    public static Timestamp ofHours(int val){
        return create(0,0,0,val,0,0);
    }
    public static Timestamp ofMinutes(int val){
        return create(0,0,0,0,val,0);
    }
    public static Timestamp ofSeconds(int val){
        return create(0,0,0,0,0,val);
    }


    private static Timestamp create(int years, int months, int days,int hours,int minutes,int seconds) {
        if ((years | months | days | hours | minutes | seconds) == 0) {
            return ZERO;
        }
        return new Timestamp(years, months, days,hours,minutes,seconds);
    }

    @Override
    public long get(TemporalUnit unit) {
        if (unit == ChronoUnit.YEARS) {
            return getYears();
        } else if (unit == ChronoUnit.MONTHS) {
            return getMonths();
        } else if (unit == ChronoUnit.DAYS) {
            return getDays();
        } else if (unit==ChronoUnit.HOURS){
            return getHours();
        } else if(unit==ChronoUnit.MINUTES){
            return getMinutes();
        } else if(unit==ChronoUnit.SECONDS){
            return getSeconds();
        } else{
            throw new UnsupportedTemporalTypeException("Unsupported unit: " + unit);
        }
    }

    private long getSeconds() {
        return seconds;
    }

    private long getMinutes() {
        return minutes;
    }

    private long getHours() {
        return hours;
    }

    private long getDays() {
        return days;
    }

    private long getMonths() {
        return months;
    }

    private long getYears() {
        return years;
    }

    @Override
    public List<TemporalUnit> getUnits() {
        return SUPPORTED_UNITS;
    }


    @Override
    public Temporal addTo(Temporal temporal) {
        temporal=temporal.plus(years, YEARS);
        temporal=temporal.plus(months, MONTHS);
        temporal=temporal.plus(days, DAYS);
        temporal=temporal.plus(hours, HOURS);
        temporal=temporal.plus(minutes, MINUTES);
        temporal=temporal.plus(seconds, SECONDS);
        return temporal;
    }

    @Override
    public Temporal subtractFrom(Temporal temporal) {
        temporal=temporal.minus(years, YEARS);
        temporal=temporal.minus(months, MONTHS);
        temporal=temporal.minus(days, DAYS);
        temporal=temporal.minus(hours, HOURS);
        temporal=temporal.minus(minutes, MINUTES);
        temporal=temporal.minus(seconds, SECONDS);
        return temporal;
    }

    public boolean isZero() {
        return (this.compareTo(ZERO)==0);
    }

    public static Timestamp from(TemporalAmount amount) {
        if (amount instanceof Timestamp) {
            return (Timestamp) amount;
        }
        Objects.requireNonNull(amount, "amount");
        int years = 0;
        int months = 0;
        int days = 0;
        int hours=0;
        int minutes=0;
        int seconds=0;
        for (TemporalUnit unit : amount.getUnits()) {
            long unitAmount = amount.get(unit);
            if (unit == ChronoUnit.YEARS) {
                years = Math.toIntExact(unitAmount);
            } else if (unit == ChronoUnit.MONTHS) {
                months = Math.toIntExact(unitAmount);
            } else if (unit == ChronoUnit.DAYS) {
                days = Math.toIntExact(unitAmount);
            } else if (unit == ChronoUnit.HOURS) {
                hours = Math.toIntExact(unitAmount);
            } else if (unit == ChronoUnit.MINUTES) {
                minutes = Math.toIntExact(unitAmount);
            } else if(unit==ChronoUnit.SECONDS){
                seconds=Math.toIntExact(unitAmount);
            } else {
                throw new DateTimeException("Unit must be Years, Months,Days,Hours,Minutes or Senconds, but was " + unit);
            }
        }
        return create(years, months, days,hours,minutes,seconds);
    }
    @Override
    public int compareTo(Timestamp o) {
        int cmp=Integer.compare(years,o.years);
        if(cmp==0)
            cmp=Integer.compare(months,o.months);
        if(cmp==0)
            cmp=Integer.compare(days,o.days);
        if(cmp==0)
            cmp=Integer.compare(hours,o.hours);
        if(cmp==0)
            cmp=Integer.compare(minutes,o.minutes);
        if(cmp==0)
            cmp=Integer.compare(seconds,o.seconds);
        return cmp;
    }

    @Override
    public int hashCode() {
        return ((int) Integer.rotateLeft(years,32) + Integer.rotateLeft(months, 16) + Integer.rotateLeft(days, 8)+ Integer.rotateLeft(hours,4) +Integer.rotateLeft(minutes,2)+seconds);
    }

    @Override
    public String toString(){
        String s="";
        if(this.compareTo(ZERO)==0){
            return "T0S";
        }
        StringBuilder buf = new StringBuilder();
        buf.append("T");
        if(years!=0) buf.append(years).append("Y");
        if(months!=0) buf.append(months).append("Mo");
        if(days!=0) buf.append(days).append("D");
        if(hours!=0) buf.append(hours).append("H");
        if(minutes!=0) buf.append(minutes).append("Mi");
        if(seconds!=0)buf.append(seconds).append("S");
        return buf.toString();
    }

    @Override
    public boolean equals(Object p){
        return this.compareTo((Timestamp) p)==0;
    }

    public long inSeconds(){
        return seconds+minutes*60+hours*3600+days*86400+months*2629800+years*31557600;
    }

    public static Timestamp fromSeconds(long secs){
        int years=0,months=0,days=0,hours=0,minutes=0,seconds=0;
        years=(int)secs/31557600;
        secs=secs%31557600;
        months=(int)secs/2629800;
        secs=secs%2629800;
        days=(int)secs/86400;
        secs=secs%86400;
        hours=(int)secs/3600;
        secs=secs%3600;
        minutes=(int)secs/60;
        secs=secs%60;
        seconds=(int)secs;
        return create(years,months,days,hours,minutes,seconds);
    }
}
