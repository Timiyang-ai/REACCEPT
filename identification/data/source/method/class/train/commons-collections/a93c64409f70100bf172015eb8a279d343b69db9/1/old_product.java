public static Collection union(final Collection a, final Collection b) {
        SetOperationCardinalityHelper helper = new SetOperationCardinalityHelper(a, b);
        for (Object obj : helper) {
            helper.setCardinality(obj, helper.max(obj));
        }
        return helper.list();
    }