public final List<T> getDates(final T seed, final T periodStart, final T periodEnd, final int maxCount) {

        final List<T> dates = new ArrayList<>();

        T candidateSeed = seed;

        // optimize the start time for selecting candidates
        // (only applicable where a COUNT is not specified)
        if (count == null) {
            while (TemporalAdapter.isBefore(candidateSeed, periodStart)) {
                candidateSeed = increment(candidateSeed);
                if (candidateSeed == null) {
                    return dates;
                }
            }
        }

        HashSet<T> invalidCandidates = new HashSet<>();
        int noCandidateIncrementCount = 0;
        T candidate = null;
        while ((maxCount < 0) || (dates.size() < maxCount)) {
            if (getUntil() != null && candidate != null && TemporalAdapter.isAfter(candidate, getUntil())) {
                break;
            }
            if (periodEnd != null && candidate != null && TemporalAdapter.isAfter(candidate, periodEnd)) {
                break;
            }
            if (getCount() >= 1 && (dates.size() + invalidCandidates.size()) >= getCount()) {
                break;
            }

            // rootSeed = date used for the seed for the RRule at the
            //            start of the first period.
            // candidateSeed = date used for the start of 
            //                 the current period.
            final List<T> candidates = getCandidates(seed, candidateSeed);
            if (!candidates.isEmpty()) {
                noCandidateIncrementCount = 0;
                // sort candidates for identifying when UNTIL date is exceeded..
                if (seed instanceof LocalDate) {
                    candidates.sort(new TemporalComparator(ChronoUnit.DAYS));
                } else {
                    candidates.sort(CANDIDATE_SORTER);
                }
                for (T candidate1 : candidates) {
                    candidate = candidate1;
                    // don't count candidates that occur before the seed date..
                    if (!TemporalAdapter.isBefore(candidate, seed)) {
                        // candidates exclusive of periodEnd..
                        if (TemporalAdapter.isBefore(candidate, periodStart) || TemporalAdapter.isAfter(candidate, periodEnd)) {
                            invalidCandidates.add(candidate);
                        } else if (getCount() >= 1 && (dates.size() + invalidCandidates.size()) >= getCount()) {
                            break;
                        } else if (!TemporalAdapter.isBefore(candidate, periodStart) && !TemporalAdapter.isAfter(candidate, periodEnd)
                            && (getUntil() == null || !TemporalAdapter.isAfter(candidate, getUntil()))) {

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
            candidateSeed = increment(candidateSeed);
            if (candidateSeed == null) {
                break;
            }
        }
        // sort final list..
        if (seed instanceof LocalDate) {
            dates.sort(new TemporalComparator(ChronoUnit.DAYS));
        } else {
            dates.sort(CANDIDATE_SORTER);
        }
        return dates;
    }