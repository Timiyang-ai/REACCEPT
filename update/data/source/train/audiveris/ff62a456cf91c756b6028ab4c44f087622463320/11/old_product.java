public RunTable purge (Predicate<Run> predicate,
                           RunTable removed)
    {
        // Check parameters
        if (removed != null) {
            if (removed.orientation != orientation) {
                throw new IllegalArgumentException("'removed' table is of different orientation");
            }

            if ((removed.width != width) || (removed.height != height)) {
                throw new IllegalArgumentException("'removed' table is of different dimension");
            }
        }

        for (int i = 0, size = getSize(); i < size; i++) {
            RunSequence seq = getSequence(i);

            for (Iterator<Run> it = seq.iterator(); it.hasNext();) {
                Run run = it.next();

                if (predicate.check(run)) {
                    it.remove();

                    if (removed != null) {
                        removed.getSequence(i).add(run);
                    }
                }
            }
        }

        return this;
    }