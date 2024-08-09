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
				if (maxOccurs < 1) {
					errors.rejectValue("maxOccurs", "AttributeType.maxOccursShouldNotBeLessThanOne");
				} else if (maxOccurs < minOccurs) {
					errors.rejectValue("maxOccurs", "AttributeType.maxOccursShouldNotBeLessThanMinOccurs");
				}
			}
			
			if (StringUtils.isBlank(attributeType.getDatatypeClassname())) {
				errors.rejectValue("datatypeClassname", "error.null");
			} else {
				try {
					CustomDatatypeUtil.getDatatype(attributeType);
				}
				catch (Exception ex) {
					errors.rejectValue("datatypeConfig", "AttributeType.datatypeConfig.invalid", new Object[] { ex
					        .getMessage() }, "Invalid");
				}
			}
			
			// ensure that handler is suitable for datatype
			if (StringUtils.isNotEmpty(attributeType.getPreferredHandlerClassname())) {
				try {
					CustomDatatype<?> datatype = CustomDatatypeUtil.getDatatype(attributeType);
					CustomDatatypeHandler<?, ?> handler = CustomDatatypeUtil.getHandler(attributeType);
					if (!CustomDatatypeUtil.isCompatibleHandler(handler, datatype)) {
						errors.rejectValue("preferredHandlerClassname",
						    "AttributeType.preferredHandlerClassname.wrongDatatype");
					}
				}
				catch (Exception ex) {
					errors.rejectValue("handlerConfig", "AttributeType.handlerConfig.invalid", new Object[] { ex
					        .getMessage() }, "Invalid");
				}
			}
		}
	}