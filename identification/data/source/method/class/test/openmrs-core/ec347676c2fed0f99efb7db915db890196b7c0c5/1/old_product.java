@Override
	public List<Drug> getDrugs(final String phrase) throws DAOException {
		String searchPhrase = newRequirePartialWordsSearchPhrase(phrase);
		
		String query = "+name:(" + searchPhrase + ")";
		
		List<Drug> list = LuceneQuery.newQuery(query, sessionFactory.getCurrentSession(), Drug.class).list();
		
		return list;
	}