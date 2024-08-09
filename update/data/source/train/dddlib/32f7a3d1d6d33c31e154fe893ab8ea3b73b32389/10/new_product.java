public CriteriaQuery geProp(String propName, String otherProp) {
    	criterion = criterion.and(criterionBuilder.geProp(propName, otherProp));
        return this;
    }