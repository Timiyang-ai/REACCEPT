public void validate(Object obj, Errors errors) {
		RelationshipType relationshipType = (RelationshipType) obj;
		if (relationshipType == null) {
			errors.rejectValue("relationshipType", "error.general");
		} else {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "aIsToB", "RelationshipType.aIsToB.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "bIsToA", "RelationshipType.bIsToA.required");
		}
	}