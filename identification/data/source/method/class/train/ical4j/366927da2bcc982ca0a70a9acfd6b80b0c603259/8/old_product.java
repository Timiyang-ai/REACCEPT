public final DateList getDates(final Date seed, final Date periodStart,
            final Date periodEnd, final Value value) {
        DateList dates = new DateList(value);
        Calendar cal = Dates.getCalendarInstance(seed);
        cal.setTime(seed);
        int invalidCandidateCount = 0;
        while (!((getUntil() != null && cal.getTime().after(getUntil()))
                || (periodEnd != null && cal.getTime().after(periodEnd))
                || (getCount() >= 1 && (dates.size() + invalidCandidateCount) >= getCount()))) {
            DateList candidates = getCandidates(Dates.getInstance(cal.getTime(), value), value);
            for (Iterator i = candidates.iterator(); i.hasNext();) {
                Date candidate = (Date) i.next();
                // don't count candidates that occur before the seed date..
                if (!candidate.before(seed)) {
                    // candidates exclusive of periodEnd..
                    if (candidate.before(periodStart) || !candidate.before(periodEnd)) {
                        invalidCandidateCount++;
                    }
                    else if (getCount() >= 1 && (dates.size() + invalidCandidateCount) >= getCount()) {
                        break;
                    }
                    else if (!(getUntil() != null && cal.getTime().after(getUntil()))) {
                        dates.add(candidate);
                    }
                }
            }
            increment(cal);
        }
        // sort final list..
        Collections.sort(dates);
        return dates;
    }