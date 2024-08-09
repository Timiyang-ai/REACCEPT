@Override
	public List<Drug> getDrugs(final String phrase) throws DAOException {
		if (phrase.trim().isEmpty()) {
			return Collections.emptyList();
		}
		
		List<String> notEmptyWords = new ArrayList<String>();
		String[] words = phrase.split(" ");
		for (String word : words) {
			if (!word.isEmpty()) {
				notEmptyWords.add(word);
			}
		}
		
		String query = "name:(\"" + phrase + "\")^0.6 OR name:(" + phrase + ")^0.2 OR name:("
		        + StringUtils.join(notEmptyWords, "* OR ") + "*)^0.1 OR name:("
		        + StringUtils.join(notEmptyWords, "~0.2 OR ") + "~0.2)^0.1";
		
		List<Drug> list = LuceneQuery.newQuery(query, sessionFactory.getCurrentSession(), Drug.class).list();
		
		return list;
	}