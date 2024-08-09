public static <T> T[] removeElements(T[] array, T... values) {
        if (isEmpty(array) || isEmpty(values)) {
            return clone(array);
        }
        HashMap<T, MutableInt> occurrences = new HashMap<T, MutableInt>(values.length);
        for (T v : values) {
            MutableInt count = occurrences.get(v);
            if (count == null) {
                occurrences.put(v, new MutableInt(1));
            } else {
                count.increment();
            }
        }
        HashSet<Integer> toRemove = new HashSet<Integer>();
        for (Map.Entry<T, MutableInt> e : occurrences.entrySet()) {
            T v = e.getKey();
            int found = 0;
            for (int i = 0, ct = e.getValue().intValue(); i < ct; i++) {
                found = indexOf(array, v, found);
                if (found < 0) {
                    break;
                }
                toRemove.add(found++);
            }
        }
        return removeAll(array, extractIndices(toRemove));
    }