public CriteriaQuery notEqProp(String propName, String otherProp) {
        criterion = criterion.and(Criteria.notEqProp(propName, otherProp));
        return this;
    }