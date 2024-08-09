@VisibleForTesting
	List<Candidate> createOneOrTwoCandidatesWithChangedTransactionDetailAndQuantity(
			@NonNull final Candidate candidate,
			@NonNull final TransactionDetail changedTransactionDetail)
	{
		final boolean attributesKeysMatch = Objects.equals(
				candidate.getMaterialDescriptor().getStorageAttributesKey(),
				changedTransactionDetail.getStorageAttributesKey());

		final boolean datesMatch = Objects.equals(
				candidate.getMaterialDescriptor().getDate(),
				changedTransactionDetail.getTransactionDate());

		if (attributesKeysMatch && datesMatch)
		{
			final TreeSet<TransactionDetail> newTransactionDetailsSet = extractAllTransactionDetails(candidate, changedTransactionDetail);

			final Instant firstTransactionDate = extractMinTransactionDate(newTransactionDetailsSet);

			final Candidate withTransactionDetails = candidate
					.withTransactionDetails(ImmutableList.copyOf(newTransactionDetailsSet))
					.withDate(firstTransactionDate);
			final BigDecimal actualQty = withTransactionDetails.computeActualQty();
			final BigDecimal detailQty = candidate.getBusinessCaseDetailQty();

			return ImmutableList.of(withTransactionDetails.withQuantity(actualQty.max(detailQty)));
		}
		else
		{
			// create a copy of candidate, just with
			// * the transaction's ASI/storage-key
			// * quantity == transactionQty
			// * the transactionDetail added to it
			final MaterialDescriptor newMaterialDescriptor = candidate
					.getMaterialDescriptor()
					.withStorageAttributes(
							changedTransactionDetail.getStorageAttributesKey(),
							changedTransactionDetail.getAttributeSetInstanceId())
					.withQuantity(changedTransactionDetail.getQuantity())
					.withDate(changedTransactionDetail.getTransactionDate());

			final Candidate newCandidate = candidate
					.toBuilder()
					.id(null)
					.parentId(null) // important to make sure a supply new candidate gets a stock record
					.type(computeCounterCandiateType(candidate))
					.materialDescriptor(newMaterialDescriptor)
					.clearTransactionDetails()
					.transactionDetail(changedTransactionDetail)
					.build();

			// subtract the transaction's Qty from the candidate;
			// because we don't expect that quantity anymore. It just came, but with different attributes.
			final BigDecimal actualQty = candidate.computeActualQty();
			final BigDecimal plannedQty = candidate.getQuantity().subtract(changedTransactionDetail.getQuantity());
			final BigDecimal updatedQty = actualQty.max(plannedQty);
			final Candidate updatedCandidate = candidate.withQuantity(updatedQty);

			// return the subtracted-qty-candidate and the copy
			return ImmutableList.of(newCandidate, updatedCandidate);
		}
	}