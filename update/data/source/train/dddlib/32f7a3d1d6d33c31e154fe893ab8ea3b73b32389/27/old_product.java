public CriteriaQuery le(String propName, Comparable<?> value) {
        addCriterion(criterionBuilder.le(propName, value));
        return this;
    }