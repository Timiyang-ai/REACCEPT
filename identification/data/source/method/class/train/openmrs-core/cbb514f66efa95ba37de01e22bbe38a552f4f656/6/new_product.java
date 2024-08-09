public static PersonName newInstance(PersonName pn) {
		if (pn == null)
			throw new IllegalArgumentException();
		PersonName newName = new PersonName(Integer.valueOf(pn.getPersonNameId()));
		if (pn.getGivenName() != null)
			newName.setGivenName(new String(pn.getGivenName()));
		if (pn.getMiddleName() != null)
			newName.setMiddleName(new String(pn.getMiddleName()));
		if (pn.getFamilyName() != null)
			newName.setFamilyName(new String(pn.getFamilyName()));
		if (pn.getFamilyName2() != null)
			newName.setFamilyName2(new String(pn.getFamilyName2()));
		if (pn.getFamilyNamePrefix() != null)
			newName.setFamilyNamePrefix(new String(pn.getFamilyNamePrefix()));
		if (pn.getFamilyNameSuffix() != null)
			newName.setFamilyNameSuffix(new String(pn.getFamilyNameSuffix()));
		if (pn.getPrefix() != null)
			newName.setPrefix(new String(pn.getPrefix()));
		if (pn.getDegree() != null)
			newName.setDegree(new String(pn.getDegree()));
		if (pn.getVoidReason() != null)
			newName.setVoidReason(new String(pn.getVoidReason()));
		
		if (pn.getDateChanged() != null)
			newName.setDateChanged((Date) pn.getDateChanged().clone());
		if (pn.getDateCreated() != null)
			newName.setDateCreated((Date) pn.getDateCreated().clone());
		if (pn.getDateVoided() != null)
			newName.setDateVoided((Date) pn.getDateVoided().clone());
		
		if (pn.getPreferred() != null)
			newName.setPreferred(pn.getPreferred().booleanValue());
		if (pn.getVoided() != null)
			newName.setVoided(pn.getVoided().booleanValue());
		
		newName.setPerson(pn.getPerson());
		newName.setVoidedBy(pn.getVoidedBy());
		newName.setChangedBy(pn.getChangedBy());
		newName.setCreator(pn.getCreator());
		
		return newName;
	}