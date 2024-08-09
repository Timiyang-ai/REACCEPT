static Array<Long> rangeClosedBy(long from, long toInclusive, long step) {
        if (step == 0) {
            throw new IllegalArgumentException("step cannot be 0");
        } else if (from == toInclusive) {
            return Array.of(from);
        } else if (step * (from - toInclusive) > 0) {
            return Array.empty();
        } else {
            final Long[] arr = new Long[(int) ((toInclusive - from) / step + 1)];
            int p = 0;
            if (step > 0) {
                long i = from;
                while (i <= toInclusive) {
                    arr[p++] = i;
                    if (Long.MAX_VALUE - step < i) {
                        break;
                    }
                    i += step;
                }
            } else {
                long i = from;
                while (i >= toInclusive) {
                    arr[p++] = i;
                    if (Long.MIN_VALUE - step > i) {
                        break;
                    }
                    i += step;
                }
            }
            if (p < arr.length) {
                return wrap(Arrays.copyOf(arr, p));
            } else {
                return wrap(arr);
            }
        }
    }