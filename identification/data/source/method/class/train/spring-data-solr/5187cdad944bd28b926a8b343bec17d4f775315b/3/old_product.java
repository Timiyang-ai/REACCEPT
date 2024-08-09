public Criteria or(Criteria criteria) {
		Assert.notNull(criteria, "Cannot chain 'null' criteria.");

		Criteria orConnectedCriteria = new OrCriteria(this.criteriaChain, criteria.getField());
		orConnectedCriteria.criteria.addAll(criteria.criteria);

		for (Criteria c : criteria.getCriteriaChain()) {
			if (c == criteria) {
				continue;
			}
			orConnectedCriteria.and(c);
		}

		return orConnectedCriteria;
	}