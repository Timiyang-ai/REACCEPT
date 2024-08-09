public void validate(Object obj, Errors errors) throws APIException, DuplicateConceptNameException {
		
		if (obj == null || !(obj instanceof Concept))
			throw new IllegalArgumentException("The parameter obj should not be null and must be of type" + Concept.class);
		
		Concept conceptToValidate = (Concept) obj;
		//no name to validate, but why is this the case?
		if (conceptToValidate.getNames().size() == 0) {
			errors.reject("Concept.name.atLeastOneRequired");
			return;
		}
		
		boolean hasFullySpecifiedName = false;
		for (Locale conceptNameLocale : conceptToValidate.getAllConceptNameLocales()) {
			//The concept's locale should be among the allowed locales listed in global properties
			if (!LocaleUtility.getLocalesInOrder().contains(conceptNameLocale)) {
				log.warn("The locale '" + conceptNameLocale.toString() + "' is not listed among allowed locales");
				errors.reject("Concept.error.invalid.locale");
			}
			
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
					if (!hasFullySpecifiedName)
						hasFullySpecifiedName = true;
					if (!fullySpecifiedNameForLocaleFound)
						fullySpecifiedNameForLocaleFound = true;
					else {
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
					if (!shortNameForLocaleFound)
						shortNameForLocaleFound = true;
					//should have one short name per locale
					else {
						log.warn("Found multiple short names in locale '" + conceptNameLocale.toString() + "'");
						errors.reject("Concept.error.multipleShortNames");
					}
				}
				if (nameInLocale.isLocalePreferred() || nameInLocale.isFullySpecifiedName()) {
					List<Concept> conceptsWithPossibleDuplicateNames = Context.getConceptService().getConceptsByName(
					    nameInLocale.getName());
					if (conceptsWithPossibleDuplicateNames.size() > 0) {
						for (Concept concept : conceptsWithPossibleDuplicateNames) {
							//skip past the concept being edited and retired ones
							if (concept.isRetired()
							        || (conceptToValidate.getConceptId() != null && conceptToValidate.getConceptId().equals(
							            concept.getConceptId())))
								continue;
							//should be a unique name amongst all preferred and fully specified names in its locale system wide
							if ((concept.getFullySpecifiedName(conceptNameLocale) != null && concept.getFullySpecifiedName(
							    conceptNameLocale).getName().equalsIgnoreCase(nameInLocale.getName()))
							        || (concept.getPreferredName(conceptNameLocale) != null && concept.getPreferredName(
							            conceptNameLocale).getName().equalsIgnoreCase(nameInLocale.getName()))) {
								throw new DuplicateConceptNameException("'" + nameInLocale.getName()
								        + "' is a duplicate name in locale '" + conceptNameLocale.toString() + "'");
							}
						}
					}
				}
				
				if (errors.hasErrors()) {
					log.debug("Concept name '" + nameInLocale.getName() + "' for locale '" + conceptNameLocale
					        + "' is invalid");
					//if validation fails for any conceptName in current locale, don't proceed
					//This helps not to have multiple messages shown that are identical though they might be
					//for different conceptNames
					return;
				}
				
				//No duplicate names allowed for the same locale and concept, keep the case the same
				if (!validNamesFoundInLocale.add(nameInLocale.getName().toLowerCase()))
					throw new DuplicateConceptNameException("'" + nameInLocale.getName()
					        + "' is a duplicate name in locale '" + conceptNameLocale.toString() + "' for the same concept");
				if (log.isDebugEnabled())
					log.debug("Valid name found: " + nameInLocale.getName());
			}
		}
		
		//Ensure that each concept has atleast a fully specified name
		if (!hasFullySpecifiedName) {
			log.debug("Concept has no fully specified name");
			errors.reject("Concept.error.no.FullySpecifiedName");
		}
	}