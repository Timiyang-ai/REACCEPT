public CriteriaQuery sizeEq(String propName, int size) {
        addCriterion(criterionBuilder.sizeEq(propName, size));
        return this;
    }