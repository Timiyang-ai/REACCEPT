public CriteriaQuery sizeGt(String propName, int size) {
        criterion = criterion.and(Criteria.sizeGt(propName, size));
        return this;
    }