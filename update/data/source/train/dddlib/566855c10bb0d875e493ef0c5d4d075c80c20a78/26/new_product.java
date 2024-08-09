public CriteriaQuery containsText(String propName, String value) {
        criterion = criterion.and(Criteria.containsText(propName, value));
        return this;
    }