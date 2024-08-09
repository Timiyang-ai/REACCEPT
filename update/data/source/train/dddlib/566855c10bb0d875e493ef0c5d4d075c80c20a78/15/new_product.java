public CriteriaQuery leProp(String propName, String otherProp) {
        criterion = criterion.and(Criteria.leProp(propName, otherProp));
        return this;
    }