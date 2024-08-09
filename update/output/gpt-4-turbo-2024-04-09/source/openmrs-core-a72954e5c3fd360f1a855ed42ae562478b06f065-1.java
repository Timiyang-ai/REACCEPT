@Test
public void compareTo_shouldFailIfStartOrEndDateDoNotMatch() throws Exception {
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

    assertEquals(-1, firstMembership.compareTo(secondMembership));

    secondMembership.setStartDate(oneDate);
    secondMembership.setEndDate(twoDate);

    // Adjusted to reflect the correct expected result based on the new comparison logic
    int comparisonResult = firstMembership.compareTo(secondMembership);
    assertTrue("Comparison should consider end date", comparisonResult != 0);
    assertEquals("End date should affect comparison", -1, comparisonResult);
}