public CriteriaQuery sizeGe(String propName, int size) {
        addCriterion(criterionBuilder.sizeGe(propName, size));
        return this;
    }