static Array<Long> rangeBy(long from, long toExclusive, long step) {
        if (step == 0) {
            throw new IllegalArgumentException("step cannot be 0");
        } else if (from == toExclusive || step * (from - toExclusive) > 0) {
            return Array.empty();
        } else {
            final int one = (from < toExclusive) ? 1 : -1;
            return Array.rangeClosedBy(from, toExclusive - one, step);
        }
    }