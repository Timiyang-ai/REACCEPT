public CriteriaQuery containsText(String propName, String value) {
        addCriterion(criterionBuilder.containsText(propName, value));
        return this;
    }