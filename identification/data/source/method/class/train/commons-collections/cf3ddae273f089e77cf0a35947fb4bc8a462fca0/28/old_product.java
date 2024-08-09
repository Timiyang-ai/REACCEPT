public static Object find(Collection collection, Predicate predicate) {
        if (collection != null && predicate != null) {
            for (Iterator iter = collection.iterator(); iter.hasNext();) {
                Object item = iter.next();
                if (predicate.evaluate(item)) {
                    return item;
                }
            }
        }
        return null;
    }