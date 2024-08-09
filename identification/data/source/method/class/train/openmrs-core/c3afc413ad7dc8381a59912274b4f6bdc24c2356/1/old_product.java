@Transactional(readOnly = true)
	@Authorized( { PrivilegeConstants.VIEW_ENCOUNTERS })
	List<Encounter> getEncountersByVisit(Visit visit);