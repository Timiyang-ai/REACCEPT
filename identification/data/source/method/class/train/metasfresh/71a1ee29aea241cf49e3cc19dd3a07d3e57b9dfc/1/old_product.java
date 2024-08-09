public Candidate addOrReplace(@NonNull final Candidate candidate)
	{
		final Optional<I_MD_Candidate> oldCandidateRecord = retrieveExact(candidate);

		final BigDecimal oldqty = oldCandidateRecord.isPresent() ? oldCandidateRecord.get().getQty() : BigDecimal.ZERO;
		final BigDecimal qtyDelta = candidate.getQuantity().subtract(oldqty);

		final I_MD_Candidate synchedRecord = syncToRecord(oldCandidateRecord, candidate);
		InterfaceWrapperHelper.save(synchedRecord);

		if (candidate.getType() != Type.STOCK && synchedRecord.getMD_Candidate_GroupId() <= 0)
		{
			synchedRecord.setMD_Candidate_GroupId(synchedRecord.getMD_Candidate_ID());
			InterfaceWrapperHelper.save(synchedRecord);
		}

		return candidate
				.withId(synchedRecord.getMD_Candidate_ID())
				.withGroupId(synchedRecord.getMD_Candidate_GroupId())
				.withQuantity(qtyDelta);
	}