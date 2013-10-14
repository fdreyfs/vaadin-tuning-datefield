package org.vaadin.addons.tuningdatefield.demo;

import org.joda.time.LocalDate;

public class Absence {
    private LocalDate day;
    private AbsenceDuration duration;

    public Absence(LocalDate day, AbsenceDuration duration) {
        this.day = day;
        this.duration = duration;
    }

    public Absence(int year, int monthOfYear, int dayOfMonth, AbsenceDuration duration) {
        this.day = new LocalDate(year, monthOfYear, dayOfMonth);
        this.duration = duration;
    }

    public static enum AbsenceDuration {
        FULLDAY, MORNING, AFTERNOON;
    }

    /**
     * @return the day
     */
    public LocalDate getDay() {
        return day;
    }

    /**
     * @return the duration
     */
    public AbsenceDuration getDuration() {
        return duration;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((day == null) ? 0 : day.hashCode());
        result = prime * result + ((duration == null) ? 0 : duration.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Absence other = (Absence) obj;
        if (day == null) {
            if (other.day != null)
                return false;
        } else if (!day.equals(other.day))
            return false;
        if (duration != other.duration)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Absence [day=" + day + ", duration=" + duration + "]";
    }

    /**
     * @param day the day to set
     */
    public void setDay(LocalDate day) {
        this.day = day;
    }

    /**
     * @param duration the duration to set
     */
    public void setDuration(AbsenceDuration duration) {
        this.duration = duration;
    }}


