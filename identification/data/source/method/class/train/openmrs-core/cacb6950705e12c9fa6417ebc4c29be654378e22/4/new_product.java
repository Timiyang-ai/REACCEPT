String[] getQueryParts(String query) {
		if (query == null) {
			throw new IllegalArgumentException("query must not be null");
		}
		
		query = query.replace(",", " ");
		String[] queryPartArray = query.split(" ");
		
		List<String> queryPartList = new ArrayList<>();
		for (String queryPart : queryPartArray) {
			if (queryPart.trim().length() > 0) {
				queryPartList.add(queryPart);
			}
		}
		
		return queryPartList.toArray(new String[0]);
	}