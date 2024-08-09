@Test
public void compareTo_shouldReflectNewComparisonLogic() throws Exception {
    CohortMembership firstMembership = new CohortMembership(4);
    CohortMembership secondMembership = new CohortMembership(4);

    Cohort cohort = new Cohort(1);

    firstMembership.setCohort(cohort);
    secondMembership.setCohort(cohort);

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date oneDate = dateFormat.parse("2017-01-01 00:00:00");
    Date twoDate = dateFormat.parse("2017-01-31 00:00:00");

    // Setting start dates to be the same and end dates to differ
    firstMembership.setStartDate(oneDate);
    secondMembership.setStartDate(oneDate);
    secondMembership.setEndDate(twoDate);

    // First membership has no end date, should be considered "earlier" than second
    assertEquals("First membership should be considered earlier due to lack of end date", -1, firstMembership.compareTo(secondMembership));

    // Setting both to active, but second membership is voided
    firstMembership.setVoided(false);
    secondMembership.setVoided(true);

    // First membership is active and not voided, should be considered "earlier" than second which is voided
    assertEquals("Active, non-voided membership should be considered earlier than a voided one", -1, firstMembership.compareTo(secondMembership));

    // Now both are active, but first is voided
    firstMembership.setVoided(true);
    secondMembership.setVoided(false);

    // Now first membership is voided, should be considered "later" than the active, non-voided second membership
    assertEquals("Voided membership should be considered later than an active, non-voided one", 1, firstMembership.compareTo(secondMembership));

    // Resetting voided status and making both memberships have the same start and end dates
    firstMembership.setVoided(false);
    secondMembership.setVoided(false);
    firstMembership.setEndDate(twoDate);

    // Both memberships are now identical in terms of dates and voided status, should be considered equal
    assertEquals("Memberships with same start and end dates, both active and non-voided, should be considered equal", 0, firstMembership.compareTo(secondMembership));
}