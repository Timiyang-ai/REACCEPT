public CriteriaQuery leProp(String propName, String otherProp) {
        criterion = criterion.and(criterionBuilder.leProp(propName, otherProp));
        return this;
    }