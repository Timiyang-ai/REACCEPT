public CriteriaQuery containsText(String propName, String value) {
        criterion = criterion.and(criterionBuilder.containsText(propName, value));
        return this;
    }