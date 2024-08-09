@Override
	@Transactional(readOnly = true)
	public List<Diagnosis> getUniqueDiagnoses(Patient patient, Date fromDate) {
		List<Diagnosis> diagnoses = getDiagnoses(patient, fromDate);
		Set<CodedOrFreeText> answers = new HashSet<CodedOrFreeText>();
		Iterator<Diagnosis> iterator = diagnoses.iterator();
		while(iterator.hasNext()) {
			Diagnosis diagnosis = iterator.next();

			if (!answers.add(diagnosis.getDiagnosis())) {
				iterator.remove();
			}
		}
		return diagnoses;
	}