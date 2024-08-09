public static TreeSet<Integer> rangeBy(int from, int toExclusive, int step) {
        if (step == 0) {
            throw new IllegalArgumentException("step cannot be 0");
        } else if (from == toExclusive || step * (from - toExclusive) > 0) {
            return TreeSet.empty();
        } else {
            final int one = (from < toExclusive) ? 1 : -1;
            return TreeSet.rangeClosedBy(from, toExclusive - one, step);
        }
    }