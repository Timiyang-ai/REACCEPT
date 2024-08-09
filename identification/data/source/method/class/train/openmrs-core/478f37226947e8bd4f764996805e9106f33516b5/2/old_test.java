	@Test
	public void removeIdentifier_shouldRemoveIdentifierIfExists() {
		Patient p = new Patient();
		
		PatientIdentifier pa1 = new PatientIdentifier();
		
		pa1.setIdentifier("firsttest");
		pa1.setIdentifierType(new PatientIdentifierType(1));
		pa1.setDateCreated(new Date());
		pa1.setVoided(false);
		p.addIdentifier(pa1);
		
		// adding the same identifier should not increment the size
		p.addIdentifier(pa1);
		
		PatientIdentifier pa2 = new PatientIdentifier();
		pa2.setIdentifier("secondtest");
		pa2.setIdentifierType(new PatientIdentifierType(2));
		pa2.setVoided(false);
		
		p.addIdentifier(pa2);
		
		PatientIdentifier pa3 = new PatientIdentifier();
		pa3.setIdentifierType(pa1.getIdentifierType());
		pa3.setIdentifier(pa3.getIdentifier() + "some new string to make sure it gets added");
		pa3.setVoided(true);
		pa3.setDateCreated(new Date(pa1.getDateCreated().getTime() + 1));
		p.addIdentifier(pa3);
		
		// test removing all of the identifiers
		p.removeIdentifier(pa3);
		assertTrue("There should be only 2 identifiers in the patient object now", p.getIdentifiers().size() == 2);
		p.removeIdentifier(pa2);
		assertTrue("There should be only 1 identifier in the patient object now", p.getIdentifiers().size() == 1);
		p.removeIdentifier(pa2);
		assertTrue("There should still be only 1 identifier in the patient object now", p.getIdentifiers().size() == 1);
		p.removeIdentifier(pa1);
		assertTrue("There shouldn't be any identifiers in the patient object now", p.getIdentifiers().size() == 0);
	}