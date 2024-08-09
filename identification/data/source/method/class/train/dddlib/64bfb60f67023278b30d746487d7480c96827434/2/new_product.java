public CriteriaQuery or(QueryCriterion aCriterion) {
        if (aCriterion == null) {
            return this;
        }
        criterion = criterion.or(aCriterion);
        return this;
    }