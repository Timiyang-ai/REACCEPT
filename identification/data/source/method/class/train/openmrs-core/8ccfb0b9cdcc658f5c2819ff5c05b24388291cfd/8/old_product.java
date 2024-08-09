public Cohort addMembershipToCohort(Cohort cohort, CohortMembership cohortMembership) throws APIException {
		cohort.addMembership(cohortMembership);
		Context.getCohortService().saveCohort(cohort);
		return cohort;
	}