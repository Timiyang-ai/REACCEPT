public CriteriaQuery sizeNotEq(String propName, int size) {
        criterion = criterion.and(criterionBuilder.sizeNotEq(propName, size));
        return this;
    }