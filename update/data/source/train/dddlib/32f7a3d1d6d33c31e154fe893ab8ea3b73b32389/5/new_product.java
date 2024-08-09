public CriteriaQuery sizeLe(String propName, int size) {
        criterion = criterion.and(criterionBuilder.sizeLe(propName, size));
        return this;
    }