public Optional<Candidate> retrieveStockAt(@NonNull final CandidatesSegment segment)
	{
		final IQueryBL queryBL = Services.get(IQueryBL.class);

		final IQueryBuilder<I_MD_Candidate> queryBuilder = queryBL.createQueryBuilder(I_MD_Candidate.class)
				.addOnlyActiveRecordsFilter()
				.addEqualsFilter(I_MD_Candidate.COLUMN_MD_Candidate_Type, X_MD_Candidate.MD_CANDIDATE_TYPE_STOCK)
				.addEqualsFilter(I_MD_Candidate.COLUMN_M_Warehouse_ID, segment.getWarehouse())
				.addEqualsFilter(I_MD_Candidate.COLUMN_M_Product_ID, segment.getProduct().getM_Product_ID())
				.addCompareFilter(I_MD_Candidate.COLUMN_DateProjected, Operator.LESS_OR_EQUAL, segment.getProjectedDate());

		if (segment.getLocator() != null)
		{
			queryBuilder.addInArrayFilter(I_MD_Candidate.COLUMN_M_Locator_ID, null, segment.getLocator().getM_Locator_ID());
		}

		final I_MD_Candidate candidateRecord = queryBuilder
				.orderBy().addColumn(I_MD_Candidate.COLUMNNAME_DateProjected, false).endOrderBy()
				.create()
				.first();

		return fromCandidateRecord(candidateRecord);
	}