@Override
	@Transactional(readOnly = true)
	public void checkPatientIdentifiers(Patient patient) throws PatientIdentifierException {
		// check patient has at least one identifier
		if (!patient.getVoided() && patient.getActiveIdentifiers().isEmpty()) {
			throw new InsufficientIdentifiersException("At least one nonvoided Patient Identifier is required");
		}
		
		List<PatientIdentifier> patientIdentifiers = new ArrayList<>();
		patientIdentifiers.addAll(patient.getIdentifiers());
		List<PatientIdentifierType> requiredTypes = this.getPatientIdentifierTypes(null, null, true, null);
		if (requiredTypes == null) {
			requiredTypes = new ArrayList<PatientIdentifierType>();
		}

		List<String> identifiersUsed = new ArrayList<>();

		for (PatientIdentifier pi : patientIdentifiers) {
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