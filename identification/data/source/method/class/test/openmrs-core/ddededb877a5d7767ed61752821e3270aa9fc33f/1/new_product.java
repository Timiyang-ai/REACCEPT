@Override
	@Transactional(readOnly = true)
	public void checkPatientIdentifiers(Patient patient) throws PatientIdentifierException {
		// check patient has at least one identifier
		if (!patient.getVoided() && patient.getActiveIdentifiers().isEmpty()) {
			throw new InsufficientIdentifiersException("At least one nonvoided Patient Identifier is required");
		}

		final List<PatientIdentifier> patientIdentifiers = new ArrayList<>(patient.getIdentifiers());

		final Set<String> uniqueIdentifiers = new HashSet<>();

		patientIdentifiers.stream()
			.filter(pi -> !pi.getVoided())
			.forEach(pi -> {
				try {
					PatientIdentifierValidator.validateIdentifier(pi);
				}
				catch (BlankIdentifierException bie) {
					patient.removeIdentifier(pi);
					throw bie;
				}

				// check this patient for duplicate identifiers+identifierType
				String compareString = pi.getIdentifier() + " id type #: " + pi.getIdentifierType().getPatientIdentifierTypeId();
				if(! uniqueIdentifiers.add(compareString)) {
					throw new DuplicateIdentifierException("This patient has two identical identifiers of type "
							+ compareString, pi);
				}
			});

		checkForMissingRequiredIdentifiers(patientIdentifiers);

	}