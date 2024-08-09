@SuppressWarnings("unchecked")
	public List<Drug> getDrugs(String phrase) throws DAOException {
		List<String> words = ConceptWord.getUniqueWords(phrase); 
		List<Drug> conceptDrugs = new Vector<Drug>();
		
		if (words.size() > 0) {
		
			Criteria searchCriteria = sessionFactory.getCurrentSession().createCriteria(Drug.class, "drug");
			
			searchCriteria.add(Expression.eq("drug.retired", false));
			
			Iterator<String> word = words.iterator();
			searchCriteria.add(Expression.like("name", word.next(), MatchMode.ANYWHERE));
			while (word.hasNext()) {
				String w = word.next();
				log.debug(w);
				searchCriteria.add(Expression.like("name", w, MatchMode.ANYWHERE));
			}
			searchCriteria.addOrder(Order.asc("drug.concept"));
			conceptDrugs = searchCriteria.list();
		}

		return conceptDrugs;
	}