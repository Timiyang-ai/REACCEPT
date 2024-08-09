public CriteriaQuery sizeEq(String propName, int size) {
        criterion = criterion.and(criterionBuilder.sizeEq(propName, size));
        return this;
    }