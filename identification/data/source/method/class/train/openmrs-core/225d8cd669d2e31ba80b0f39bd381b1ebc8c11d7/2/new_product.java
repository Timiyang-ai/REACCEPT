public void validate(Object obj, Errors errors) {
		FieldType fieldType = (FieldType) obj;
		if (fieldType == null) {
			errors.rejectValue("fieldType", "error.general");
		} else {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "error.name");
			if (!errors.hasErrors()) {
				FieldType exist = Context.getFormService().getFieldTypeByName(fieldType.getName());
				if (exist != null && !exist.isRetired() && !OpenmrsUtil.nullSafeEquals(fieldType.getUuid(), exist.getUuid())) {
					errors.rejectValue("name", "fieldtype.duplicate.name");
				}
			}
		}
	}