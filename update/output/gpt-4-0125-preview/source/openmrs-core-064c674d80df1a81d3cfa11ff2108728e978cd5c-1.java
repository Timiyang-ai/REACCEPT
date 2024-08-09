@Test
public void compareTo_shouldCompareByVoidStatusCohortPatientIdStartAndEndDate() throws Exception {
	CohortMembership firstMembership = new CohortMembership(4);
	CohortMembership secondMembership = new CohortMembership(4);

	Cohort cohort = new Cohort(1);

	firstMembership.setCohort(cohort);
	secondMembership.setCohort(cohort);

	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Date oneDate = dateFormat.parse("2017-01-01 00:00:00");
	Date twoDate = dateFormat.parse("2017-01-31 00:00:00");

	firstMembership.setStartDate(oneDate);
	secondMembership.setStartDate(twoDate);

	// Compare two memberships when their start dates are different
	int compareResult = firstMembership.compareTo(secondMembership);
	assertTrue("Expected first membership to be less than second membership", compareResult < 0);

	// Set the same start date on second membership to check end date comparison
	secondMembership.setStartDate(oneDate);
	secondMembership.setEndDate(twoDate);

	// Since firstMembership end date is null, and considering the new comparison logic where null is treated as latest,
	// firstMembership should be greater than secondMembership
	compareResult = firstMembership.compareTo(secondMembership);
	assertTrue("Expected first membership to be greater than second membership when only end date differs", compareResult > 0);

	// Update test to reflect the compareTo method's change in handling null values in end date comparison
	Date nullDate = null;
	firstMembership.setEndDate(nullDate);
	secondMembership.setEndDate(nullDate);

	// When both end dates are null, they should be considered equal
	compareResult = firstMembership.compareTo(secondMembership);
	assertEquals("Expected both memberships to be equal when end dates are null", 0, compareResult);
}