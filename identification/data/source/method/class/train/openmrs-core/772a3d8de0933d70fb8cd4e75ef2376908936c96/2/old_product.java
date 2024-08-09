public void validate(Object obj, Errors errors) throws APIException, DuplicateConceptNameException {
		
		if (obj == null || !(obj instanceof Concept)) {
			throw new IllegalArgumentException("The parameter obj should not be null and must be of type" + Concept.class);
		}
		
		Concept conceptToValidate = (Concept) obj;
		//no name to validate, but why is this the case?
		if (conceptToValidate.getNames().size() == 0) {
			errors.reject("Concept.name.atLeastOneRequired");
			return;
		}
		
		boolean hasFullySpecifiedName = false;
		for (Locale conceptNameLocale : conceptToValidate.getAllConceptNameLocales()) {
			boolean fullySpecifiedNameForLocaleFound = false;
			boolean preferredNameForLocaleFound = false;
			boolean shortNameForLocaleFound = false;
			Set<String> validNamesFoundInLocale = new HashSet<String>();
			Collection<ConceptName> namesInLocale = conceptToValidate.getNames(conceptNameLocale);
			for (ConceptName nameInLocale : namesInLocale) {
				if (StringUtils.isBlank(nameInLocale.getName())) {
					log.debug("Name in locale '" + conceptNameLocale.toString()
					        + "' cannot be an empty string or white space");
					errors.reject("Concept.name.empty");
				}
				if (nameInLocale.isLocalePreferred() != null) {
					if (nameInLocale.isLocalePreferred() && !preferredNameForLocaleFound) {
						if (nameInLocale.isIndexTerm()) {
							log.warn("Preferred name in locale '" + conceptNameLocale.toString()
							        + "' shouldn't be an index term");
							errors.reject("Concept.error.preferredName.is.indexTerm");
						} else if (nameInLocale.isShort()) {
							log.warn("Preferred name in locale '" + conceptNameLocale.toString()
							        + "' shouldn't be a short name");
							errors.reject("Concept.error.preferredName.is.shortName");
						} else if (nameInLocale.isVoided()) {
							log.warn("Preferred name in locale '" + conceptNameLocale.toString()
							        + "' shouldn't be a voided name");
							errors.reject("Concept.error.preferredName.is.voided");
						}
						
						preferredNameForLocaleFound = true;
					}
					//should have one preferred name per locale
					else if (nameInLocale.isLocalePreferred() && preferredNameForLocaleFound) {
						log.warn("Found multiple preferred names in locale '" + conceptNameLocale.toString() + "'");
						errors.reject("Concept.error.multipleLocalePreferredNames");
					}
				}
				
				if (nameInLocale.isFullySpecifiedName()) {
					if (!hasFullySpecifiedName) {
						hasFullySpecifiedName = true;
					}
					if (!fullySpecifiedNameForLocaleFound) {
						fullySpecifiedNameForLocaleFound = true;
					} else {
						log.warn("Found multiple fully specified names in locale '" + conceptNameLocale.toString() + "'");
						errors.reject("Concept.error.multipleFullySpecifiedNames");
					}
					if (nameInLocale.isVoided()) {
						log.warn("Fully Specified name in locale '" + conceptNameLocale.toString()
						        + "' shouldn't be a voided name");
						errors.reject("Concept.error.fullySpecifiedName.is.voided");
					}
				}
				
				if (nameInLocale.isShort()) {
					if (!shortNameForLocaleFound) {
						shortNameForLocaleFound = true;
					}
					//should have one short name per locale
					else {
						log.warn("Found multiple short names in locale '" + conceptNameLocale.toString() + "'");
						errors.reject("Concept.error.multipleShortNames");
					}
				}
				
				//find duplicate names for a non-retired concept
				if (Context.getConceptService().isConceptNameDuplicate(nameInLocale)) {
					throw new DuplicateConceptNameException("'" + nameInLocale.getName()
					        + "' is a duplicate name in locale '" + conceptNameLocale.toString() + "'");
				}
				
				//
				if (errors.hasErrors()) {
					log.debug("Concept name '" + nameInLocale.getName() + "' for locale '" + conceptNameLocale
					        + "' is invalid");
					//if validation fails for any conceptName in current locale, don't proceed
					//This helps not to have multiple messages shown that are identical though they might be
					//for different conceptNames
					return;
				}
				
				//No duplicate names allowed for the same locale and concept, keep the case the same
				//except for short names
				if (!nameInLocale.isShort() && !validNamesFoundInLocale.add(nameInLocale.getName().toLowerCase())) {
					throw new DuplicateConceptNameException("'" + nameInLocale.getName()
					        + "' is a duplicate name in locale '" + conceptNameLocale.toString() + "' for the same concept");
				}
				
				if (log.isDebugEnabled()) {
					log.debug("Valid name found: " + nameInLocale.getName());
				}
			}
		}
		
		//Ensure that each concept has atleast a fully specified name
		if (!hasFullySpecifiedName) {
			log.debug("Concept has no fully specified name");
			errors.reject("Concept.error.no.FullySpecifiedName");
		}
		
		if (CollectionUtils.isNotEmpty(conceptToValidate.getConceptMappings())) {
			//validate all the concept maps
			int index = 0;
			Set<Integer> mappedTermIds = null;
			for (ConceptMap map : conceptToValidate.getConceptMappings()) {
				if (map.getConceptReferenceTerm().getConceptReferenceTermId() == null) {
					//if this term is getting created on the fly e.g. from old legacy code, validate it
					try {
						errors.pushNestedPath("conceptMappings[" + index + "].conceptReferenceTerm");
						ValidationUtils.invokeValidator(new ConceptReferenceTermValidator(), map.getConceptReferenceTerm(),
						    errors);
					}
					finally {
						errors.popNestedPath();
					}
					
				}
				/*if (map.getConceptMapType() == null) {
					errors.rejectValue("conceptMappings[" + index + "].conceptMapType", "Concept.map.typeRequired",
					    "The concept map type is required for a concept map");
					return;
				}*/

				//don't proceed to the next maps since the current one already has errors
				if (errors.hasErrors()) {
					return;
				}
				
				if (mappedTermIds == null) {
					mappedTermIds = new HashSet<Integer>();
				}
				
				//if we already have a mapping to this term, reject it this map
				if (map.getConceptReferenceTerm().getId() != null
				        && !mappedTermIds.add(map.getConceptReferenceTerm().getId())) {
					errors.rejectValue("conceptMappings[" + index + "]", "ConceptReferenceTerm.term.alreadyMapped",
					    "Cannot map a reference term multiple times to the same concept");
				}
				
				index++;
			}
		}
		ValidateUtil.validateFieldLengths(errors, obj.getClass(), "version", "retireReason");
	}