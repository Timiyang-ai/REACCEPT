public static Collection intersection(final Collection a, final Collection b) {
        SetOperationCardinalityHelper helper = new SetOperationCardinalityHelper(a, b);
        for (Object obj : helper) {
            helper.setCardinality(obj, helper.min(obj));
        }
        return helper.list();
    }