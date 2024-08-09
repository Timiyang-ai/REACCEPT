public CriteriaQuery isNull(String propName) {
        criterion = criterion.and(Criteria.isNull(propName));
        return this;
    }