public static <T> T[] removeElements(final T[] array, final T... values) {
        if (isEmpty(array) || isEmpty(values)) {
            return clone(array);
        }
        final HashMap<T, MutableInt> occurrences = new HashMap<T, MutableInt>(values.length);
        for (final T v : values) {
            final MutableInt count = occurrences.get(v);
            if (count == null) {
                occurrences.put(v, new MutableInt(1));
            } else {
                count.increment();
            }
        }
        final BitSet toRemove = new BitSet();
        for (final Map.Entry<T, MutableInt> e : occurrences.entrySet()) {
            final T v = e.getKey();
            int found = 0;
            for (int i = 0, ct = e.getValue().intValue(); i < ct; i++) {
                found = indexOf(array, v, found);
                if (found < 0) {
                    break;
                }
                toRemove.set(found++);
            }
        }
        @SuppressWarnings("unchecked") // removeAll() always creates an array of the same type as its input
        final
        T[] result = (T[]) removeAll(array, toRemove);
        return result;
    }