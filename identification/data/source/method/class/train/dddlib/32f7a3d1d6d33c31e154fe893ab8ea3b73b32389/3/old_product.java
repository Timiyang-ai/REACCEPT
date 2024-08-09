public CriteriaQuery notEqProp(String propName, String otherProp) {
        addCriterion(criterionBuilder.notEqProp(propName, otherProp));
        return this;
    }