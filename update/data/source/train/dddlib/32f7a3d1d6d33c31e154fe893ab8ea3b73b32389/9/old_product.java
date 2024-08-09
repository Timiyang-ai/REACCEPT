public CriteriaQuery gt(String propName, Comparable<?> value) {
        addCriterion(criterionBuilder.gt(propName, value));
        return this;
    }