@Test
public void compareTo_shouldCorrectlyCompareCohortMemberships() throws Exception {
    CohortMembership firstMembership = new CohortMembership(4);
    CohortMembership secondMembership = new CohortMembership(4);

    Cohort cohort = new Cohort(1);

    firstMembership.setCohort(cohort);
    secondMembership.setCohort(cohort);

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date oneDate = dateFormat.parse("2017-01-01 00:00:00");
    Date twoDate = dateFormat.parse("2017-01-31 00:00:00");

    // Setting start dates
    firstMembership.setStartDate(oneDate);
    secondMembership.setStartDate(twoDate);

    // First membership should come before the second due to earlier start date
    assertTrue("First membership should come before second", firstMembership.compareTo(secondMembership) < 0);

    // Setting the same start date for both memberships
    secondMembership.setStartDate(oneDate);

    // Setting end date for the second membership
    secondMembership.setEndDate(twoDate);

    // First membership should still come before the second as the second one has an end date
    assertTrue("First membership without end date should come before second with end date", firstMembership.compareTo(secondMembership) < 0);

    // Setting end date for the first membership earlier than the second
    firstMembership.setEndDate(dateFormat.parse("2017-01-15 00:00:00"));

    // Now, first membership should come before the second due to earlier end date
    assertTrue("First membership with earlier end date should come before second", firstMembership.compareTo(secondMembership) < 0);

    // Testing with voided statuses
    firstMembership.setVoided(true);
    secondMembership.setVoided(false);

    // Voided memberships should come after non-voided ones
    assertTrue("Voided membership should come after non-voided", firstMembership.compareTo(secondMembership) > 0);

    // Reversing voided statuses
    firstMembership.setVoided(false);
    secondMembership.setVoided(true);

    // Non-voided memberships should come before voided ones
    assertTrue("Non-voided membership should come before voided", firstMembership.compareTo(secondMembership) < 0);
}