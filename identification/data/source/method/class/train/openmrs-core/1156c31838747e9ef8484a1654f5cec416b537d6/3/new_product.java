@Override
	public void validate(Object obj, Errors errors) {
		RelationshipType relationshipType = (RelationshipType) obj;
		if (relationshipType == null) {
			errors.rejectValue("relationshipType", "error.general");
		} else {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "aIsToB", "RelationshipType.aIsToB.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "bIsToA", "RelationshipType.bIsToA.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "RelationshipType.description.required");
			RelationshipType exist = Context.getPersonService().getRelationshipTypeByName(
			    relationshipType.getaIsToB() + "/" + relationshipType.getbIsToA());
			if (exist != null && !exist.getRetired()
			        && !OpenmrsUtil.nullSafeEquals(relationshipType.getUuid(), exist.getUuid())) {
				errors.reject("duplicate.relationshipType");
			}
			ValidateUtil.validateFieldLengths(errors, obj.getClass(), "aIsToB", "bIsToA", "description", "retireReason");
		}
	}