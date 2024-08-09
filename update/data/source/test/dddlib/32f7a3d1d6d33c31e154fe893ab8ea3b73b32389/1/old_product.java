public CriteriaQuery sizeGt(String propName, int size) {
        addCriterion(criterionBuilder.sizeGt(propName, size));
        return this;
    }