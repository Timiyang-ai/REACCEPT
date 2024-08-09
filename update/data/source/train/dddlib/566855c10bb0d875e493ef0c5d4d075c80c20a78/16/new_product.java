public CriteriaQuery isBlank(String propName) {
        criterion = criterion.and(Criteria.isBlank(propName));
        return this;
    }