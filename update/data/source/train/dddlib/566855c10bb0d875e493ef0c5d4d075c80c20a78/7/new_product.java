public CriteriaQuery notNull(String propName) {
        criterion = criterion.and(Criteria.notNull(propName));
        return this;
    }