static List<Long> rangeClosedBy(long from, long toInclusive, long step) {
        if (step == 0) {
            throw new IllegalArgumentException("step cannot be 0.");
        } else if (from == toInclusive) {
            return List.of(from);
        } else if (step * (from - toInclusive) > 0) {
            return List.empty();
        } else {
            final long gap = (from - toInclusive) % step;
            final int signum = (from < toInclusive) ? -1 : 1;
            final long bound = from * signum;
            List<Long> result = List.empty();
            for (long i = toInclusive + gap; i * signum <= bound; i -= step) {
                result = result.prepend(i);
            }
            return result;
        }
    }