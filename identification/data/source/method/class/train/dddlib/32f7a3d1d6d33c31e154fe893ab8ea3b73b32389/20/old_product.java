public CriteriaQuery eq(String propName, Object value) {
        addCriterion(criterionBuilder.eq(propName, value));
        return this;
    }