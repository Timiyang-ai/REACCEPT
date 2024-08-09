@Test
public void compareTo_shouldCorrectlyCompareStartAndEndDates() throws Exception {
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
    
    int comparisonResult = firstMembership.compareTo(secondMembership);
    assertTrue("Expected first membership to be considered earlier than second membership", comparisonResult < 0);
    
    secondMembership.setStartDate(oneDate);
    secondMembership.setEndDate(twoDate);
    
    comparisonResult = firstMembership.compareTo(secondMembership);
    assertTrue("Expected memberships with the same start date but different end dates to be considered differently", comparisonResult != 0);
}