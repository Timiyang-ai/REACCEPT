public static <O> Collection<O> union(final Iterable<? extends O> a, final Iterable<? extends O> b) {
        SetOperationCardinalityHelper<O> helper = new SetOperationCardinalityHelper<O>(a, b);
        for (O obj : helper) {
            helper.setCardinality(obj, helper.max(obj));
        }
        return helper.list();
    }