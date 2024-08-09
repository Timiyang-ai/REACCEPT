public final DateList getDates(final Date seed, final Date periodStart,
                                   final Date periodEnd, final Value value,
                                   final int maxCount) {

        final DateList dates = new DateList(value);
        if (seed instanceof DateTime) {
            if (((DateTime) seed).isUtc()) {
                dates.setUtc(true);
            }
            else {
                dates.setTimeZone(((DateTime) seed).getTimeZone());
            }
        }
        final Calendar cal = getCalendarInstance(seed, true);

        // optimize the start time for selecting candidates
        // (only applicable where a COUNT is not specified)
        if (getCount() < 1) {
            final Calendar seededCal = (Calendar) cal.clone();
            while (seededCal.getTime().before(periodStart)) {
                cal.setTime(seededCal.getTime());
                increment(seededCal);
            }
        }

        int invalidCandidateCount = 0;
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
                    && (dates.size() + invalidCandidateCount) >= getCount()) {

                break;
            }

//            if (Value.DATE_TIME.equals(value)) {
            if (candidateSeed instanceof DateTime) {
                if (dates.isUtc()) {
                    ((DateTime) candidateSeed).setUtc(true);
                }
                else {
                    ((DateTime) candidateSeed).setTimeZone(dates.getTimeZone());
                }
            }

            final DateList candidates = getCandidates(candidateSeed, value);
            if (!candidates.isEmpty()) {
                noCandidateIncrementCount = 0;
                // sort candidates for identifying when UNTIL date is exceeded..
                Collections.sort(candidates);
                for (final Iterator<Date> i = candidates.iterator(); i.hasNext();) {
                    candidate = i.next();
                    // don't count candidates that occur before the seed date..
                    if (!candidate.before(seed)) {
                        // candidates exclusive of periodEnd..
                        if (candidate.before(periodStart)
                                || !candidate.before(periodEnd)) {
                            invalidCandidateCount++;
                        } else if (getCount() >= 1
                                && (dates.size() + invalidCandidateCount) >= getCount()) {
                            break;
                        } else if (!(getUntil() != null
                                && candidate.after(getUntil()))) {
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
            increment(cal);
        }
        // sort final list..
        Collections.sort(dates);
        return dates;
    }