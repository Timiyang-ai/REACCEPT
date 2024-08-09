public void validate(Object obj, Errors errors) {
		if (obj != null) {
			super.validate(obj, errors);
			ValidateUtil.validateFieldLengths(errors, obj.getClass(), "name", "description", "datatypeClassname",
			    "preferredHandlerClassname", "retireReason");
		}
	}