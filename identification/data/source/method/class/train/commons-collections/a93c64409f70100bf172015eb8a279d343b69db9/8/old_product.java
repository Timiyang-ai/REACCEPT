public static Collection disjunction(final Collection a, final Collection b) {
        SetOperationCardinalityHelper helper = new SetOperationCardinalityHelper(a, b);
        for (Object obj : helper) {
            helper.setCardinality(obj, helper.max(obj) - helper.min(obj));
        }
        return helper.list();
    }