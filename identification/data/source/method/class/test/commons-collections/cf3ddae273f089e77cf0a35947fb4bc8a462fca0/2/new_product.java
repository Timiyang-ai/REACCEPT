public static <O> Map<O, Integer> getCardinalityMap(final Iterable<? extends O> coll) {
        Map<O, Integer> count = new HashMap<O, Integer>();
        for (O obj : coll) {
            Integer c = count.get(obj);
            if (c == null) {
                count.put(obj, 1);
            } else {
                count.put(obj, c + 1);
            }
        }
        return count;
    }