@Test
public void compareTo_shouldCorrectlyCompareCohortMemberships() throws Exception {
    CohortMembership firstMembership = new CohortMembership(4);
    CohortMembership secondMembership = new CohortMembership(4);

    Cohort cohort = new Cohort(1);

    firstMembership.setCohort(cohort);
    secondMembership.setCohort(cohort);

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date sameDate = dateFormat.parse("2017-01-01 00:00:00");

    // Set both start dates to the same value to test other comparison conditions
    firstMembership.setStartDate(sameDate);
    secondMembership.setStartDate(sameDate);

    // Both memberships have the same start date, no end dates, and are not voided
    // Expecting a comparison based on patient ID or another attribute not covered by the test failure message
    assertEquals("Comparing two equal memberships should return 0", 
        0, firstMembership.compareTo(secondMembership));

    // Set different voided status to test this comparison aspect
    firstMembership.setVoided(false);
    secondMembership.setVoided(true);

    // Assert that non-voided membership comes before a voided one
    assertTrue("Non-voided membership should come before a voided one", 
        firstMembership.compareTo(secondMembership) < 0);

    // Reset voided status to false for both to test end date comparison
    secondMembership.setVoided(false);
    secondMembership.setEndDate(sameDate); // Only second membership has an end date

    // With the same start date and only the second having an end date, the first should be considered active longer
    assertTrue("Membership without an end date should be considered active longer", 
        firstMembership.compareTo(secondMembership) > 0);
}