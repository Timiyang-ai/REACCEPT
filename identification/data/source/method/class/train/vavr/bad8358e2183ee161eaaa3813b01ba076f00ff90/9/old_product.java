static List<Long> rangeClosedBy(long from, long toInclusive, long step) {
        if (step == 0) {
            throw new IllegalArgumentException("step cannot be 0");
        } else if (from == toInclusive) {
            return List.of(from);
        } else if (step * (from - toInclusive) > 0) {
            return List.empty();
        } else {
            return List.ofAll(() -> Iterator.rangeClosedBy(from, toInclusive, step));
        }
    }