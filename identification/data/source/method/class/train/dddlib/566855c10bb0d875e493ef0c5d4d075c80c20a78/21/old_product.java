public CriteriaQuery ge(String propName, Comparable<?> value) {
        criterion = criterion.and(criterionBuilder.ge(propName, value));
        return this;
    }