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
			uuidClonedConceptNameMap = new HashMap<>();
			for (ConceptName conceptName : concept.getNames()) {
				// ignore newly added names
				if (conceptName.getConceptNameId() != null) {
					ConceptName clone = cloneConceptName(conceptName);
					clone.setConceptNameId(null);
					uuidClonedConceptNameMap.put(conceptName.getUuid(), clone);
					
					if (hasNameChanged(conceptName)) {
						if (changedConceptNames == null) {
							changedConceptNames = new ArrayList<>();
						}
						changedConceptNames.add(conceptName);
					} else {
						// put back the concept name id
						clone.setConceptNameId(conceptName.getConceptNameId());
						// Use the cloned version
						try {
							BeanUtils.copyProperties(conceptName, clone);
						}
						catch (IllegalAccessException | InvocationTargetException e) {
							log.error(errorMessage, e);
						}
					}
				}
			}
		}
		
		if (CollectionUtils.isNotEmpty(changedConceptNames)) {
			for (ConceptName changedName : changedConceptNames) {
				// void old concept name
				changedName.setVoided(true);
				changedName.setDateVoided(new Date());
				changedName.setVoidedBy(Context.getAuthenticatedUser());
				changedName.setVoidReason(Context.getMessageSourceService().getMessage("Concept.name.voidReason.nameChanged"));

				makeVoidedNameSynonym(changedName);
				makeLocaleNotPreferred(changedName);
				
				// create a new concept name from the matching cloned
				// conceptName
				ConceptName clone = uuidClonedConceptNameMap.get(changedName.getUuid());
				clone.setUuid(UUID.randomUUID().toString());
				clone.setDateCreated(null);
				clone.setCreator(null);
				concept.addName(clone);
			}
		}
		ensurePreferredNameForLocale(concept);
		logConceptChangedData(concept);
		
		// force isSet when concept has members
		if (!concept.getSet() && (!concept.getSetMembers().isEmpty())) {
			concept.setSet(true);
		}

		return dao.saveConcept(concept);
	}