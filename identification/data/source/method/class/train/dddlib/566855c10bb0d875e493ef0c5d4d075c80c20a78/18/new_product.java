public CriteriaQuery notEmpty(String propName) {
        criterion = criterion.and(Criteria.notEmpty(propName));
        return this;
    }