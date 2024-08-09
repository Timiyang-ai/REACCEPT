@Override
	public void purgeVisit(Visit visit) throws APIException {
		if (visit.getVisitId() == null)
			return;
		//TODO there is a ticket for adding includeVoided argument to getEncountersByVisit for this not to fail
		if (Context.getEncounterService().getEncountersByVisit(visit).size() > 0)
			throw new APIException(Context.getMessageSourceService().getMessage("Visit.purge.inUse", null,
			    "Cannot purge a visit that has encounters associated to it", Context.getLocale()));
		dao.deleteVisit(visit);
	}