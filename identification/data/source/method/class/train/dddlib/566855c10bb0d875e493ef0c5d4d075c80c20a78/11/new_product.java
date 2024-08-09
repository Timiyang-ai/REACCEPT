public CriteriaQuery isTrue(String propName) {
        criterion = criterion.and(Criteria.isTrue(propName));
        return this;
    }