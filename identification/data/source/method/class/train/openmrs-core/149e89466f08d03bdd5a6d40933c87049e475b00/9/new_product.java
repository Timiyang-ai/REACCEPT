public void validate(Object obj, Errors errorss) throws APIException, DuplicateConceptNameException {
		
		if (obj == null || !(obj instanceof Concept)) {
			
			if (log.isErrorEnabled())
				log.error("The parameter obj should not be null and must be of type" + Concept.class);
			throw new IllegalArgumentException("Concept name is null or of invalid class");
			
		} else {
			
			Concept newConcept = (Concept) obj;
			String newName = null;
			
			//no name to validate, but why is this the case?
			if (newConcept.getName() == null)
				return;
			
			//if the new concept name is in a different locale other than openmrs' default one
			if (newConcept.getName().getLocale() != null && !newConcept.getName().getLocale().equals(Context.getLocale()))
				newName = newConcept.getName().getName();
			else
				newName = newConcept.getPreferredName(Context.getLocale()).getName();
			
			if (StringUtils.isBlank(newName)) {
				
				if (log.isErrorEnabled())
					log.error("No preferred name specified for the concept to be validated");
				throw new APIException("Concept name cannot be an empty String");
			}
			if (log.isDebugEnabled())
				log.debug("Looking up concept names matching " + newName);
			
			List<Concept> matchingConcepts = Context.getConceptService().getConceptsByName(newName);
			
			Set<Concept> duplicates = new HashSet<Concept>();
			
			for (Concept c : matchingConcepts) {
				
				//If updating a concept, read past the concept being updated
				if (newConcept.getConceptId() != null && c.getConceptId().intValue() == newConcept.getConceptId())
					continue;
				//get only duplicates that are not retired
				if (c.getPreferredName(Context.getLocale()).getName().equalsIgnoreCase(newName) && !c.isRetired()) {
					duplicates.add(c);
				}
			}
			
			if (duplicates.size() > 0) {
				for (Concept duplicate : duplicates) {
					if (log.isErrorEnabled())
						log.error("The name '" + newName + "' is already being used by concept with Id: "
						        + duplicate.getConceptId());
					throw new DuplicateConceptNameException("Duplicate concept name '" + newName + "'");
					
				}
				
			}
			
		}
		
	}