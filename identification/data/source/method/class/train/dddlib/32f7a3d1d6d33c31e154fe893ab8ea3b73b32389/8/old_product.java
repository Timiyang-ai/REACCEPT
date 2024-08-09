public CriteriaQuery not(QueryCriterion criterion) {
        addCriterion(criterionBuilder.not(criterion));
        return this;
    }