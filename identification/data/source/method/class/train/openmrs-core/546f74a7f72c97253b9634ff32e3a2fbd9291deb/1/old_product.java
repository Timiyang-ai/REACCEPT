@Override
	public boolean isConceptNameDuplicate(ConceptName name) {
		if (!name.isFullySpecifiedName() || !name.isLocalePreferred()) {
			return false;
		}
		if (name.getConcept() != null && name.getConcept().isRetired()) {
			return false;
		}
		
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ConceptName.class);
		
		criteria.add(Restrictions.eq("voided", false));
		criteria.add(Restrictions.or(Restrictions.eq("locale", name.getLocale()), Restrictions.eq("locale", new Locale(name
		        .getLocale().getLanguage()))));
		if (Context.getConceptService().isConceptNameSearchCaseSensitive()) {
			criteria.add(Restrictions.ilike("name", name.getName()));
		} else {
			criteria.add(Restrictions.eq("name", name.getName()));
		}
		
		criteria.add(Restrictions.or(Restrictions.eq("conceptNameType", ConceptNameType.FULLY_SPECIFIED), Restrictions.eq(
		    "localePreferred", true)));
		
		criteria.createAlias("concept", "concept");
		criteria.add(Restrictions.eq("concept.retired", false));
		if (name.getConcept() != null && name.getConcept().getConceptId() != null) {
			criteria.add(Restrictions.ne("concept.conceptId", name.getConcept().getConceptId()));
		}
		
		criteria.setProjection(Projections.rowCount());
		long rowCount = ((Number) criteria.uniqueResult()).longValue();
		
		return rowCount != 0L;
	}