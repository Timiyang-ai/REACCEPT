	@Test
	public void getActiveIdentifiers_shouldReturnPreferredIdentifiersFirstInTheList() {
		Patient p = new Patient();
		p.setIdentifiers(null);
		PatientIdentifier pa1 = new PatientIdentifier();
		PatientIdentifier pa2 = new PatientIdentifier();
		PatientIdentifier pa3 = new PatientIdentifier();
		
		pa1.setIdentifier("first");
		pa1.setIdentifierType(new PatientIdentifierType(1));
		pa1.setDateCreated(new Date());
		pa1.setVoided(false);
		pa1.setPreferred(true);
		
		pa2.setIdentifier("second");
		pa2.setIdentifierType(new PatientIdentifierType(1));
		pa2.setDateCreated(new Date());
		pa2.setVoided(false);
		pa2.setPreferred(false);
		
		pa3.setIdentifier("third");
		pa3.setIdentifierType(new PatientIdentifierType(1));
		pa3.setDateCreated(new Date());
		pa3.setVoided(false);
		pa3.setPreferred(false);
		
		p.addIdentifier(pa1);
		p.addIdentifier(pa2);
		p.addIdentifier(pa3);
		
		pa1.setPreferred(false);
		pa2.setPreferred(true);
		pa3.setVoided(true);
		
		assertTrue("With the third identifier voided, there should only be 2 identifiers",
		    p.getActiveIdentifiers().size() == 2);
		assertTrue("Preferred identifier should be first in the list", p.getActiveIdentifiers().get(0) == pa2);
		assertTrue("Non-preferred identifier should be last in the list", p.getActiveIdentifiers().get(1) == pa1);
	}