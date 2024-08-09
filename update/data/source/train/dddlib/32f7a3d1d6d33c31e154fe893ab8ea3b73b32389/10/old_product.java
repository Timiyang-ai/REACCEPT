public CriteriaQuery geProp(String propName, String otherProp) {
        addCriterion(criterionBuilder.geProp(propName, otherProp));
        return this;
    }