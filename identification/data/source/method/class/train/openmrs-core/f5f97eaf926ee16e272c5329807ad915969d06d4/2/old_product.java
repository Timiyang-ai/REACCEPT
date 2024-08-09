@Authorized( { PrivilegeConstants.VIEW_PROGRAMS })
	@Transactional(readOnly = true)
	public List<Concept> getPossibleOutcomes(Integer programId);