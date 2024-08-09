public CriteriaQuery notEq(String propName, Object value) {
        criterion = criterion.and(Criteria.notEq(propName, value));
        return this;
    }