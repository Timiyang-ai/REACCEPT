public CriteriaQuery geProp(String propName, String otherProp) {
        criterion = criterion.and(Criteria.geProp(propName, otherProp));
        return this;
    }