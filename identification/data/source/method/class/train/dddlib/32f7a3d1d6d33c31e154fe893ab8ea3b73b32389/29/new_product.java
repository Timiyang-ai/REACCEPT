public CriteriaQuery sizeGe(String propName, int size) {
        criterion = criterion.and(criterionBuilder.sizeGe(propName, size));
        return this;
    }