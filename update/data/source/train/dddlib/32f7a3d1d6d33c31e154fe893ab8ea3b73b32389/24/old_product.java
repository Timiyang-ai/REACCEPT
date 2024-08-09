public CriteriaQuery startsWithText(String propName, String value) {
        addCriterion(criterionBuilder.startsWithText(propName, value));
        return this;
    }