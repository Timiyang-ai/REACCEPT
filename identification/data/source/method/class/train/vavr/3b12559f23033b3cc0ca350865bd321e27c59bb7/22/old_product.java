static List<Integer> rangeClosed(int from, int toInclusive) {
        if (from > toInclusive) {
            return Nil.instance();
        } else if (toInclusive == Integer.MIN_VALUE) {
            return List.of(Integer.MIN_VALUE);
        } else {
            List<Integer> result = Nil.instance();
            for (int i = toInclusive; i >= from; i--) {
                result = result.prepend(i);
            }
            return result;
        }
    }