@Override
	public void validate(Object object, Errors errors) {
		//TODO Validate other aspects of the personAddress object
		if (log.isDebugEnabled()) {
			log.debug(this.getClass().getName() + ".validate...");
		}
		
		if (object == null) {
			throw new IllegalArgumentException("The personAddress object should not be null");
		}
		
		PersonAddress personAddress = (PersonAddress) object;
		
		//resolve a shorter name to display along with the error message
		String addressString = null;
		if (StringUtils.isNotBlank(personAddress.getAddress1())) {
			addressString = personAddress.getAddress1();
		} else if (StringUtils.isNotBlank(personAddress.getAddress2())) {
			addressString = personAddress.getAddress2();
		} else if (StringUtils.isNotBlank(personAddress.getCityVillage())) {
			addressString = personAddress.getCityVillage();
		} else {
			addressString = personAddress.toString();
		}
		
		if (OpenmrsUtil.compareWithNullAsEarliest(personAddress.getStartDate(), new Date()) > 0) {
			errors.rejectValue("startDate", "PersonAddress.error.startDateInFuture", new Object[] { "'" + addressString
			        + "'" }, "The Start Date for address '" + addressString + "' shouldn't be in the future");
		}
		
		if (personAddress.getStartDate() != null
		        && OpenmrsUtil.compareWithNullAsLatest(personAddress.getStartDate(), personAddress.getEndDate()) > 0) {
			errors.rejectValue("endDate", "PersonAddress.error.endDateBeforeStartDate", new Object[] { "'" + addressString
			        + "'" }, "The End Date for address '" + addressString + "' shouldn't be earlier than the Start Date");
		}
		
		String xml = Context.getLocationService().getAddressTemplate();
		List<String> requiredElements;
		
		try {
			AddressTemplate addressTemplate = Context.getSerializationService().getDefaultSerializer().deserialize(xml,
			    AddressTemplate.class);
			requiredElements = addressTemplate.getRequiredElements();
		}
		catch (Exception e) {
			errors.reject(Context.getMessageSourceService().getMessage("AddressTemplate.error"));
			return;
		}
		
		if (requiredElements != null) {
			for (String fieldName : requiredElements) {
				try {
					Object value = PropertyUtils.getProperty(personAddress, fieldName);
					if (StringUtils.isBlank((String) value)) {
						//required field not found
						errors.reject(Context.getMessageSourceService().getMessage(
						    "AddressTemplate.error.requiredAddressFieldIsBlank", new Object[] { fieldName },
						    Context.getLocale()));
					}
				}
				catch (Exception e) {
					//wrong field declared in template
					errors
					        .reject(Context.getMessageSourceService().getMessage(
					            "AddressTemplate.error.fieldNotDeclaredInTemplate", new Object[] { fieldName },
					            Context.getLocale()));
				}
			}
		}
		
		ValidateUtil.validateFieldLengths(errors, object.getClass(), "address1", "address2", "cityVillage", "stateProvince",
		    "postalCode", "country", "latitude", "longitude", "voidReason", "countyDistrict", "address3", "address4",
		    "address5", "address6", "address7", "address8", "address9", "address10", "address11", "address12", "address13", 
		    "address14", "address15");
	}