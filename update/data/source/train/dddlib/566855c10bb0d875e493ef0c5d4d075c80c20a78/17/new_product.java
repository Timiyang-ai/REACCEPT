public CriteriaQuery sizeNotEq(String propName, int size) {
        criterion = criterion.and(Criteria.sizeNotEq(propName, size));
        return this;
    }