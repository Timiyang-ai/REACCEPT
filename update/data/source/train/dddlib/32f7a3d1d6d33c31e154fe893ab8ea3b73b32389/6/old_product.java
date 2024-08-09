public CriteriaQuery gtProp(String propName, String otherProp) {
        addCriterion(criterionBuilder.gtProp(propName, otherProp));
        return this;
    }