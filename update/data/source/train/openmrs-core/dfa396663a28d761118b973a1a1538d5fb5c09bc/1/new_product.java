public static void validate(Object obj) throws ValidationException {
		Errors errors = new BindException(obj, "");
		
		Context.getAdministrationService().validate(obj, errors);
		
		if (errors.hasErrors()) {
			Set<String> uniqueErrorMessages = new LinkedHashSet<String>();
			for (Object objerr : errors.getAllErrors()) {
				ObjectError error = (ObjectError) objerr;
				String message = Context.getMessageSourceService().getMessage(error.getCode());
				if (error instanceof FieldError) {
					message = ((FieldError) error).getField() + ": " + message;
				}
				uniqueErrorMessages.add(message);
			}
			
			String exceptionMessage = "'" + obj + "' failed to validate with reason: ";
			exceptionMessage += StringUtils.join(uniqueErrorMessages, ", ");
			throw new ValidationException(exceptionMessage, errors);
		}
	}