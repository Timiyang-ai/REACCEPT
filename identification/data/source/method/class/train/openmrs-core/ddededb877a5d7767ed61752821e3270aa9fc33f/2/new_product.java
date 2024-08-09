private void checkPatientIdentifiers(Patient patient) throws PatientIdentifierException {
		// TODO create a temporary identifier?
		if (patient.getIdentifiers().size() < 1 )
			throw new InsufficientIdentifiersException("At least one Patient Identifier is required");
		
		List<String> identifiersUsed = new Vector<String>();
		
		List<PatientIdentifier> identifiers = new Vector<PatientIdentifier>();
		identifiers.addAll(patient.getIdentifiers());

		// Check for required identifiers
		// TODO: decide if we actually want to use this
		/*
		List<PatientIdentifierType> requiredTypes = this.getRequiredPatientIdentifierTypes();
		for ( PatientIdentifierType requiredType : requiredTypes ) {
			boolean isFound = false;
			for ( PatientIdentifier identifier : identifiers ) {
				if ( identifier.getIdentifierType().equals(requiredType)) isFound = true;
			}
			if ( !isFound ) throw new APIException("Patient is missing the following required identifier: " + requiredType.getName());
		}
		*/
		
		// Check for duplicate identifiers and identifiers
		for (PatientIdentifier pi : identifiers) {
			// skip voided identifiers
			if (pi.isVoided()) continue;
			
			// skip and remove invalid/empty identifiers
			if (pi.getIdentifier() == null || pi.getIdentifier().length() == 0) {
				patient.removeIdentifier(pi);
				continue;
			}
			
			// check this patient for duplicate identifiers
			if (pi.getIdentifierType().hasCheckDigit()) {
				if (identifiersUsed.contains(pi.getIdentifier()))
					throw new DuplicateIdentifierException("This patient has duplicate identifiers for: " + pi.getIdentifier(), pi.getIdentifier());
				else
					identifiersUsed.add(pi.getIdentifier());
			}
				
			// compare against other identifiers matching the id string and id type
			Patient p = identifierInUse(pi.getIdentifier(), pi.getIdentifierType(), patient);
			if (p != null)
				throw new IdentifierNotUniqueException("Identifier " + pi.getIdentifier() + " already in use by patient #" + p.getPatientId(), pi.getIdentifier());
			
			// validate checkdigit
			PatientIdentifierType pit = pi.getIdentifierType();
			String identifier = pi.getIdentifier();

			try {
				if (pit.hasCheckDigit() && !OpenmrsUtil.isValidCheckDigit(identifier)) {
					log.error("hasCheckDigit and is not valid: " + pit.getName() + " " + identifier);
					throw new InvalidCheckDigitException("Invalid check digit for identifier: " + identifier, identifier);
				}
			} catch (Exception e) {
				throw new InvalidCheckDigitException("Invalid check digit for identifier: " + identifier, identifier);
			}
				
			// also validate regular expression - if it exists
			String regExp = pit.getFormat();
			if ( regExp != null ) {
				if ( regExp.length() > 0 ) {
					// if this ID has a valid corresponding regular expression, check against it
					log.debug("Trying to match " + identifier + " and " + regExp);
					if ( !identifier.matches(regExp) ) {
						log.debug("The two DO NOT match");
						if ( pit.getFormatDescription() != null ) {
							if ( pit.getFormatDescription().length() > 0 ) throw new InvalidIdentifierFormatException("Identifier [" + identifier + "] does not match required format: " + pit.getFormatDescription(), identifier, pit.getFormat());
							else throw new InvalidIdentifierFormatException("Identifier [" + identifier + "] does not match required format: " + pit.getFormat(), identifier, pit.getFormat());
						} else {
							throw new InvalidIdentifierFormatException("Identifier [" + identifier + "] does not match required format: " + pit.getFormat(), identifier, pit.getFormat());
						}
					} else {
						log.debug("The two match!!");
					}
				}
			}

			// Changed by Christian - 21 Dec 2006 - I think we can leave this up to regexp, what characters are valid or not
			/*
			else if (pit.hasCheckDigit() == false && identifier.contains("-")) {
				log.error("hasn't CheckDigit and contains '-': " + pit.getName() + " " + identifier);
				throw new APIException("Invalid character for non-checkdigit identifier " + identifier);
			}
			// Changed by Christian - 21 Dec 2006 - I think we are already throwing Exceptions exactly the way we want already - don't want to catch them again here
			} catch (Exception e) {
				log.error("exception thrown with: " + pit.getName() + " " + identifier);
				log.error("Error while adding patient identifiers to savedIdentifier list", e);
				throw new PatientIdentifierException("Invalid identifier " + identifier);
			}
			*/
		}
		
	}