public Optional<Candidate> retrieveLatestMatch(@NonNull final CandidatesSegment segment)
	{
		final IQueryBuilder<I_MD_Candidate> builder = mkQueryBuilder(segment);

		final I_MD_Candidate candidateRecord = builder
				.orderBy().addColumn(I_MD_Candidate.COLUMNNAME_DateProjected, false).endOrderBy()
				.create()
				.first();

		return fromCandidateRecord(Optional.ofNullable(candidateRecord));
	}