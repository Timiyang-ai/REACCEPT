@Override
	public void validate(Object object, Errors errors) {
		if (log.isDebugEnabled()) {
			log.debug(this.getClass().getName() + ".validate...");
		}
		PersonName personName = (PersonName) object;
		try {
			// Validate that the person name object is not null
			if (personName == null) {
				errors.reject("error.name");
			} else if (!personName.getVoided()) {
				validatePersonName(personName, errors, false, true);
			}
		}
		catch (Exception e) {
			errors.reject(e.getMessage());
		}
	}