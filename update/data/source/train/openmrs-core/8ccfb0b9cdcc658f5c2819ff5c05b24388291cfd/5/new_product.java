@Override
	public Cohort removePatientFromCohort(Cohort cohort, Patient patient) {
		if (cohort.contains(patient.getPatientId())) {
			CohortMembership membership = cohort.getActiveMembership(patient);
			Context.getCohortService().removeMembershipFromCohort(cohort, membership);
		}
		return cohort;
	}