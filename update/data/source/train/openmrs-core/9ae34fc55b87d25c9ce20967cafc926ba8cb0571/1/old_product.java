@Override
	public Cohort addPatientToCohort(Cohort cohort, Patient patient) {
		if (!cohort.contains(patient.getPatientId())) {
			CohortMembership cohortMembership = new CohortMembership(patient);
			Context.getCohortService().addMembershipToCohort(cohort, cohortMembership);
			Context.getCohortService().saveCohort(cohort);
		}
		return cohort;
	}