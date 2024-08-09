public CriteriaQuery notEq(String propName, Object value) {
        addCriterion(criterionBuilder.notEq(propName, value));
        return this;
    }