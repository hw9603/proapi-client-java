package com.whitepages.proapi.data.util;

import java.util.Date;

/**
 * Copyright 2015 Whitepages, Inc.
 */
public class TimePeriod {

    private final Date start;

    private final Date stop;

    public TimePeriod(Date start, Date stop) {
        this.start = start;
        this.stop = stop;
    }

    public Date getStart() {
        return start;
    }

    public Date getStop() {
        return stop;
    }

    public boolean isHistorical() {
        return (stop == null);
    }

    public boolean isCurrent() {
        return !isHistorical();
    }

    @Override
    public String toString() {
        return String.format( "[TimePeriod: start=%s; stop=%s]", start, stop );
    }
}
