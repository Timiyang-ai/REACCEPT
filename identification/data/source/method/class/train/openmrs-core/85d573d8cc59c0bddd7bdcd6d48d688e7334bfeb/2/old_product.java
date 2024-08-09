public void validate(Object object, Errors errors) {
		if (!ValidateUtil.isValidationOn()) {
			return;
		}
		
		//TODO Validate other aspects of the personAddress object
		if (log.isDebugEnabled())
			log.debug(this.getClass().getName() + ".validate...");
		
		if (object == null)
			throw new IllegalArgumentException("The personAddress object should not be null");
		
		PersonAddress personAddress = (PersonAddress) object;
		
		//resolve a shorter name to display along with the error message
		String addressString = null;
		if (StringUtils.isNotBlank(personAddress.getAddress1()))
			addressString = personAddress.getAddress1();
		else if (StringUtils.isNotBlank(personAddress.getAddress2()))
			addressString = personAddress.getAddress2();
		else if (StringUtils.isNotBlank(personAddress.getCityVillage()))
			addressString = personAddress.getCityVillage();
		else
			addressString = personAddress.toString();
		
		if (OpenmrsUtil.compareWithNullAsEarliest(personAddress.getStartDate(), new Date()) > 0)
			errors.rejectValue("startDate", "PersonAddress.error.startDateInFuture", new Object[] { "'" + addressString
			        + "'" }, "The Start Date for address '" + addressString + "' shouldn't be in the future");
		
		if (personAddress.getStartDate() != null
		        && OpenmrsUtil.compareWithNullAsLatest(personAddress.getStartDate(), personAddress.getEndDate()) > 0)
			errors.rejectValue("endDate", "PersonAddress.error.endDateBeforeStartDate", new Object[] { "'" + addressString
			        + "'" }, "The End Date for address '" + addressString + "' shouldn't be earlier than the Start Date");
		
	}