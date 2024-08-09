@Override
	public void validate(Object obj, Errors errors) {
		if (obj == null || !(obj instanceof Drug)) {
			throw new IllegalArgumentException("The parameter obj should not be null and must be of type" + Drug.class);
		} else {
			Drug drug = (Drug) obj;
			Set<DrugReferenceMap> drugReferenceMaps = drug.getDrugReferenceMaps();
			Set<String> mappedTermUuids = new HashSet<String>();
			int index = 0;
			for (DrugReferenceMap referenceMap : drugReferenceMaps) {
				Drug mappedDrug = referenceMap.getDrug();
				ConceptReferenceTerm referenceTerm = referenceMap.getConceptReferenceTerm();
				ConceptMapType mapType = referenceMap.getConceptMapType();
				
				if (mappedDrug == null) {
					errors.rejectValue("drugReferenceMaps[" + index + "].drug", "Drug.drugReferenceMap.mappedDrug");
				}
				if (referenceTerm == null) {
					errors.rejectValue("drugReferenceMaps[" + index + "].conceptReferenceTerm",
					    "Drug.drugReferenceMap.conceptReferenceTerm");
				} else if (referenceTerm.getConceptReferenceTermId() == null) {
					try {
						errors.pushNestedPath("drugReferenceMaps[" + index + "].conceptReferenceTerm");
						ValidationUtils.invokeValidator(new ConceptReferenceTermValidator(), referenceTerm, errors);
					}
					finally {
						errors.popNestedPath();
					}
				}
				
				if (mapType == null) {
					errors.rejectValue("drugReferenceMaps[" + index + "].conceptMapType",
					    "Drug.drugReferenceMap.conceptMapType");
				} else if (mapType.getConceptMapTypeId() == null) {
					try {
						errors.pushNestedPath("drugReferenceMaps[" + index + "].conceptMapType");
						ValidationUtils.invokeValidator(new ConceptMapTypeValidator(), mapType, errors);
					}
					finally {
						errors.popNestedPath();
					}
				}
				
				//don't proceed to the next map
				if (errors.hasErrors()) {
					return;
				}
				
				//if we already have a mapping to this term, reject it this map
				if (!mappedTermUuids.add(referenceMap.getConceptReferenceTerm().getUuid())) {
					errors.rejectValue("drugReferenceMaps[" + index + "].conceptReferenceTerm",
					    "Drug.drugReferenceMap.termAlreadyMapped",
					    "Cannot map a drug multiple times to the same reference term");
				}
				index++;
			}
		}
	}