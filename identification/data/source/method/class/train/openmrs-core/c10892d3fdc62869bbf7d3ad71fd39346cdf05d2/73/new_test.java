	@Test
	public void getRelationshipsByPerson_shouldOnlyGetUnvoidedRelationships() {
		executeDataSet(CREATE_PATIENT_XML);
		executeDataSet(CREATE_RELATIONSHIP_XML);
		
		Patient p1 = ps.getPatient(6);
		Patient p2 = ps.getPatient(8);
		
		// Create a sibling relationship between o1 and p2
		Relationship sibling = new Relationship();
		sibling.setPersonA(p1);
		sibling.setPersonB(p2);
		sibling.setRelationshipType(personService.getRelationshipType(4));
		personService.saveRelationship(sibling);
		
		// Make p2 the Doctor of p1.
		Relationship doctor = new Relationship();
		doctor.setPersonB(p1);
		doctor.setPersonA(p2);
		doctor.setRelationshipType(personService.getRelationshipType(3));
		personService.saveRelationship(doctor);
		
		// Void all relationships.
		List<Relationship> allRels = personService.getAllRelationships();
		for (Relationship r : allRels) {
			personService.voidRelationship(r, "Because of a JUnit test.");
		}
		
		List<Relationship> updatedARels = personService.getRelationshipsByPerson(p1);
		List<Relationship> updatedBRels = personService.getRelationshipsByPerson(p2);
		
		// Neither p1 or p2 should have any relationships now.
		assertEquals(0, updatedARels.size());
		assertEquals(updatedARels, updatedBRels);
	}