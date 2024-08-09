@Override
	public void validate(Object target, Errors errors) {
		if (log.isDebugEnabled()) {
			log.debug(this.getClass().getName() + ".validate...");
		}
		
		if (target == null) {
			return;
		}
		
		Person person = (Person) target;
		
		boolean atLeastOneNonVoidPersonNameLeft = false;
		for (PersonName personName : person.getNames()) {
			personNameValidator.validate(personName, errors);
			if (!personName.isVoided()) {
				atLeastOneNonVoidPersonNameLeft = true;
			}
		}
		if (!atLeastOneNonVoidPersonNameLeft) {
			errors.rejectValue("names", "Person.shouldHaveAtleastOneNonVoidedName");
		}
		
		//validate the personAddress
		int index = 0;
		for (PersonAddress address : person.getAddresses()) {
			try {
				errors.pushNestedPath("addresses[" + index + "]");
				ValidationUtils.invokeValidator(personAddressValidator, address, errors);
			}
			finally {
				errors.popNestedPath();
				index++;
			}
		}
		
		validateBirthDate(errors, person.getBirthdate());
		
		if (person.isVoided()) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "voidReason", "error.null");
		}
		if (person.isDead()) {
			ValidationUtils.rejectIfEmpty(errors, "causeOfDeath", "Person.dead.causeOfDeathNull");
		}
		
		ValidateUtil.validateFieldLengths(errors, Person.class, "gender");
	}