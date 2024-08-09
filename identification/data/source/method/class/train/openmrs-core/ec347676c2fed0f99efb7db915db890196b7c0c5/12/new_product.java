@Override
	public List<Drug> getDrugs(final String phrase) throws DAOException {
		if (StringUtils.isBlank(phrase)) {
			return Collections.emptyList();
		}
		
		final String escapedPhrase = LuceneQuery.escapeQuery(phrase.trim());
		
		List<String> tokenizedPhrase = new ArrayList<String>();
		String[] words = escapedPhrase.split(" ");
		for (String word : words) {
			word = word.trim();
			if (!word.isEmpty()) {
				tokenizedPhrase.add(word);
			}
		}
		
		StringBuilder query = newNameQuery(tokenizedPhrase, escapedPhrase, true);
		
		List<Drug> list = LuceneQuery.newQuery(query.toString(), sessionFactory.getCurrentSession(), Drug.class).exclude(
		    "retired", true).list();
		
		return list;
	}