public final DateList getDates(final Date base, final Date periodStart,
                                   final Date periodEnd, final Value value) {

        DateList dates = new DateList(value);

        // Should never happen!  base is always required!
        if (base == null) {
            return dates;
        }
        
        Date start = (Date) periodStart.clone();

        // We don't want to see or search any dates that occur before the
        // first instance of this recurrence.
        if (start.before(base)) {
            start = base;
        }

        Calendar baseCalendar = Calendar.getInstance();
        baseCalendar.setTime(base);
        
        Calendar cal = Calendar.getInstance();
        cal.setTime(start);
        cal.set(Calendar.MILLISECOND, 0);

        int lvf = getLowestVariableField();
        
        // Set everything to the right of the LVF to match the DTSTART.
        // Everything else comes from the query start date.
        int[] matchFields = getMatchFields(lvf);
        for (int i = 0; i < matchFields.length; i++) {
            cal.set(matchFields[i], baseCalendar.get(matchFields[i]));
        }

        // Deal with rolling over to the next time period if necessary.
        if (cal.getTime().before(periodStart)) {
            cal.add(lvf, 1);
        }
        
        // Weekly frequencies need to match up the week day. (i.e. if it's
        // Friday, and that's not part of our criteria, keep adding a day
        // until they match).
        /*if (getFrequency().equals(WEEKLY)) {

            while (cal.get(Calendar.DAY_OF_WEEK) !=

                   dtStartCalendar.get(Calendar.DAY_OF_WEEK)) {
                cal.add(Calendar.DAY_OF_MONTH, 1);
            }
        }*/

        cal.setTime(base);
        
        // apply frequency/interval rules..
        if (getUntil() != null) {
            while (!cal.getTime().after(getUntil())
                    && !(periodEnd != null && cal.getTime().after(periodEnd))) {
                dates.add(new Date(cal.getTime().getTime()));
                increment(cal);
            }
        }
        /*
        else if (getCount() >= 1) {
            for (int i = 0;
                i < getCount() && !(periodEnd != null && cal.getTime().after(periodEnd));
                i++) {
                dates.add(cal.getTime());
                increment(cal);
            }
        }
        */
        else if (periodEnd != null) {
            while (!cal.getTime().after(periodEnd)) {
                dates.add(new Date(cal.getTime().getTime()));
                increment(cal);
            }
        }
        else {
            // if no end-point specified we can't calculate a finite
            // set of dates..
            return dates;
        }

        // debugging..
        if (log.isDebugEnabled()) {
            log.debug("Dates after FREQUENCY/INTERVAL processing: " + dates);
        }

        dates = getMonthVariants(dates);

        // debugging..
        if (log.isDebugEnabled()) {
            log.debug("Dates after BYMONTH processing: " + dates);
        }

        dates = getWeekNoVariants(dates);

        // debugging..
        if (log.isDebugEnabled()) {
            log.debug("Dates after BYWEEKNO processing: " + dates);
        }

        dates = getYearDayVariants(dates);

        // debugging..
        if (log.isDebugEnabled()) {
            log.debug("Dates after BYYEARDAY processing: " + dates);
        }

        dates = getMonthDayVariants(dates);

        // debugging..
        if (log.isDebugEnabled()) {
            log.debug("Dates after BYMONTHDAY processing: " + dates);
        }

        dates = getDayVariants(dates);

        // debugging..
        if (log.isDebugEnabled()) {
            log.debug("Dates after BYDAY processing: " + dates);
        }

        dates = getHourVariants(dates);

        // debugging..
        if (log.isDebugEnabled()) {
            log.debug("Dates after BYHOUR processing: " + dates);
        }

        dates = getMinuteVariants(dates);

        // debugging..
        if (log.isDebugEnabled()) {
            log.debug("Dates after BYMINUTE processing: " + dates);
        }

        dates = getSecondVariants(dates);

        // debugging..
        if (log.isDebugEnabled()) {
            log.debug("Dates after BYSECOND processing: " + dates);
        }
        
        // check count..
        if (getCount() >= 1 && dates.size() >= getCount()) {
            for (int i = getCount(); i < dates.size();) {
                dates.remove(i);
            }
        }

        // debugging..
        if (log.isDebugEnabled()) {
            log.debug("Dates after COUNT processing: " + dates);
        }

        // final post-processing..
        for (int i = 0; i < dates.size(); i++) {
            Date date = (Date) dates.get(i);
            if (periodStart != null && date.before(periodStart)) {
                dates.remove(date);
                i--;
            }
            else if (periodEnd != null && date.after(periodEnd)) {
                dates.remove(date);
                i--;
            }
            else if (getUntil() != null && date.after(getUntil())) {
                dates.remove(date);
                i--;
            }
            /*
            else if (getCount() >= 1 && i >= getCount()) {
                dates.remove(date);
                i--;
            }
            */
        }

        return dates;
    }