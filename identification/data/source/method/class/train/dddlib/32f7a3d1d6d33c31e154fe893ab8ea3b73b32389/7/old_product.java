public CriteriaQuery ge(String propName, Comparable<?> value) {
        addCriterion(criterionBuilder.ge(propName, value));
        return this;
    }