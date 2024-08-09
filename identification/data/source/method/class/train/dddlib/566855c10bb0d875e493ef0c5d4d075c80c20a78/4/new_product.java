public CriteriaQuery isFalse(String propName) {
        criterion = criterion.and(Criteria.isFalse(propName));
        return this;
    }