public CriteriaQuery gtProp(String propName, String otherProp) {
    	criterion = criterion.and(criterionBuilder.gtProp(propName, otherProp));
        return this;
    }