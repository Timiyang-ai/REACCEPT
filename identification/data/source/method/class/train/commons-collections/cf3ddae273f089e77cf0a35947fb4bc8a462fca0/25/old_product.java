public static void filter(Collection collection, Predicate predicate) {
        if (collection != null && predicate != null) {
            for (Iterator it = collection.iterator(); it.hasNext();) {
                if (predicate.evaluate(it.next()) == false) {
                    it.remove();
                }
            }
        }
    }