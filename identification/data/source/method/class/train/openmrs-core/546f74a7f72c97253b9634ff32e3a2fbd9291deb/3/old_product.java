@Override
	public boolean isConceptNameDuplicate(ConceptName name) {
		if (name.isVoided()) {
			return false;
		}
		if (name.getConcept() != null) {
			if (name.getConcept().isRetired()) {
				return false;
			}
			
			//If it is not a default name for a concept
			if (!name.getConcept().getName(name.getLocale()).equals(name)) {
				return false;
			}
		}
		
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ConceptName.class);
		
		criteria.add(Restrictions.eq("voided", false));
		criteria.add(Restrictions.or(Restrictions.eq("locale", name.getLocale()), Restrictions.eq("locale", new Locale(name
		        .getLocale().getLanguage()))));
		if (Context.getConceptService().isConceptNameTableCaseSensitive()) {
			criteria.add(Restrictions.ilike("name", name.getName()));
		} else {
			criteria.add(Restrictions.eq("name", name.getName()));
		}
		
		List<ConceptName> candidateNames = criteria.list();
		
		for (ConceptName candidateName : candidateNames) {
			if (candidateName.getConcept().isRetired()) {
				continue;
			}
			if (candidateName.getConcept().equals(name.getConcept())) {
				continue;
			}
			
			//If it is a default name for a concept
			if (candidateName.getConcept().getName(candidateName.getLocale()).equals(candidateName)) {
				return true;
			}
		}
		
		return false;
	}