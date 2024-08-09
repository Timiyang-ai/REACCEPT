public CriteriaQuery sizeLt(String propName, int size) {
        criterion = criterion.and(Criteria.sizeLt(propName, size));
        return this;
    }