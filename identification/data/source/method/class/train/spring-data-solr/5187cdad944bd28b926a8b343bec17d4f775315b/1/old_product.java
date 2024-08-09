public Criteria and(String fieldname) {
		return new Criteria(this.criteriaChain, fieldname);
	}