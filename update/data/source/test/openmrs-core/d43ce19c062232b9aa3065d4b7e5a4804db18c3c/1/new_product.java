@Override
	@Transactional(readOnly = true)
	public void checkPatientIdentifiers(Patient patient) throws PatientIdentifierException {
		// check patient has at least one identifier
		if (!patient.getVoided() && patient.getActiveIdentifiers().isEmpty()) {
			throw new InsufficientIdentifiersException("At least one nonvoided Patient Identifier is required");
		}
		
		List<PatientIdentifier> identifiers = new Vector<PatientIdentifier>();
		identifiers.addAll(patient.getIdentifiers());
		List<String> identifiersUsed = new Vector<String>();
		List<PatientIdentifierType> requiredTypes = Context.getPatientService().getPatientIdentifierTypes(null, null, true,
		    null);
		if (requiredTypes == null) {
			requiredTypes = new ArrayList<PatientIdentifierType>();
		}
		List<PatientIdentifierType> foundRequiredTypes = new ArrayList<PatientIdentifierType>();
		
		for (PatientIdentifier pi : identifiers) {
			if (pi.getVoided()) {
				continue;
			}
			
			try {
				PatientIdentifierValidator.validateIdentifier(pi);
			}
			catch (BlankIdentifierException bie) {
				patient.removeIdentifier(pi);
				throw bie;
			}
			
			// check if this is a required identifier
			for (PatientIdentifierType requiredType : requiredTypes) {
				if (pi.getIdentifierType().equals(requiredType)) {
					foundRequiredTypes.add(requiredType);
					requiredTypes.remove(requiredType);
					break;
				}
			}
			
			// check this patient for duplicate identifiers+identifierType
			if (identifiersUsed.contains(pi.getIdentifier() + " id type #: "
			        + pi.getIdentifierType().getPatientIdentifierTypeId())) {
				throw new DuplicateIdentifierException("This patient has two identical identifiers of type "
				        + pi.getIdentifierType().getName() + ": " + pi.getIdentifier(), pi);
			} else {
				identifiersUsed.add(pi.getIdentifier() + " id type #: "
				        + pi.getIdentifierType().getPatientIdentifierTypeId());
			}
		}
		
		if (!requiredTypes.isEmpty()) {
			String missingNames = "";
			for (PatientIdentifierType pit : requiredTypes) {
				missingNames += (missingNames.length() > 0) ? ", " + pit.getName() : pit.getName();
			}
			throw new MissingRequiredIdentifierException("Patient is missing the following required identifier(s): "
			        + missingNames);
		}
	}