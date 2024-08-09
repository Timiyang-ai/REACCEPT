public CriteriaQuery ltProp(String propName, String otherProp) {
        criterion = criterion.and(criterionBuilder.ltProp(propName, otherProp));
        return this;
    }