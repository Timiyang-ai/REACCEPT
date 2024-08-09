public CriteriaQuery ge(String propName, Comparable<?> value) {
        criterion = criterion.and(Criteria.ge(propName, value));
        return this;
    }