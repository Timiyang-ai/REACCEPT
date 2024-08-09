public CriteriaQuery sizeGt(String propName, int size) {
        criterion = criterion.and(criterionBuilder.sizeGt(propName, size));
        return this;
    }