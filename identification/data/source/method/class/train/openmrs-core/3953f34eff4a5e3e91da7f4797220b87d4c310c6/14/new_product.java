public Concept saveConcept(Concept concept) throws APIException {
		ConceptMapType defaultConceptMapType = null;
		for (ConceptMap map : concept.getConceptMappings()) {
			if (map.getConceptMapType() == null) {
				if (defaultConceptMapType == null) {
					defaultConceptMapType = getDefaultConceptMapType();
				}
				map.setConceptMapType(defaultConceptMapType);
			}
		}
		
		// make sure the administrator hasn't turned off concept editing
		checkIfLocked();
		checkIfDatatypeCanBeChanged(concept);
		
		List<ConceptName> changedConceptNames = null;
		Map<String, ConceptName> uuidClonedConceptNameMap = null;
		
		if (concept.getConceptId() != null) {
			uuidClonedConceptNameMap = new HashMap<String, ConceptName>();
			for (ConceptName conceptName : concept.getNames()) {
				// ignore newly added names
				if (conceptName.getConceptNameId() != null) {
					ConceptName clone = cloneConceptName(conceptName);
					clone.setConceptNameId(null);
					uuidClonedConceptNameMap.put(conceptName.getUuid(), clone);
					
					if (hasNameChanged(conceptName)) {
						if (changedConceptNames == null)
							changedConceptNames = new ArrayList<ConceptName>();
						changedConceptNames.add(conceptName);
					} else {
						// put back the concept name id
						clone.setConceptNameId(conceptName.getConceptNameId());
						// Use the cloned version
						try {
							BeanUtils.copyProperties(conceptName, clone);
						}
						catch (IllegalAccessException e) {
							log.error("Error generated", e);
						}
						catch (InvocationTargetException e) {
							log.error("Error generated", e);
						}
					}
				}
			}
		}
		
		if (CollectionUtils.isNotEmpty(changedConceptNames)) {
			for (ConceptName changedName : changedConceptNames) {
				// void old concept name
				ConceptName nameInDB = changedName;
				nameInDB.setVoided(true);
				nameInDB.setDateVoided(new Date());
				nameInDB.setVoidedBy(Context.getAuthenticatedUser());
				nameInDB.setVoidReason(Context.getMessageSourceService().getMessage("Concept.name.voidReason.nameChanged"));
				
				// Make the voided name a synonym, this would help to avoid
				// having multiple fully specified or preferred
				// names in a locale incase the name is unvoided
				if (!nameInDB.isSynonym())
					nameInDB.setConceptNameType(null);
				if (nameInDB.isLocalePreferred())
					nameInDB.setLocalePreferred(false);
				
				// create a new concept name from the matching cloned
				// conceptName
				ConceptName clone = uuidClonedConceptNameMap.get(nameInDB.getUuid());
				clone.setUuid(UUID.randomUUID().toString());
				concept.addName(clone);
			}
		}
		
		//Ensure if there's a name for a locale that at least one suitable name is marked preferred in that locale
		//Order of preference is:
		// 1) any name that concept.getPreferredName returns
		// 2) fully specified name
		// 3) any synonym
		// short name and index terms are never preferred.
		
		Set<Locale> checkedLocales = new HashSet<Locale>();
		for (ConceptName n : concept.getNames()) {
			Locale locale = n.getLocale();
			if (checkedLocales.contains(locale))
				continue; //we've already checked this locale
				
			//getPreferredName(locale) returns any name marked preferred,
			//or the fullySpecifiedName even if not marked preferred
			ConceptName possiblePreferredName = concept.getPreferredName(locale);
			
			if (possiblePreferredName != null)
				; //do nothing yet, but stick around to setLocalePreferred(true)
			else if (concept.getFullySpecifiedName(locale) != null)
				possiblePreferredName = concept.getFullySpecifiedName(locale);
			else if (!CollectionUtils.isEmpty(concept.getSynonyms(locale)))
				concept.getSynonyms(locale).iterator().next().setLocalePreferred(true);
			//index terms are never used as preferred name
			
			if (possiblePreferredName != null) { //there may have been none
				possiblePreferredName.setLocalePreferred(true);
			}
			checkedLocales.add(locale);
		}
		
		Errors errors = new BindException(concept, "concept");
		new ConceptValidator().validate(concept, errors);
		if (errors.hasErrors())
			throw new APIException("Validation errors found: " + errors.getAllErrors());
		
		Concept conceptToReturn = dao.saveConcept(concept);
		
		// add/remove entries in the concept_word table (used for searching)
		this.updateConceptIndex(conceptToReturn);
		
		return conceptToReturn;
	}