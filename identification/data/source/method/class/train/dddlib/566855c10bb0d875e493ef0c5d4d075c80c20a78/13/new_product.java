public CriteriaQuery notBlank(String propName) {
        criterion = criterion.and(Criteria.notBlank(propName));
        return this;
    }