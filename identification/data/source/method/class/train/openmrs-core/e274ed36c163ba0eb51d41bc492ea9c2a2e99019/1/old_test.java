@Test
	public void newInstance_shouldCopyEveryPropertyOfGivenPersonName() {
		Integer personNameId = 333;
		boolean preferred = true;
		String prefix = "prefix";
		Person person = new Person(1);
		String givenName = "given";
		String middleName = "middle";
		String familyNamePrefix = "familyNamePrefix";
		String familyName = "familyName";
		String familyName2 = "familyName2";
		String familyNameSuffix = "familyNameSuffix";
		String degree = "degree";
		boolean voided = true;
		User voidedBy = new User(1);
		String voidReason = "voidReason";
		
		PersonName pn = new PersonName(personNameId);
		pn.setPreferred(preferred);
		pn.setPrefix(prefix);
		pn.setPerson(person);
		pn.setGivenName(givenName);
		pn.setMiddleName(middleName);
		pn.setFamilyNamePrefix(familyNamePrefix);
		pn.setFamilyName(familyName);
		pn.setFamilyName2(familyName2);
		pn.setFamilyNameSuffix(familyNameSuffix);
		pn.setDegree(degree);
		pn.setVoided(voided);
		pn.setVoidedBy(voidedBy);
		pn.setVoidReason(voidReason);
		
		PersonName copy = PersonName.newInstance(pn);
		
		Assert.assertEquals(personNameId, copy.getPersonNameId());
		Assert.assertEquals(preferred, copy.getPreferred().booleanValue());
		Assert.assertEquals(prefix, copy.getPrefix());
		Assert.assertEquals(person, copy.getPerson());
		Assert.assertEquals(givenName, copy.getGivenName());
		Assert.assertEquals(middleName, copy.getMiddleName());
		Assert.assertEquals(familyNamePrefix, copy.getFamilyNamePrefix());
		Assert.assertEquals(familyName, copy.getFamilyName());
		Assert.assertEquals(familyName2, copy.getFamilyName2());
		Assert.assertEquals(familyNameSuffix, copy.getFamilyNameSuffix());
		Assert.assertEquals(degree, copy.getDegree());
		Assert.assertEquals(voided, copy.getVoided().booleanValue());
		Assert.assertEquals(voidedBy, copy.getVoidedBy());
		Assert.assertEquals(voidReason, copy.getVoidReason());
	}