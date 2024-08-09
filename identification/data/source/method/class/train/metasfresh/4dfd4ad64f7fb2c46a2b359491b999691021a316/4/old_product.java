private List<Candidate> createOneOrTwoCandidatesWithChangedTransactionDetailAndQuantity(
			@NonNull final Candidate candidate,
			@NonNull final TransactionDetail changedTransactionDetail)
	{
		final boolean transactionMatchesCandidate = Objects
				.equals(
						candidate.getMaterialDescriptor().getStorageAttributesKey(),
						changedTransactionDetail.getStorageAttributesKey());

		if (transactionMatchesCandidate)
		{
			final ImmutableList<TransactionDetail> otherTransactionDetails = candidate.getTransactionDetails()
					.stream()
					.filter(transactionDetail -> transactionDetail.getTransactionId() != changedTransactionDetail.getTransactionId())
					.collect(ImmutableList.toImmutableList());

			// note: using TreeSet to make sure we don't end up with duplicated transactionDetails
			final TreeSet<TransactionDetail> newTransactionDetailsSet = new TreeSet<>(Comparator.comparing(TransactionDetail::getTransactionId));
			newTransactionDetailsSet.addAll(otherTransactionDetails);
			newTransactionDetailsSet.add(changedTransactionDetail);

			final Candidate withTransactionDetails = candidate.withTransactionDetails(ImmutableList.copyOf(newTransactionDetailsSet));
			final BigDecimal actualQty = withTransactionDetails.computeActualQty();
			final BigDecimal plannedQty = candidate.getPlannedQty();

			return ImmutableList.of(withTransactionDetails.withQuantity(actualQty.max(plannedQty)));
		}
		else
		{
			// create a copy of candidate, just with
			// * the transaction's ASI/storage-key
			// * plannedQty == actualQty == transactionQty
			// * the transactionDetail added to it
			final ProductDescriptor newProductDescriptor = ProductDescriptor
					.forProductAndAttributes(
							candidate.getMaterialDescriptor().getProductId(),
							changedTransactionDetail.getStorageAttributesKey(),
							changedTransactionDetail.getAttributeSetInstanceId());

			final MaterialDescriptor newMaterialDescriptor = candidate
					.getMaterialDescriptor()
					.withProductDescriptor(newProductDescriptor)
					.withQuantity(changedTransactionDetail.getQuantity());

			final Candidate newCandidate = candidate
					.toBuilder()
					.id(null)
					.materialDescriptor(newMaterialDescriptor)
					.clearTransactionDetails()
					.transactionDetail(changedTransactionDetail)
					.build();

			// subtract the transaction's Qty from the candidate
			final BigDecimal actualQty = candidate.computeActualQty();
			final BigDecimal plannedQty = candidate.getPlannedQty().subtract(changedTransactionDetail.getQuantity());
			final BigDecimal updatedQty = actualQty.max(plannedQty);
			final Candidate updatedCandidate = candidate.withQuantity(updatedQty);

			// return the subtracted-qty-candidate and the copy
			return ImmutableList.of(newCandidate, updatedCandidate);
		}
	}