@Test
public void compareTo_shouldCorrectlyEvaluateStartAndEndDateDifferences() throws Exception {
    CohortMembership firstMembership = new CohortMembership(4);
    CohortMembership secondMembership = new CohortMembership(4);
    
    Cohort cohort = new Cohort(1);
    
    firstMembership.setCohort(cohort);
    secondMembership.setCohort(cohort);
    
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date startDate = dateFormat.parse("2017-01-01 00:00:00");
    Date endDate = dateFormat.parse("2017-01-31 00:00:00");
    
    // Setting start dates to the same for both memberships and differing the end dates
    firstMembership.setStartDate(startDate);
    secondMembership.setStartDate(startDate);
    secondMembership.setEndDate(endDate); // Only the second membership has end date
    
    // Comparing memberships when only one has an end date
    assertNotEquals("Expected first membership to not equal to the second when only one has an end date", 0, firstMembership.compareTo(secondMembership));
    
    // Both memberships have the same start and end dates
    firstMembership.setEndDate(endDate); // now both memberships have end dates
    
    // Comparing memberships when both have same start and end dates
    assertEquals("Expected memberships to be equal when both start and end dates are the same", 0, firstMembership.compareTo(secondMembership));

    // Adjusting the end date for secondMembership to test comparison again
    secondMembership.setEndDate(dateFormat.parse("2017-02-01 00:00:00")); // second's end date is later
    
    // Comparing memberships when both have start dates but different end dates
    assertTrue("Expected first membership to be less than the second when end date is earlier", firstMembership.compareTo(secondMembership) < 0);
}