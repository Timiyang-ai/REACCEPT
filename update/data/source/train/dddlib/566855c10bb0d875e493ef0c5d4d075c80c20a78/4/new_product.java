public CriteriaQuery startsWithText(String propName, String value) {
        criterion = criterion.and(Criteria.startsWithText(propName, value));
        return this;
    }