public CriteriaQuery startsWithText(String propName, String value) {
        criterion = criterion.and(criterionBuilder.startsWithText(propName, value));
        return this;
    }