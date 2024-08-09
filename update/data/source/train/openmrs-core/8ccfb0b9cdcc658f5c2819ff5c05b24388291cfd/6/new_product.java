@Override
	public Cohort addMembershipToCohort(Cohort cohort, CohortMembership cohortMembership) throws APIException {
		cohort.addMembership(cohortMembership);
		return Context.getCohortService().saveCohort(cohort);
	}