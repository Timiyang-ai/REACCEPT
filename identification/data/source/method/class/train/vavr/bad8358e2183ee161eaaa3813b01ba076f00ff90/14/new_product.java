static List<Integer> rangeClosedBy(int from, int toInclusive, int step) {
        if (step == 0) {
            throw new IllegalArgumentException("step cannot be 0");
        } else if (from == toInclusive) {
            return List.of(from);
        } else if (step * (from - toInclusive) > 0) {
            return List.empty();
        } else {
            final int gap = (from - toInclusive) % step;
            final int signum = (from < toInclusive) ? -1 : 1;
            final int bound = from * signum;
            List<Integer> result = List.empty();
            for (int i = toInclusive + gap; i * signum <= bound; i -= step) {
                result = result.prepend(i);
            }
            return result;
        }
    }