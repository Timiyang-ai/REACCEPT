@Override
	public void validate(Object target, Errors errors) {
		@SuppressWarnings("unchecked")
		T attributeType = (T) target;
		
		if (attributeType == null) {
			errors.reject("error.general");
		} else {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "error.name");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "minOccurs", "error.null");
			
			Integer minOccurs = attributeType.getMinOccurs();
			Integer maxOccurs = attributeType.getMaxOccurs();
			
			if (minOccurs != null) {
				if (minOccurs < 0) {
					errors.rejectValue("minOccurs", "AttributeType.minOccursShouldNotBeLessThanZero");
				}
			}
			
			if (maxOccurs != null) {
				if (maxOccurs < 0) {
					errors.rejectValue("maxOccurs", "AttributeType.maxOccursShouldNotBeLessThanZero");
				} else if (maxOccurs < minOccurs) {
					errors.rejectValue("maxOccurs", "AttributeType.maxOccursShouldNotBeLessThanMinOccurs");
				}
			}
			
			if (StringUtils.isBlank(attributeType.getDatatype())) {
				errors.rejectValue("datatype", "error.null");
			} else {
				try {
					Context.getAttributeService().getHandler(attributeType);
				}
				catch (Exception ex) {
					errors.rejectValue("handlerConfig", "AttributeType.handlerConfig.invalid", new Object[] { ex
					        .getMessage() }, "Invalid");
				}
			}
		}
	}