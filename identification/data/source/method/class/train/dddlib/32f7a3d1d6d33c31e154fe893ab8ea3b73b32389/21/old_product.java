public CriteriaQuery sizeLe(String propName, int size) {
        addCriterion(criterionBuilder.sizeLe(propName, size));
        return this;
    }