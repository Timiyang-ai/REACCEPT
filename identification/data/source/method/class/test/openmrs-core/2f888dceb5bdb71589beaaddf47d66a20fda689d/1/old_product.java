@SuppressWarnings("unchecked")
	public List<Concept> getConcepts(String name, Locale loc, boolean searchOnPhrase, List<ConceptClass> classes,
	        List<ConceptDatatype> datatypes) throws DAOException {
		
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Concept.class);
		
		criteria.add(Restrictions.eq("retired", false));
		
		if (!StringUtils.isBlank(name)) {
			if (loc == null)
				// TRUNK-2730 replaces this behavior with use of the default locale
				// throw new DAOException("Locale must be not null");
				loc = Context.getLocale();
			
			String caseSensitiveNamesInConceptNameTable = Context.getAdministrationService().getGlobalProperty(
			    OpenmrsConstants.GP_CASE_SENSITIVE_NAMES_IN_CONCEPT_NAME_TABLE, "true");
			
			criteria.createAlias("names", "names");
			MatchMode matchmode = MatchMode.EXACT;
			if (searchOnPhrase)
				matchmode = MatchMode.ANYWHERE;
			
			if (Boolean.valueOf(caseSensitiveNamesInConceptNameTable)) {
				criteria.add(Restrictions.ilike("names.name", name, matchmode));
			} else {
				if (searchOnPhrase) {
					criteria.add(Restrictions.like("names.name", name, matchmode));
				} else {
					criteria.add(Restrictions.eq("names.name", name));
				}
			}
			
			criteria.add(Restrictions.eq("names.voided", false));
			
			String language = loc.getLanguage();
			if (language.length() > 2) {
				// if searching in specific locale like en_US
				criteria.add(Restrictions.or(Restrictions.eq("names.locale", loc), Restrictions.eq("names.locale",
				    new Locale(loc.getLanguage().substring(0, 2)))));
			} else {
				// if searching in general locale like just "en"
				//	criteria.add(Restrictions.like("names.locale", loc.getLanguage(), MatchMode.START));
			}
		}
		
		if (classes.size() > 0)
			criteria.add(Restrictions.in("conceptClass", classes));
		
		if (datatypes.size() > 0)
			criteria.add(Restrictions.in("datatype", datatypes));
		
		return criteria.list();
	}