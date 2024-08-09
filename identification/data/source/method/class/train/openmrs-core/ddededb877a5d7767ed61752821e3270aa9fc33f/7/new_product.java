public void checkPatientIdentifiers(Patient patient)
	        throws PatientIdentifierException {
		// check patient has at least one identifier
		if (patient.getIdentifiers().size() < 1 )
			throw new InsufficientIdentifiersException("At least one Patient Identifier is required");

		List<PatientIdentifier> identifiers = new Vector<PatientIdentifier>();
		identifiers.addAll(patient.getIdentifiers());
		List<String> identifiersUsed = new Vector<String>();
		List<PatientIdentifierType> requiredTypes = getPatientIdentifierTypes(null, null, true, null);
		if (requiredTypes == null)
			requiredTypes = new ArrayList<PatientIdentifierType>();
		List<PatientIdentifierType> foundRequiredTypes = new ArrayList<PatientIdentifierType>();

		for (PatientIdentifier pi : identifiers) {
			if (pi.isVoided())
				continue;

			try {
				checkPatientIdentifier(pi);
			} catch ( BlankIdentifierException bie ) {
				patient.removeIdentifier(pi);
				throw bie;
			}
			
			// check if this is a required identifier
			for ( PatientIdentifierType requiredType : requiredTypes ) {
				if ( pi.getIdentifierType().equals(requiredType) ) {
					foundRequiredTypes.add(requiredType);
					requiredTypes.remove(requiredType);
					break;
				}
			}

			// TODO: check patient has at least one "sufficient" identifier

			// check this patient for duplicate identifiers
			if (identifiersUsed.contains(pi.getIdentifier())) {
				patient.removeIdentifier(pi);
				throw new DuplicateIdentifierException("This patient has two identical identifiers of type "
				                                               + pi.getIdentifierType()
				                                                   .getName()
				                                               + ": "
				                                               + pi.getIdentifier()
				                                               + ", deleting one of them",
				                                       pi);
			}
			
			else
				identifiersUsed.add(pi.getIdentifier());
		}

		if ( requiredTypes.size() > 0 ) {
			String missingNames = "";
			for ( PatientIdentifierType pit : requiredTypes ) {
				missingNames += (missingNames.length() > 0) ? ", "
				        + pit.getName() : pit.getName();
			}
			throw new MissingRequiredIdentifierException("Patient is missing the following required identifier(s): "
			        + missingNames);
		}
	}