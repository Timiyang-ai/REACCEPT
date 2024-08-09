public CriteriaQuery sizeEq(String propName, int size) {
        criterion = criterion.and(Criteria.sizeEq(propName, size));
        return this;
    }