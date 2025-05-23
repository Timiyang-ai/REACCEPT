public void validate(Object obj, Errors errors) {
		VisitAttributeType visitAttributeType = (VisitAttributeType) obj;
		if (visitAttributeType == null) {
			errors.reject("error.general");
		} else {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "error.name");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "minOccurs", "error.null");
			
			Integer minOccurs = visitAttributeType.getMinOccurs();
			Integer maxOccurs = visitAttributeType.getMaxOccurs();
			
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

			if (StringUtils.isBlank(visitAttributeType.getDatatype())) {
				errors.rejectValue("datatype", "error.null");
			} else {
				try {
					Context.getAttributeService().getHandler(visitAttributeType);
				} catch (Exception ex) {
					errors.rejectValue("handlerConfig", "AttributeType.handlerConfig.invalid", new Object[] { ex.getMessage() }, "Invalid");
				}
			}
		}
	}