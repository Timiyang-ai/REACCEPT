@SuppressWarnings("unchecked")
	public List<Drug> getDrugs(String phrase) throws DAOException {
		List<String> words = ConceptWord.getUniqueWords(phrase);
		List<Drug> conceptDrugs = new Vector<Drug>();
		
		if (words.size() > 0) {
			
			Criteria searchCriteria = sessionFactory.getCurrentSession().createCriteria(Drug.class, "drug");
			
			Iterator<String> word = words.iterator();
			searchCriteria.add(Restrictions.like("name", word.next(), MatchMode.ANYWHERE));
			while (word.hasNext()) {
				String w = word.next();
				log.debug(w);
				searchCriteria.add(Restrictions.like("name", w, MatchMode.ANYWHERE));
			}
			searchCriteria.add(Restrictions.eq("drug.retired", false)); /* exclude retired drugs */
			searchCriteria.addOrder(Order.asc("drug.concept"));
			conceptDrugs = searchCriteria.list();
		}
		
		return conceptDrugs;
	}