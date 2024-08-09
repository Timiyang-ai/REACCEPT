public final DateList getDates(final DtStart dtStart, final Date strt,
                                   final Date end, final Value value) {

        DateList dates = new DateList(value);
        Date start = (Date) strt.clone();

        // Should never happen!  DTSTART is always required!
        if (dtStart == null) {
            return dates;
        }

        // We don't want to see or search any dates that occurr before the
        // first instance of this recurrence.
        if (start.before(dtStart.getTime())) {
            start = dtStart.getTime();
        }

        int lowestVariableField = 9999;
        String frequency = getFrequency();

        if (YEARLY.equals(frequency)) {
            lowestVariableField = Calendar.YEAR;
        } else if (MONTHLY.equals(frequency)) {
            lowestVariableField = Calendar.MONTH;
        } else if (WEEKLY.equals(frequency)) {
            lowestVariableField = Calendar.DAY_OF_MONTH;
        } else if (DAILY.equals(frequency)) {
            lowestVariableField = Calendar.DAY_OF_MONTH;
        } else if (HOURLY.equals(frequency)) {
            lowestVariableField = Calendar.HOUR_OF_DAY;
        } else if (MINUTELY.equals(frequency)) {
            lowestVariableField = Calendar.MINUTE;
        } else if (SECONDLY.equals(frequency)) {
            lowestVariableField = Calendar.SECOND;
        }

        Calendar dtStartCalendar = Calendar.getInstance();
        dtStartCalendar.setTime(dtStart.getTime());
        Calendar cal = Calendar.getInstance();
        cal.setTime(start);
        cal.set(Calendar.MILLISECOND, 0);
        // reset fields where BY* rules apply..
        if (!getMonthList().isEmpty()) {
            //cal.set(Calendar.MONTH, 0);
            lowestVariableField = Calendar.MONTH < lowestVariableField ?
                                  Calendar.MONTH : lowestVariableField;
        }
        if (!getWeekNoList().isEmpty()) {
            //cal.set(Calendar.WEEK_OF_YEAR, 1);
            lowestVariableField = Calendar.DAY_OF_MONTH < lowestVariableField ?
                                  Calendar.DAY_OF_MONTH : lowestVariableField;
        }
        if (!getYearDayList().isEmpty()) {
            //cal.set(Calendar.DAY_OF_YEAR, 1);
            lowestVariableField = Calendar.DAY_OF_MONTH < lowestVariableField ?
                                  Calendar.DAY_OF_MONTH : lowestVariableField;
        }
        if (!getMonthDayList().isEmpty()) {
            //cal.set(Calendar.DAY_OF_MONTH, 1);
            lowestVariableField = Calendar.DAY_OF_MONTH < lowestVariableField ?
                                  Calendar.DAY_OF_MONTH : lowestVariableField;
        }
        if (!getDayList().isEmpty()) {
            //cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
            lowestVariableField = Calendar.DAY_OF_MONTH < lowestVariableField ?
                                  Calendar.DAY_OF_MONTH : lowestVariableField;
        }
        if (!getHourList().isEmpty()) {
            //cal.set(Calendar.HOUR_OF_DAY, 0);
            lowestVariableField = Calendar.HOUR_OF_DAY < lowestVariableField ?
                                  Calendar.HOUR_OF_DAY : lowestVariableField;
        }
        if (!getMinuteList().isEmpty()) {
            //cal.set(Calendar.MINUTE, 0);
            lowestVariableField = Calendar.MINUTE < lowestVariableField ?
                                  Calendar.MINUTE : lowestVariableField;
        }
        if (!getSecondList().isEmpty()) {
            //cal.set(Calendar.SECOND, 0);
            lowestVariableField = Calendar.SECOND < lowestVariableField ?
                                  Calendar.SECOND : lowestVariableField;
        }

        // Set everything to the right of the LVF to match the DTSTART.
        // Everything else comes from the query start date.
        int[] matchFields = getMatchFields(lowestVariableField);
        for (int i = 0; i < matchFields.length; i++) {
            cal.set(matchFields[i], dtStartCalendar.get(matchFields[i]));
        }

        // Deal with rolling over to the next time period if necessary.
        if (cal.getTime().getTime() < strt.getTime()) {
            cal.add(lowestVariableField, 1);
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

        // apply frequency/interval rules..
        if (getUntil() != null) {
            while (!cal.getTime().after(getUntil())
                    && !(end != null && cal.getTime().after(end))) {
                dates.add(cal.getTime());
                increment(cal);
            }
        }
        else if (getCount() >= 1) {
            for (int i = 0;
                i < getCount() && !(end != null && cal.getTime().after(end));
                i++) {
                dates.add(cal.getTime());
                increment(cal);
            }
        }
        else if (end != null) {
            while (!cal.getTime().after(end)) {
                dates.add(cal.getTime());
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

        // final processing..
        for (int i = 0; i < dates.size(); i++) {
            Date date = (Date) dates.get(i);
            if (date.before(start)) {
                dates.remove(date);
                i--;
            }
            else if (end != null && date.after(end)) {
                dates.remove(date);
                i--;
            }
            else if (getUntil() != null && date.after(getUntil())) {
                dates.remove(date);
                i--;
            }
            else if (getCount() >= 1 && i >= getCount()) {
                dates.remove(date);
                i--;
            }
        }

        return dates;
    }