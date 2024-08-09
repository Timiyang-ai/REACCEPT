@Override
	public List<Drug> getDrugs(final String phrase) throws DAOException {
		List<Drug> list = new LuceneQueryBuilder<Drug>(
		                                               sessionFactory.getCurrentSession()) {
			
			@Override
			protected org.apache.lucene.search.Query prepareQuery() throws ParseException {
				String searchPhrase = newRequirePartialWordsSearchPhrase(phrase);
				
				String query = "+name:(" + searchPhrase + ")";
				
				return newQueryParser().parse(query);
			}
		}.list();
		
		return list;
	}