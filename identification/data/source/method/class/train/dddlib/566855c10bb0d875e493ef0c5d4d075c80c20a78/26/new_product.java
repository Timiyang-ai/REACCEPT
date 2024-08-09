public CriteriaQuery eqProp(String propName, String otherProp) {
        criterion = criterion.and(Criteria.eqProp(propName, otherProp));
        return this;
    }