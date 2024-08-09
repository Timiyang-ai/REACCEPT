public Result earliest() {
		if (isSingleResult()) {
			return this;
		}
		
		Result first = emptyResult();
		
		// default the returned result to the first item
		// in case all resultDates are null
		if (size() > 0) {
			first = get(0);
		}
		
		for (Result r : this) {
			if (r != null && r.getResultDate() != null
			        && (first.getResultDate() == null || r.getResultDate().before(first.getResultDate()))) {
				first = r;
			}
		}
		return first;
	}