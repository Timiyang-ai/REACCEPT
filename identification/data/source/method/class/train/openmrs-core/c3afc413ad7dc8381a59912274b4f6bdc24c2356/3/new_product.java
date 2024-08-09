@Authorized( { PrivilegeConstants.GET_ENCOUNTERS })
	List<Encounter> getEncountersByVisit(Visit visit, boolean includeVoided);