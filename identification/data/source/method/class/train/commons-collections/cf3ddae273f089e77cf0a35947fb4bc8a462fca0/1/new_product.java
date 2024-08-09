public static <T> void filter(Iterable<T> collection, Predicate<? super T> predicate) {
        if (collection != null && predicate != null) {
            for (Iterator<T> it = collection.iterator(); it.hasNext();) {
                if (!predicate.evaluate(it.next())) {
                    it.remove();
                }
            }
        }
    }