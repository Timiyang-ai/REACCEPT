@Test
public void saveEncounter_shouldCascadeSaveToContainedObs() {
    EncounterService es = Context.getEncounterService();
    // First, create a new Encounter
    Encounter enc = buildEncounter();
    
    //add an obs to the encounter
    Obs groupObs = new Obs();
    Concept c = Context.getConceptService().getConcept(1);
    groupObs.setConcept(c);
    
    // add an obs to the group
    Obs childObs = new Obs();
    childObs.setConcept(c);
    childObs.setValueNumeric(50d);
    groupObs.addGroupMember(childObs);
    enc.addObs(groupObs);
    
    //confirm that save and new enc id are cascaded to obs groupMembers
    //even though childObs aren't directly associated to enc
    assertNotNull("save succeeds without error", es.saveEncounter(enc));
    assertTrue("enc save succeeds", enc.getId() > 0);
    
    assertNotNull("obs save succeeds", groupObs.getObsId());
    assertEquals("encounter id propagated", groupObs.getEncounter().getId(), enc.getId());
    assertEquals("encounter time propagated", groupObs.getObsDatetime(), enc.getEncounterDatetime());
    assertNotNull("obs save succeeds", childObs.getObsId());
    assertEquals("encounter id propagated", childObs.getEncounter().getId(), enc.getId());
    assertEquals("encounter time propagated", childObs.getObsDatetime(), enc.getEncounterDatetime());
    
    // Check if the encounter datetime is propagated correctly to all observations
    Date encounterDatetime = DateUtil.truncateToSeconds(enc.getEncounterDatetime());
    for (Obs o : enc.getAllObs(false)) {
        assertEquals("encounter datetime propagated", DateUtil.truncateToSeconds(o.getObsDatetime()), encounterDatetime);
    }
}