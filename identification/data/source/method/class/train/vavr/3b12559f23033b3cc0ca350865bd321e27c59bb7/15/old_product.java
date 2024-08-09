static List<Long> rangeClosed(long from, long toInclusive) {
        if (from > toInclusive) {
            return Nil.instance();
        } else if (toInclusive == Long.MIN_VALUE) {
            return List.of(Long.MIN_VALUE);
        } else {
            List<Long> result = Nil.instance();
            for (long i = toInclusive; i >= from; i--) {
                result = result.prepend(i);
            }
            return result;
        }
    }