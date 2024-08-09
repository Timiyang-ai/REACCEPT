public void validate(Object obj, Errors errors) {
		Role role = (Role) obj;
		if (role == null) {
			errors.rejectValue("role", "error.general");
		} else {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "role", "error.role");
			
			// reject any role that has a leading or trailing space
			if (!role.getRole().equals(role.getRole().trim())) {
				errors.rejectValue("role", "error.trailingSpaces");
			}
		}
		if (obj == null) {
			throw new IllegalArgumentException("validated role object should not be null");
		} else {
			ValidateUtil.validateFieldLengths(errors, obj.getClass(), "role", "description");
		}
	}