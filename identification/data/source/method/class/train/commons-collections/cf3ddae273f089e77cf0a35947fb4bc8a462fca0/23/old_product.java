public static boolean exists(Collection collection, Predicate predicate) {
        if (collection != null && predicate != null) {
            for (Iterator it = collection.iterator(); it.hasNext();) {
                if (predicate.evaluate(it.next())) {
                    return true;
                }
            }
        }
        return false;
    }