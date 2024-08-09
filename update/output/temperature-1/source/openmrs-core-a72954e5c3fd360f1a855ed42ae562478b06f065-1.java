@Test
public void compareTo_shouldCorrectlyCompareCohortMemberships() throws Exception {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date startDate = dateFormat.parse("2020-01-01 00:00:00");
    Date endDate = dateFormat.parse("2020-01-31 00:00:00");

    CohortMembership firstMembership = new CohortMembership(4);
    firstMembership.setCohort(new Cohort(1));
    firstMembership.setStartDate(startDate);
    firstMembership.setEndDate(endDate);
    firstMembership.setVoided(false);
    
    CohortMembership secondMembership = new CohortMembership(4);
    secondMembership.setCohort(new Cohort(1));
    secondMembership.setStartDate(startDate);
    // No end date for the second membership, it's considered active.

    // The second membership is active, whereas the first is not due to an end date being set.
    assertEquals("Active membership should come before inactive membership", 1, firstMembership.compareTo(secondMembership));
    
    secondMembership.setVoided(true); // Now, second membership is voided.
    assertEquals("Non-voided membership should come before voided membership", -1, firstMembership.compareTo(secondMembership));

    // Setting both to the same state
    firstMembership.setVoided(true);
    secondMembership.setVoided(false); 
    
    assertEquals("Voided membership should come after non-voided membership", 1, firstMembership.compareTo(secondMembership));
    
    // Both memberships are active now.
    firstMembership.setEndDate(null);
    secondMembership.setEndDate(null);
    firstMembership.setVoided(false);
    secondMembership.setVoided(false);
    // Test comparison by patient ID and start date
    assertEquals("Memberships with the same start date and patient id should be equal", 0, firstMembership.compareTo(secondMembership));
}