public CriteriaQuery sizeNotEq(String propName, int size) {
        addCriterion(criterionBuilder.sizeNotEq(propName, size));
        return this;
    }