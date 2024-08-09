@VisibleForTesting
	Candidate addOrUpdate0(@NonNull final Candidate candidate, final boolean preserveExistingSeqNo)
	{
		final Optional<I_MD_Candidate> oldCandidateRecord = retrieveExact(candidate);

		final BigDecimal oldqty = oldCandidateRecord.isPresent() ? oldCandidateRecord.get().getQty() : BigDecimal.ZERO;
		final BigDecimal qtyDelta = candidate.getQuantity().subtract(oldqty);

		final I_MD_Candidate synchedRecord = syncToRecord(oldCandidateRecord, candidate, preserveExistingSeqNo);
		InterfaceWrapperHelper.save(synchedRecord);

		if (synchedRecord.getSeqNo() <= 0)
		{
			synchedRecord.setSeqNo(synchedRecord.getMD_Candidate_ID());
			InterfaceWrapperHelper.save(synchedRecord);
		}

		if (synchedRecord.getMD_Candidate_GroupId() <= 0)
		{
			synchedRecord.setMD_Candidate_GroupId(synchedRecord.getMD_Candidate_ID());
			InterfaceWrapperHelper.save(synchedRecord);
		}

		if (candidate.getSubType() == SubType.PRODUCTION && candidate.getProductionDetail() != null)
		{
			addOrRecplaceProductionDetail(candidate, synchedRecord);
		}

		if (candidate.getDemandDetail() != null)
		{
			// we do this independently of the type; the demand info might be needed by many records, not just by the "first" demand record
			addOrRecplaceDemandDetail(candidate, synchedRecord);
		}

		final Integer parentId = synchedRecord.getMD_Candidate_Parent_ID() > 0 ? synchedRecord.getMD_Candidate_Parent_ID() : null;

		return candidate
				.withId(synchedRecord.getMD_Candidate_ID())
				.withParentId(parentId)
				.withGroupId(synchedRecord.getMD_Candidate_GroupId())
				.withSeqNo(synchedRecord.getSeqNo())
				.withQuantity(qtyDelta);
	}