public static boolean containsAll(final Collection<?> coll1, final Collection<?> coll2) {
        if (coll2.isEmpty()) {
            return true;
        } else {
            Collection<Object> result = CollectionUtils.<Object>intersection(coll1, coll2);
            return result.size() == coll2.size();
        }
    }