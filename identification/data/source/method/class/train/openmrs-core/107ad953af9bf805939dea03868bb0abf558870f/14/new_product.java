@SuppressWarnings("unchecked")
	public List<Obs> getObservations(Concept question, String sort, Integer personType) {
		
		if (sort == null || sort.equals(""))
			sort = "obsId";
		
		Query query = sessionFactory.getCurrentSession().createQuery(
				"select obs " + getHqlPersonModifier(personType, "obs.concept = :c and obs.voided = false") + 
				" order by obs." + sort)
				.setParameter("c", question);

		return query.list();
	}