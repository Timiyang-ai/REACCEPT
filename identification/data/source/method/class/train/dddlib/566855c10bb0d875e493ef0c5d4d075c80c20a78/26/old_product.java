public CriteriaQuery eqProp(String propName, String otherProp) {
        criterion = criterion.and(criterionBuilder.eqProp(propName, otherProp));
        return this;
    }