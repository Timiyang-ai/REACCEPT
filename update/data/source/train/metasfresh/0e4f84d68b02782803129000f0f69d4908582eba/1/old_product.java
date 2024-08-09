public void add(@NonNull final Candidate candidate)
	{
		final I_MD_Candidate candidadteRecord = retrieveExact(candidate);
		syncToRecord(candidadteRecord, candidate);
		InterfaceWrapperHelper.save(candidadteRecord);
	}