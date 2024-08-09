public CriteriaQuery eqProp(String propName, String otherProp) {
        addCriterion(criterionBuilder.eqProp(propName, otherProp));
        return this;
    }