public CriteriaQuery notEq(String propName, Object value) {
    	criterion = criterion.and(criterionBuilder.notEq(propName, value));
        return this;
    }