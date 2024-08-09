public CriteriaQuery notEqProp(String propName, String otherProp) {
        criterion = criterion.and(criterionBuilder.notEqProp(propName, otherProp));
        return this;
    }