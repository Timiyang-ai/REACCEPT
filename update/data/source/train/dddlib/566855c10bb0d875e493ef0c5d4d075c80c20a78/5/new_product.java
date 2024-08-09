public CriteriaQuery ltProp(String propName, String otherProp) {
        criterion = criterion.and(Criteria.ltProp(propName, otherProp));
        return this;
    }