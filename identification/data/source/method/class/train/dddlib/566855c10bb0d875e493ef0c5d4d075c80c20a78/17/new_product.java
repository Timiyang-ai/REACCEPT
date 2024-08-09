public CriteriaQuery eq(String propName, Object value) {
        criterion = criterion.and(Criteria.eq(propName, value));
        return this;
    }