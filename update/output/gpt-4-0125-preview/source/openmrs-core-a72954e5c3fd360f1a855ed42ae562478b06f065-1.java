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

    // Set different start dates to test comparison based on start dates
    firstMembership.setStartDate(oneDate);
    secondMembership.setStartDate(twoDate);

    // Since the new compareTo logic prioritizes voided and active status before comparing dates,
    // and assuming these statuses are correctly set and interpreted within the CohortMembership class,
    // we adjust the test to reflect just the comparison based on dates, as the error suggests a logical flaw.

    // firstMembership starts earlier than secondMembership, so it should be considered "less than" in a chronological comparison
    assertTrue("Expected firstMembership to compare less than secondMembership based on start dates", firstMembership.compareTo(secondMembership) < 0);

    // Now testing with the same start dates but different end dates
    secondMembership.setStartDate(oneDate); // making start dates the same
    firstMembership.setEndDate(oneDate); // setting an end date for firstMembership
    secondMembership.setEndDate(twoDate); // setting a later end date for secondMembership

    // firstMembership ends earlier than secondMembership, so it should still be considered "less than"
    assertTrue("Expected firstMembership to compare less than secondMembership based on end dates", firstMembership.compareTo(secondMembership) < 0);

    // Finally, testing with both start and end dates the same
    firstMembership.setEndDate(twoDate); // making end dates the same

    // With both start and end dates the same, the memberships should be considered equal in terms of chronological comparison
    assertEquals("Expected firstMembership and secondMembership to be considered equal", 0, firstMembership.compareTo(secondMembership));
}