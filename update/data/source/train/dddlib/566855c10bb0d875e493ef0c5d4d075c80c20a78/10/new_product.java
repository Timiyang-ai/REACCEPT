public CriteriaQuery sizeLe(String propName, int size) {
        criterion = criterion.and(Criteria.sizeLe(propName, size));
        return this;
    }