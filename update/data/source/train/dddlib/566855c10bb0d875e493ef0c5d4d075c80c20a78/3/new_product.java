public CriteriaQuery gtProp(String propName, String otherProp) {
        criterion = criterion.and(Criteria.gtProp(propName, otherProp));
        return this;
    }