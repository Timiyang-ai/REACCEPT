public static int countMatches(Collection inputCollection, Predicate predicate) {
        int count = 0;
        if (inputCollection != null && predicate != null) {
            for (Iterator it = inputCollection.iterator(); it.hasNext();) {
                if (predicate.evaluate(it.next())) {
                    count++;
                }
            }
        }
        return count;
    }