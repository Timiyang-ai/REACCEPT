public CriteriaQuery sizeLt(String propName, int size) {
        criterion = criterion.and(criterionBuilder.sizeLt(propName, size));
        return this;
    }