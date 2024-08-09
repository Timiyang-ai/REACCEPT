public Candidate addOrReplace(@NonNull final Candidate candidate)
	{
		final Optional<I_MD_Candidate> oldCandidateRecord = retrieveExact(candidate);

		final I_MD_Candidate synchedRecord = syncToRecord(oldCandidateRecord, candidate);
		InterfaceWrapperHelper.save(synchedRecord);

		return candidate.withId(synchedRecord.getMD_Candidate_ID());
	}