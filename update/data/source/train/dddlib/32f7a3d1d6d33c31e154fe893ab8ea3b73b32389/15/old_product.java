public CriteriaQuery lt(String propName, Comparable<?> value) {
        addCriterion(criterionBuilder.lt(propName, value));
        return this;
    }