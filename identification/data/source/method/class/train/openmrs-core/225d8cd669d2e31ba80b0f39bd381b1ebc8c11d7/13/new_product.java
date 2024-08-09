public void validate(Object obj, Errors errors) {
		
		if (obj == null || !(obj instanceof ConceptMapType))
			throw new IllegalArgumentException("The parameter obj should not be null and must be of type"
			        + ConceptMapType.class);
		
		ConceptMapType conceptMapType = (ConceptMapType) obj;
		String name = conceptMapType.getName();
		if (!StringUtils.hasText(name)) {
			errors.rejectValue("name", "ConceptMapType.error.nameRequired",
			    "The name property is required for a concept map type");
			return;
		}
		
		name = name.trim();
		ConceptMapType duplicate = Context.getConceptService().getConceptMapTypeByName(name);
		if (duplicate != null) {
			if (!OpenmrsUtil.nullSafeEquals(duplicate.getId(), conceptMapType.getId())) {
				errors.rejectValue("name", "ConceptMapType.duplicate.name", "Duplicate concept map type name: " + name);
			}
		}
	}