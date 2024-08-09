@Override
	public void validate(Object target, Errors errors) {
		log.debug("{}.validate...", this.getClass().getName());
		
		if (target == null) {
			return;
		}
		
		Person person = (Person) target;
		
		validatePersonNames(person, errors);
		
		if (!person.getVoided() && !validatePersonHasAtLeastOneNonVoidedName(person)) {
			errors.rejectValue("names", "Person.shouldHaveAtleastOneNonVoidedName");
		}
		
		validatePersonAddresses(person, errors);
		
		validateBirthDate(errors, person.getBirthdate());
		validateDeathDate(errors, person.getDeathDate(), person.getBirthdate());
		if (person.getVoided()) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "voidReason", "error.null");
		}
		
		validateDeathCause(person, errors);
		
		ValidateUtil.validateFieldLengths(errors, Person.class, "gender", "personVoidReason");
	}