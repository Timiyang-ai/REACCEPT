static Array<Integer> rangeClosedBy(int from, int toInclusive, int step) {
        if (step == 0) {
            throw new IllegalArgumentException("step cannot be 0");
        } else if (from == toInclusive) {
            return Array.of(from);
        } else if (step * (from - toInclusive) > 0) {
            return Array.empty();
        } else {
            final Integer[] arr = new Integer[(toInclusive - from) / step + 1];
            int p = 0;
            if (step > 0) {
                int i = from;
                while (i <= toInclusive) {
                    arr[p++] = i;
                    if (Integer.MAX_VALUE - step < i) {
                        break;
                    }
                    i += step;
                }
            } else {
                int i = from;
                while (i >= toInclusive) {
                    arr[p++] = i;
                    if (Integer.MIN_VALUE - step > i) {
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