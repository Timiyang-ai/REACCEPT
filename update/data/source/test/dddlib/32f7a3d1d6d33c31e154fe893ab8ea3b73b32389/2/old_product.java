public CriteriaQuery leProp(String propName, String otherProp) {
        addCriterion(criterionBuilder.leProp(propName, otherProp));
        return this;
    }