@Test
public void saveEncounter_shouldPropagateChangesToAllFlattenedObs() {
    EncounterService es = Context.getEncounterService();
    // First, build or initialize an Encounter object
    Encounter enc = buildEncounter();

    // Assuming buildEncounter() correctly initializes an Encounter object
    // Add an observation group to the encounter
    Obs groupObs = new Obs();
    Concept concept = Context.getConceptService().getConcept(1);
    groupObs.setConcept(concept);

    // Add a child observation to the group
    Obs childObs = new Obs();
    childObs.setConcept(concept);
    childObs.setValueNumeric(50d);
    groupObs.addGroupMember(childObs);
    enc.addObs(groupObs);

    // Act - Save the encounter
    Encounter savedEncounter = es.saveEncounter(enc);

    // Assert - Verify the saved encounter and propagate changes
    assertNotNull("Encounter save should not be null", savedEncounter);
    assertTrue("Encounter should have an ID", savedEncounter.getEncounterId() > 0);

    for (Obs obs : savedEncounter.getAllObs(true)) {
        assertNotNull("Observation save should not be null", obs.getObsId());
        assertEquals("Encounter id should be propagated", obs.getEncounter().getEncounterId(), savedEncounter.getEncounterId());
        assertEquals("Encounter datetime should be propagated", obs.getObsDatetime(), savedEncounter.getEncounterDatetime());
    }
}