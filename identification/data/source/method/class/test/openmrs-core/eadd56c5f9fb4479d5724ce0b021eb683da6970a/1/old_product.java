@Transactional(readOnly = true)
	@Authorized(PrivilegeConstants.VIEW_VISITS)
	public List<Visit> getVisits(Collection<VisitType> visitTypes, Collection<Patient> patients,
	        Collection<Location> locations, Collection<Concept> indications, Date minStartDatetime, Date maxStartDatetime,
	        Date minEndDatetime, Date maxEndDatetime, boolean includeVoided) throws APIException;