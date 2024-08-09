public Cohort removePatientFromCohort(Cohort cohort, Patient patient) {
		if (cohort.contains(patient.getPatientId())) {
			cohort.removeMember(patient);
			Context.getCohortService().saveCohort(cohort);
		}
		return cohort;
	}