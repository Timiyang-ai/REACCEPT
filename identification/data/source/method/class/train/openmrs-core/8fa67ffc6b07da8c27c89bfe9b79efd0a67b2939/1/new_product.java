public List<Object> findBatchOfConcepts(String phrase, boolean includeRetired, List<String> includeClassNames,
	        List<String> excludeClassNames, List<String> includeDatatypeNames, List<String> excludeDatatypeNames,
	        Integer start, Integer length) {
		//TODO factor out the reusable code in this and findCountAndConcepts methods to a single utility method
		// List to return
		// Object type gives ability to return error strings
		Vector<Object> objectList = new Vector<Object>();
		
		// TODO add localization for messages
		
		Locale defaultLocale = Context.getLocale();
		
		// get the list of locales to search on
		List<Locale> searchLocales = Context.getAdministrationService().getSearchLocales();
		
		// debugging output
		if (log.isDebugEnabled()) {
			StringBuffer searchLocalesString = new StringBuffer();
			for (Locale loc : searchLocales) {
				searchLocalesString.append(loc.toString() + " ");
			}
			log.debug("searching locales: " + searchLocalesString);
		}
		
		if (includeClassNames == null) {
			includeClassNames = new Vector<String>();
		}
		if (excludeClassNames == null) {
			excludeClassNames = new Vector<String>();
		}
		if (includeDatatypeNames == null) {
			includeDatatypeNames = new Vector<String>();
		}
		if (excludeDatatypeNames == null) {
			excludeDatatypeNames = new Vector<String>();
		}
		
		try {
			ConceptService cs = Context.getConceptService();
			Set<ConceptSearchResult> searchResults = new HashSet<ConceptSearchResult>();
			
			if (phrase.matches("\\d+")) {
				// user searched on a number. Insert concept with
				// corresponding conceptId
				Concept c = cs.getConcept(Integer.valueOf(phrase));
				if (c != null && (!c.isRetired() || includeRetired)) {
					String conceptClassName = null;
					if (c.getConceptClass() != null) {
						conceptClassName = c.getConceptClass().getName();
					}
					String conceptDatatypeName = null;
					if (c.getDatatype() != null) {
						conceptDatatypeName = c.getDatatype().getName();
					}
					if ((includeClassNames.isEmpty() || includeClassNames.contains(conceptClassName))
					        && (excludeClassNames.isEmpty() || !excludeClassNames.contains(conceptClassName))
					        && (includeDatatypeNames.isEmpty() || includeDatatypeNames.contains(conceptDatatypeName))
					        && (excludeDatatypeNames.isEmpty() || !excludeDatatypeNames.contains(conceptDatatypeName))) {
						ConceptName cn = c.getName(defaultLocale);
						ConceptSearchResult searchResult = new ConceptSearchResult(phrase, c, cn);
						searchResults.add(searchResult);
					}
				}
			}
			
			if (!StringUtils.isBlank(phrase)) {
				// turn classnames into class objects
				List<ConceptClass> includeClasses = new Vector<ConceptClass>();
				for (String name : includeClassNames) {
					if (!"".equals(name)) {
						includeClasses.add(cs.getConceptClassByName(name));
					}
				}
				
				// turn classnames into class objects
				List<ConceptClass> excludeClasses = new Vector<ConceptClass>();
				for (String name : excludeClassNames) {
					if (!"".equals(name)) {
						excludeClasses.add(cs.getConceptClassByName(name));
					}
				}
				
				// turn classnames into class objects
				List<ConceptDatatype> includeDatatypes = new Vector<ConceptDatatype>();
				for (String name : includeDatatypeNames) {
					if (!"".equals(name)) {
						includeDatatypes.add(cs.getConceptDatatypeByName(name));
					}
				}
				
				// turn classnames into class objects
				List<ConceptDatatype> excludeDatatypes = new Vector<ConceptDatatype>();
				for (String name : excludeDatatypeNames) {
					if (!"".equals(name)) {
						excludeDatatypes.add(cs.getConceptDatatypeByName(name));
					}
				}
				
				// perform the search
				searchResults.addAll(cs.getConcepts(phrase, searchLocales, includeRetired, includeClasses, excludeClasses,
				    includeDatatypes, excludeDatatypes, null, start, length));
				
				//TODO Should we still include drugs, if yes, smartly harmonize the paging between the two different DB tables
				//look ups to match the values of start and length not to go over the value of count of matches returned to the search widget
				//List<Drug> drugs = null;
				//if (includeDrugConcepts)
				//	drugs = cs.getDrugs(phrase, null, false, includeRetired, null, null);
				
			}
			
			if (searchResults.size() < 1) {
				objectList.add(Context.getMessageSourceService().getMessage("general.noMatchesFoundInLocale",
				    new Object[] { "<b>" + phrase + "</b>", OpenmrsUtil.join(searchLocales, ", ") }, Context.getLocale()));
			} else {
				// turn searchResults into concept list items
				// if user wants drug concepts included, append those
				for (ConceptSearchResult searchResult : searchResults) {
					objectList.add(new ConceptListItem(searchResult));
				}
			}
		}
		catch (Exception e) {
			log.error("Error while finding concepts + " + e.getMessage(), e);
			objectList.add(Context.getMessageSourceService().getMessage("Concept.search.error") + " - " + e.getMessage());
		}
		
		if (objectList.size() == 0) {
			objectList.add(Context.getMessageSourceService().getMessage("general.noMatchesFoundInLocale",
			    new Object[] { "<b>" + phrase + "</b>", defaultLocale }, Context.getLocale()));
		}
		
		return objectList;
	}