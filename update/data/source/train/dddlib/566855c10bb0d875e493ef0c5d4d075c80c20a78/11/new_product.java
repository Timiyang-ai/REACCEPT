public CriteriaQuery isEmpty(String propName) {
        criterion = criterion.and(Criteria.isEmpty(propName));
        return this;
    }