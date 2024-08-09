	@Test
	public void getCommaSeparatedPatientIds_shouldReturnCommaSeparatedListOfPatients() {
		
		List<Patient> patients = new ArrayList<>();
		Arrays.stream(ids).forEach(id -> patients.add(new Patient(id)));
		
		Cohort cohort = new Cohort("name", "description", patients);
		
		String[] ids = StringUtils.split(cohort.getCommaSeparatedPatientIds(), ',');
		Arrays.stream(ids).forEach(id -> patients.contains(new Patient(Integer.valueOf(id))));
		
	}