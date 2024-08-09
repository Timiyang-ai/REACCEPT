public CriteriaQuery sizeLt(String propName, int size) {
        addCriterion(criterionBuilder.sizeLt(propName, size));
        return this;
    }