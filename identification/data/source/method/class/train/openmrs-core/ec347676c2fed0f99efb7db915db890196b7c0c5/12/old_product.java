@Override
	public List<Drug> getDrugs(final String phrase) throws DAOException {
		if (phrase.trim().isEmpty()) {
			return Collections.emptyList();
		}
		
		final String escapedPhrase = LuceneQuery.escapeQuery(phrase);
		
		List<String> notEmptyWords = new ArrayList<String>();
		String[] words = escapedPhrase.split(" ");
		for (String word : words) {
			if (!word.trim().isEmpty()) {
				notEmptyWords.add(word);
			}
		}
		
		String query = "name:(\"" + escapedPhrase + "\")^0.6 OR name:(" + escapedPhrase + ")^0.2";
		
		if (!notEmptyWords.isEmpty()) {
			query += "OR name:(" + StringUtils.join(notEmptyWords, "* ") + "*)^0.1 OR name:("
			        + StringUtils.join(notEmptyWords, "~0.8 ") + "~0.8)^0.1";
		}
		
		List<Drug> list = LuceneQuery.newQuery(query, sessionFactory.getCurrentSession(), Drug.class).exclude("retired",
		    true).list();
		
		return list;
	}