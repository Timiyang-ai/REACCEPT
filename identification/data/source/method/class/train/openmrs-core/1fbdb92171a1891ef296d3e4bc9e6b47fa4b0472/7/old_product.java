public void validate(Object obj, Errors errors) throws APIException {
		
		if (obj == null || !(obj instanceof ConceptReferenceTerm))
			throw new IllegalArgumentException("The parameter obj should not be null and must be of type"
			        + ConceptReferenceTerm.class);
		
		ConceptReferenceTerm conceptReferenceTerm = (ConceptReferenceTerm) obj;
		String name = conceptReferenceTerm.getName();
		//For now accept empty name fields concept reference terms
		/*if (!StringUtils.hasText(name)) {
			errors.rejectValue("name", "ConceptReferenceTerm.error.nameRequired",
			    "The name property is required for a concept reference term");
			log.warn("The name property is required for a concept reference term");
			return;
		}*/
		String code = conceptReferenceTerm.getCode();
		if (!StringUtils.hasText(code)) {
			errors.rejectValue("code", "ConceptReferenceTerm.error.codeRequired",
			    "The code property is required for a concept reference term");
			return;
		}
		if (conceptReferenceTerm.getConceptSource() == null) {
			errors.rejectValue("conceptSource", "ConceptReferenceTerm.error.sourceRequired",
			    "The conceptSource property is required for a concept reference term");
			return;
		} else if (conceptReferenceTerm.getConceptSource().getId() == null) {
			errors.rejectValue("conceptSource", "ConceptReferenceTerm.source.notInDatabase",
			    "Only existing concept reference sources can be used");
			return;
		}
		
		if (StringUtils.hasText(name)) {
			name = name.trim();
			//Ensure that there are no terms with the same name in the same source
			ConceptReferenceTerm termWithDuplicateName = Context.getConceptService().getConceptReferenceTermByName(name,
			    conceptReferenceTerm.getConceptSource());
			if (termWithDuplicateName != null) {
				if (!OpenmrsUtil.nullSafeEquals(termWithDuplicateName.getId(), conceptReferenceTerm.getId())) {
					errors.rejectValue("name", "ConceptReferenceTerm.duplicate.name",
					    "Duplicate concept reference term name: " + name);
				}
			}
		}
		
		code = code.trim();
		//Ensure that there are no terms with the same code in the same source
		ConceptReferenceTerm termWithDuplicateCode = Context.getConceptService().getConceptReferenceTermByCode(code,
		    conceptReferenceTerm.getConceptSource());
		if (termWithDuplicateCode != null) {
			if (!OpenmrsUtil.nullSafeEquals(termWithDuplicateCode.getId(), conceptReferenceTerm.getId())) {
				errors.rejectValue("code", "ConceptReferenceTerm.duplicate.code",
				    "Duplicate concept reference term code in its concept source: " + code);
			}
		}
		
		//validate the concept reference term maps
		if (CollectionUtils.isNotEmpty(conceptReferenceTerm.getConceptReferenceTermMaps())) {
			int index = 0;
			Set<Integer> mappedTermIds = null;
			for (ConceptReferenceTermMap map : conceptReferenceTerm.getConceptReferenceTermMaps()) {
				if (map == null)
					throw new APIException("Cannot add a null concept reference term map");
				
				if (map.getConceptMapType() == null) {
					errors.rejectValue("conceptReferenceTermMaps[" + index + "].conceptMapType",
					    "ConceptReferenceTerm.error.mapTypeRequired", "Concept Map Type is required");
				} else if (map.getConceptMapType().getId() == null) {
					errors.rejectValue("conceptReferenceTermMaps[" + index + "].conceptMapType",
					    "ConceptReferenceTerm.mapType.notInDatabase", "Only existing concept map types can be used");
				} else if (map.getTermB() == null) {
					errors.rejectValue("conceptReferenceTermMaps[" + index + "].termB",
					    "ConceptReferenceTerm.error.termBRequired", "Mapped Term is required");
				} else if (map.getTermB().getId() == null) {
					errors.rejectValue("conceptReferenceTermMaps[" + index + "].termB",
					    "ConceptReferenceTerm.term.notInDatabase", "Only existing concept reference terms can be mapped");
				} else if (map.getTermB().equals(conceptReferenceTerm)) {
					errors.rejectValue("conceptReferenceTermMaps[" + index + "].termB", "ConceptReferenceTerm.map.sameTerm",
					    "Cannot map a concept reference term to itself");
				}
				
				//don't proceed to the next map
				if (errors.hasErrors())
					return;
				
				if (mappedTermIds == null)
					mappedTermIds = new HashSet<Integer>();
				
				//if we already have a mapping to this term, reject it this map
				if (!mappedTermIds.add(map.getTermB().getId())) {
					errors.rejectValue("conceptReferenceTermMaps[" + index + "].termB",
					    "ConceptReferenceTerm.termToTerm.alreadyMapped",
					    "Cannot map a reference term multiple times to the same concept reference term");
				}
				
				index++;
			}
		}
	}