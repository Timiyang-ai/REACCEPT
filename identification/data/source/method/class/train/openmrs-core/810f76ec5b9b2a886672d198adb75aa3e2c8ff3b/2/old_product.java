public Result earliest() {
		if (isSingleResult())
			return this;
		Result first = emptyResult();
		for (Result r : this) {
			if (r != null && r.getResultDate() != null
			        && (first.getResultDate() == null || r.getResultDate().before(first.getResultDate()))) {
				first = r;
			}
		}
		return first;
	}