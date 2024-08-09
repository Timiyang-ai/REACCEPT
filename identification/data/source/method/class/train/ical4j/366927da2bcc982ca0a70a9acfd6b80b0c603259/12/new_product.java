public final <T extends Temporal> List<T> getDates(final T seed, final T periodStart,
                                   final T periodEnd, final int maxCount) {

        final List<T> dates = new ArrayList<>();

        T candidateSeed = seed;

        // optimize the start time for selecting candidates
        // (only applicable where a COUNT is not specified)
        if (count == null) {
            while (Instant.from(candidateSeed).isBefore(Instant.from(periodStart))) {
                candidateSeed = (T) smartIncrement(candidateSeed);
                if (candidateSeed == null) {
                    return dates;
                }
            }
        }

        HashSet<T> invalidCandidates = new HashSet<>();
        int noCandidateIncrementCount = 0;
        T candidate = null;
        while ((maxCount < 0) || (dates.size() < maxCount)) {
            if (getUntil() != null && candidate != null
                    && Instant.from(candidate).isAfter(getUntil())) {
                break;
            }
            if (periodEnd != null && candidate != null
                    && Instant.from(candidate).isAfter(Instant.from(periodEnd))) {
                break;
            }
            if (getCount() >= 1
                    && (dates.size() + invalidCandidates.size()) >= getCount()) {
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
                Collections.sort(candidates, CANDIDATE_SORTER);
                for (T candidate1 : candidates) {
                    candidate = candidate1;
                    // don't count candidates that occur before the seed date..
                    if (!Instant.from(candidate).isBefore(Instant.from(seed))) {
                        // candidates exclusive of periodEnd..
                        if (Instant.from(candidate).isBefore(Instant.from(periodStart))
                                || Instant.from(candidate).isAfter(Instant.from(periodEnd))) {
                            invalidCandidates.add(candidate);
                        } else if (getCount() >= 1
                                && (dates.size() + invalidCandidates.size()) >= getCount()) {
                            break;
                        } else if (!Instant.from(candidate).isBefore(Instant.from(periodStart)) && !Instant.from(candidate).isAfter(Instant.from(periodEnd))
                            && (getUntil() == null || !Instant.from(candidate).isAfter(getUntil()))) {

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
            candidateSeed = smartIncrement(candidateSeed);
            if (candidateSeed == null) {
                break;
            }
        }
        // sort final list..
        Collections.sort(dates, CANDIDATE_SORTER);
        return dates;
    }