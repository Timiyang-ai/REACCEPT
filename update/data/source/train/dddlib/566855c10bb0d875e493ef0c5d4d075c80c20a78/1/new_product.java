public CriteriaQuery sizeGe(String propName, int size) {
        criterion = criterion.and(Criteria.sizeGe(propName, size));
        return this;
    }