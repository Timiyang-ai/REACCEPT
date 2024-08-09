@Override
	public boolean isConceptNameDuplicate(ConceptName name) {
		if (name.isVoided()) {
			return false;
		}
		if (name.getConcept() != null) {
			if (name.getConcept().isRetired()) {
				return false;
			}
			
			//If it is not a default name of a concept, it cannot be a duplicate.
			//Note that a concept may not have a default name for the given locale, if just a short name or
			//a search term is set.
			if (!name.equals(name.getConcept().getName(name.getLocale()))) {
				return false;
			}
		}
		
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ConceptName.class);
		
		criteria.add(Restrictions.eq("voided", false));
		criteria.add(Restrictions.or(Restrictions.eq("locale", name.getLocale()), Restrictions.eq("locale", new Locale(name
		        .getLocale().getLanguage()))));
		if (Context.getAdministrationService().isDatabaseStringComparisonCaseSensitive()) {
			criteria.add(Restrictions.eq("name", name.getName()).ignoreCase());
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