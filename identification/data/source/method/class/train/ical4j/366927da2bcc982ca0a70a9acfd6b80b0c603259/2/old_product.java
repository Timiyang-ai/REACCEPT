public final DateList getDates(final Date seed, final Date periodStart,
                                   final Date periodEnd, final Value value,
                                   final int maxCount) {

        final DateList dates = new DateList(value);
        if (seed instanceof DateTime) {
            if (((DateTime) seed).isUtc()) {
                dates.setUtc(true);
            } else {
                dates.setTimeZone(((DateTime) seed).getTimeZone());
            }
        }
        Calendar cal = getCalendarInstance(seed, true);
        final Calendar rootSeed = (Calendar)cal.clone();
        
        // optimize the start time for selecting candidates
        // (only applicable where a COUNT is not specified)
        if (count == null) {
            Calendar seededCal = (Calendar) cal.clone();
            while (seededCal.getTime().before(periodStart)) {
                cal.setTime(seededCal.getTime());
                seededCal = smartIncrement(seededCal);
                if (seededCal == null) {
                    return dates;
                }
            }
        }

        HashSet<Date> invalidCandidates = new HashSet<Date>();
        int noCandidateIncrementCount = 0;
        Date candidate = null;
        while ((maxCount < 0) || (dates.size() < maxCount)) {
            final Date candidateSeed = Dates.getInstance(cal.getTime(), value);

            if (getUntil() != null && candidate != null
                    && candidate.after(getUntil())) {
                break;
            }
            if (periodEnd != null && candidate != null
                    && candidate.after(periodEnd)) {
                break;
            }
            if (getCount() >= 1
                    && (dates.size() + invalidCandidates.size()) >= getCount()) {
                break;
            }

//            if (Value.DATE_TIME.equals(value)) {
            if (candidateSeed instanceof DateTime) {
                if (dates.isUtc()) {
                    ((DateTime) candidateSeed).setUtc(true);
                } else {
                    ((DateTime) candidateSeed).setTimeZone(dates.getTimeZone());
                }
            }

            // rootSeed = date used for the seed for the RRule at the
            //            start of the first period.
            // candidateSeed = date used for the start of 
            //                 the current period.
            final DateList candidates = getCandidates(rootSeed, candidateSeed, value);
            if (!candidates.isEmpty()) {
                noCandidateIncrementCount = 0;
                // sort candidates for identifying when UNTIL date is exceeded..
                Collections.sort(candidates);
                for (Date candidate1 : candidates) {
                    candidate = candidate1;
                    // don't count candidates that occur before the seed date..
                    if (!candidate.before(seed)) {
                        // candidates exclusive of periodEnd..
                        if (candidate.before(periodStart)
                                || candidate.after(periodEnd)) {
                            invalidCandidates.add(candidate);
                        } else if (getCount() >= 1
                                && (dates.size() + invalidCandidates.size()) >= getCount()) {
                            break;
                        } else if (getUntil() instanceof Date
                                && candidate.compareTo(getUntil()) < 1) {
                            dates.add(candidate);
                        } else if (!candidate.before(periodStart) && candidate.before(periodEnd)) {
                            dates.add(candidate);
                        }
                    }
                }
            } else {
                noCandidateIncrementCount++;
                if ((maxIncrementCount > 0) && (noCandidateIncrementCount > maxIncrementCount)) {
                    break;
                }
            }
            cal = smartIncrement(cal);
            if (cal == null) {
                break;
            }
        }
        // sort final list..
        Collections.sort(dates);
        return dates;
    }