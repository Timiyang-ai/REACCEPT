public static boolean containsAll(final Collection<?> coll1, final Collection<?> coll2) {
        if (coll2.isEmpty()) {
            return true;
        } else {
            final SetOperationCardinalityHelper<Object> helper =
                    new SetOperationCardinalityHelper<Object>(coll1, coll2);
            for (final Object obj : helper) {
                helper.setCardinality(obj, helper.min(obj));
            }
            return helper.list().size() == helper.sizeB();
        }
    }