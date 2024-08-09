public void validate(Object obj, Errors errors) throws APIException {
		
		if (obj == null || !(obj instanceof ConceptReferenceTerm)) {
			throw new IllegalArgumentException("The parameter obj should not be null and must be of type"
			        + ConceptReferenceTerm.class);
		}
		
		ConceptReferenceTerm conceptReferenceTerm = (ConceptReferenceTerm) obj;
		
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
		}
		
		code = code.trim();
		//Ensure that there are no terms with the same code in the same source
		ConceptReferenceTerm termWithDuplicateCode = Context.getConceptService().getConceptReferenceTermByCode(code,
		    conceptReferenceTerm.getConceptSource());
		if (termWithDuplicateCode != null) {
			if (!OpenmrsUtil.nullSafeEquals(termWithDuplicateCode.getUuid(), conceptReferenceTerm.getUuid())) {
				errors.rejectValue("code", "ConceptReferenceTerm.duplicate.code",
				    "Duplicate concept reference term code in its concept source: " + code);
			}
		}
		
		//validate the concept reference term maps
		if (CollectionUtils.isNotEmpty(conceptReferenceTerm.getConceptReferenceTermMaps())) {
			int index = 0;
			Set<String> mappedTermUuids = null;
			for (ConceptReferenceTermMap map : conceptReferenceTerm.getConceptReferenceTermMaps()) {
				if (map == null) {
					throw new APIException("Cannot add a null concept reference term map");
				}
				
				if (map.getConceptMapType() == null) {
					errors.rejectValue("conceptReferenceTermMaps[" + index + "].conceptMapType",
					    "ConceptReferenceTerm.error.mapTypeRequired", "Concept Map Type is required");
				} else if (map.getTermB() == null) {
					errors.rejectValue("conceptReferenceTermMaps[" + index + "].termB",
					    "ConceptReferenceTerm.error.termBRequired", "Mapped Term is required");
				} else if (map.getTermB().equals(conceptReferenceTerm)) {
					errors.rejectValue("conceptReferenceTermMaps[" + index + "].termB", "ConceptReferenceTerm.map.sameTerm",
					    "Cannot map a concept reference term to itself");
				}
				
				//don't proceed to the next map
				if (errors.hasErrors()) {
					return;
				}
				
				if (mappedTermUuids == null) {
					mappedTermUuids = new HashSet<String>();
				}
				
				//if we already have a mapping to this term, reject it this map
				if (!mappedTermUuids.add(map.getTermB().getUuid())) {
					errors.rejectValue("conceptReferenceTermMaps[" + index + "].termB",
					    "ConceptReferenceTerm.termToTerm.alreadyMapped",
					    "Cannot map a reference term multiple times to the same concept reference term");
				}
				
				index++;
			}
		}
	}