public CriteriaQuery eq(String propName, Object value) {
    	criterion = criterion.and(criterionBuilder.eq(propName, value));
        return this;
    }