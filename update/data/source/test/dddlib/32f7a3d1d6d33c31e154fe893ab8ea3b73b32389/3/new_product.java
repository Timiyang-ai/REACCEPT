public CriteriaQuery notNull(String propName) {
        criterion = criterion.and(criterionBuilder.notNull(propName));
        return this;
    }