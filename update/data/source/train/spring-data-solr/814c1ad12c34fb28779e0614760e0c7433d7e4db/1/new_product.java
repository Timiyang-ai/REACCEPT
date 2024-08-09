public Criteria fuzzy(String s, float levenshteinDistance) {
		if (!Float.isNaN(levenshteinDistance) && (levenshteinDistance < 0 || levenshteinDistance > 1)) {
			throw new InvalidDataAccessApiUsageException("Levenshtein Distance has to be within its bounds (0.0 - 1.0).");
		}
		criteria.add(new CriteriaEntry(OperationKey.FUZZY, new Object[] { s, levenshteinDistance }));
		return this;
	}