@Test
public void saveEncounter_shouldCascadeSaveToContainedObs() {
    EncounterService es = Context.getEncounterService();
    ConceptService conceptService = Context.getConceptService();
    
    // Assuming buildEncounter is a method that correctly sets up and returns a new Encounter instance
    Encounter enc = buildEncounter();
    
    // Ensure the Concept and Obs instances are correctly set up
    Concept c = conceptService.getConcept(1);
    assertNotNull("Concept should not be null", c);
    
    Obs groupObs = new Obs();
    groupObs.setConcept(c);
    
    Obs childObs = new Obs();
    childObs.setConcept(c);
    childObs.setValueNumeric(50d);
    groupObs.addGroupMember(childObs);
    enc.addObs(groupObs);
    
    // Save the encounter, which should cascade save the obs
    Encounter savedEncounter = es.saveEncounter(enc);
    assertNotNull("Encounter save should succeed", savedEncounter);
    assertNotNull("Encounter ID should be set", savedEncounter.getId());
    
    // Assuming getAllObs is correctly retrieving all observations, including child observations
    // Adjust based on the actual method to retrieve flattened observations if needed
    boolean foundGroupObs = false;
    boolean foundChildObs = false;
    for (Obs obs : savedEncounter.getAllObs(false)) {
        if (obs.equals(groupObs)) {
            foundGroupObs = true;
        }
        if (obs.getGroupMembers() != null && obs.getGroupMembers().contains(childObs)) {
            foundChildObs = true;
        }
    }
    
    assertTrue("Group Obs should be found in the encounter", foundGroupObs);
    assertTrue("Child Obs should be found as a member of the group Obs", foundChildObs);
}