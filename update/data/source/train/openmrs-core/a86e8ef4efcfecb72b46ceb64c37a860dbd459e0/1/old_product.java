public Cohort removePatientFromCohort(Cohort cohort, Patient patient) {
		if (cohort.contains(patient)) {
			cohort.getMemberIds().remove(patient.getPatientId());
			Context.getCohortService().saveCohort(cohort);
		}
		return cohort;
	}