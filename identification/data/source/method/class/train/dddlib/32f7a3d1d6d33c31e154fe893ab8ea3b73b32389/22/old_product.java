public CriteriaQuery ltProp(String propName, String otherProp) {
        addCriterion(criterionBuilder.ltProp(propName, otherProp));
        return this;
    }