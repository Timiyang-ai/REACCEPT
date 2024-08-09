public Concept saveConcept(Concept concept) throws APIException {
		
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
		
		Date dateChanged = new Date();
		
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
				clone.setDateCreated(dateChanged);
				clone.setCreator(Context.getAuthenticatedUser());
				concept.addName(clone);
			}
		}
		
		//See TRUNK-3337 for why we set changed by and date changed every time we save a concept.
		concept.setDateChanged(dateChanged);
		concept.setChangedBy(Context.getAuthenticatedUser());
		
		Errors errors = new BindException(concept, "concept");
		new ConceptValidator().validate(concept, errors);
		if (errors.hasErrors())
			throw new APIException("Validation errors found");
		
		//Set a preferred name for each locale for those where it isn't yet specified
		for (Locale locale : LocaleUtility.getLocalesInOrder()) {
			ConceptName possiblePreferredName = concept.getPreferredName(locale);
			if (possiblePreferredName == null || !possiblePreferredName.isLocalePreferred()) {
				if (possiblePreferredName != null)
					possiblePreferredName.setLocalePreferred(true);
				//set the first synonym as the preferred name if it has any
				else if (!CollectionUtils.isEmpty(concept.getSynonyms(locale)))
					concept.getSynonyms(locale).iterator().next().setLocalePreferred(true);
			}
		}
		
		Concept conceptToReturn = dao.saveConcept(concept);
		
		// add/remove entries in the concept_word table (used for searching)
		this.updateConceptIndex(conceptToReturn);
		
		return conceptToReturn;
	}