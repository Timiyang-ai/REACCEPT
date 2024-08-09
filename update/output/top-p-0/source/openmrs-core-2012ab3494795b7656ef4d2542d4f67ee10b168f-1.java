@Test
public void saveConcept_shouldAutomaticallySetIsSetForConceptsWithMembers() throws Exception {
    Locale locale = new Locale("en", "US");
    ConceptService conceptService = Context.getConceptService();

    // Create and save a concept class and datatype to avoid validation errors
    ConceptDatatype datatype = conceptService.getConceptDatatypeByName("Text");
    ConceptClass conceptClass = conceptService.getConceptClassByName("Test");

    // Ensure these entities are persisted before using them in a Concept
    if (datatype == null || conceptClass == null) {
        throw new IllegalStateException("Required concept datatype or class not found.");
    }

    // Create a concept with required fields to pass validation
    Concept concept = new Concept();
    ConceptName conceptName = new ConceptName("Test Concept", locale);
    concept.addName(conceptName);
    concept.setDatatype(datatype);
    concept.setConceptClass(conceptClass);

    // Create set members with required fields and save them
    Concept setMemberOne = new Concept();
    ConceptName setMemberOneName = new ConceptName("Set Member One", locale);
    setMemberOne.addName(setMemberOneName);
    setMemberOne.setDatatype(datatype);
    setMemberOne.setConceptClass(conceptClass);
    conceptService.saveConcept(setMemberOne);

    Concept setMemberTwo = new Concept();
    ConceptName setMemberTwoName = new ConceptName("Set Member Two", locale);
    setMemberTwo.addName(setMemberTwoName);
    setMemberTwo.setDatatype(datatype);
    setMemberTwo.setConceptClass(conceptClass);
    conceptService.saveConcept(setMemberTwo);

    concept.addSetMember(setMemberOne);
    concept.addSetMember(setMemberTwo);

    assertFalse("Initially, concept should not be marked as a set", concept.isSet());

    // Save the parent concept after its members have been persisted
    conceptService.saveConcept(concept);

    assertTrue("After saving, concept with set members should be marked as a set", concept.isSet());
}