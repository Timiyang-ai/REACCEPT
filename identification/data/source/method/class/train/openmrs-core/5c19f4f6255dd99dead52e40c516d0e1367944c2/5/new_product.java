@Override
	public void validate(Object target, Errors errors) {
		if (log.isDebugEnabled()) {
			log.debug(this.getClass().getName() + ".validate...");
		}
		
		if (target == null) {
			return;
		}
		
		Person person = (Person) target;
		
		int index = 0;
		boolean atLeastOneNonVoidPersonNameLeft = false;
		for (PersonName personName : person.getNames()) {
			errors.pushNestedPath("names[" + index + "]");
			personNameValidator.validate(personName, errors);
			if (!personName.isVoided()) {
				atLeastOneNonVoidPersonNameLeft = true;
			}
			errors.popNestedPath();
			index++;
		}
		if (!person.isVoided() && !atLeastOneNonVoidPersonNameLeft) {
			errors.rejectValue("names", "Person.shouldHaveAtleastOneNonVoidedName");
		}
		
		// validate the personAddress
		index = 0;
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
		validateDeathDate(errors, person.getDeathDate(), person.getBirthdate());
		
		if (person.isVoided()) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "voidReason", "error.null");
		}
		if (person.isDead()) {
			ValidationUtils.rejectIfEmpty(errors, "causeOfDeath", "Person.dead.causeOfDeathNull");
		}
		
		ValidateUtil.validateFieldLengths(errors, Person.class, "gender", "personVoidReason");
	}