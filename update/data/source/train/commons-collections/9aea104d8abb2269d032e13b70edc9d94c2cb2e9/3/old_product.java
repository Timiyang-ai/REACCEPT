public static <T> boolean filter(Iterable<T> collection, Predicate<? super T> predicate) {
        boolean result = false;
        if (collection != null && predicate != null) {
            for (Iterator<T> it = collection.iterator(); it.hasNext();) {
                if (!predicate.evaluate(it.next())) {
                    it.remove();
                    result = true;
                }
            }
        }
        return result;
    }