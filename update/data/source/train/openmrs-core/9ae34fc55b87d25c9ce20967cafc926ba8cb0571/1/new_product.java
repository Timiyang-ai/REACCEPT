@Override
	public Cohort addPatientToCohort(Cohort cohort, Patient patient) {
		if (!cohort.contains(patient.getPatientId())) {
			CohortMembership cohortMembership = new CohortMembership(patient.getPatientId());
			Context.getCohortService().addMembershipToCohort(cohort, cohortMembership);
		}
		return cohort;
	}