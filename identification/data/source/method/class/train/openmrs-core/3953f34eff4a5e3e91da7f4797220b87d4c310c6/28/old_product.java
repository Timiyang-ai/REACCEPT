@Override
	public Concept saveConcept(Concept concept) throws APIException {
		ensureConceptMapTypeIsSet(concept);

		CustomDatatypeUtil.saveAttributesIfNecessary(concept);

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
						if (changedConceptNames == null) {
							changedConceptNames = new ArrayList<ConceptName>();
						}
						changedConceptNames.add(conceptName);
					} else {
						// put back the concept name id
						clone.setConceptNameId(conceptName.getConceptNameId());
						// Use the cloned version
						try {
							BeanUtils.copyProperties(conceptName, clone);
						}
						catch (IllegalAccessException e) {
							log.error(errorMessage, e);
						}
						catch (InvocationTargetException e) {
							log.error(errorMessage, e);
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
				// names in a locale in case the name is unvoided
				if (!nameInDB.isSynonym()) {
					nameInDB.setConceptNameType(null);
				}
				if (nameInDB.getLocalePreferred()) {
					nameInDB.setLocalePreferred(false);
				}
				
				// create a new concept name from the matching cloned
				// conceptName
				ConceptName clone = uuidClonedConceptNameMap.get(nameInDB.getUuid());
				clone.setUuid(UUID.randomUUID().toString());
				clone.setDateCreated(null);
				clone.setCreator(null);
				concept.addName(clone);
			}
		}
		ensurePreferredNameForLocale(concept);

		//See TRUNK-3337 for why we set changed by and date changed every time we save a concept.
		concept.setDateChanged(new Date());
		concept.setChangedBy(Context.getAuthenticatedUser());
		
		// force isSet when concept has members
		if (!concept.getSet() && (!concept.getSetMembers().isEmpty())) {
			concept.setSet(true);
		}

		return dao.saveConcept(concept);
	}