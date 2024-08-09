public UnassignResult unassignCandidate(@NonNull final AssignableInvoiceCandidate assignableInvoiceCandidate)
	{
		final UnassignResult result = unassignSingleCandidate(assignableInvoiceCandidate);

		final List<UnassignedPairOfCandidates> unassignedPairs = result.getUnassignedPairs();

		final RefundMode refundMode = CollectionUtils.extractSingleElement(
				unassignedPairs,
				pair -> pair.getRefundInvoiceCandidate().getRefundConfig().getRefundMode());

		if (RefundMode.ALL_MAX_SCALE.equals(refundMode))
		{
			Check.errorIf(unassignedPairs.size() > 1, "If refundMode={}, then there can be only one refund candidate; unassignResult={}", refundMode, result);
			final UnassignedPairOfCandidates unassignedPair = CollectionUtils.singleElement(unassignedPairs);

			final RefundInvoiceCandidate refundCandidate = unassignedPair.getRefundInvoiceCandidate();
			final RefundConfig currentRefundConfig = refundCandidate.getRefundConfig();
			final RefundConfig targetRefundConfig = refundCandidate
					.getRefundContract()
					.getRefundConfig(refundCandidate.getAssignedQuantity().getAsBigDecimal());

			// if accumulated: check if the current quantity still matches the respective candidate's current refund-config's minQty;
			if (!currentRefundConfig.getId().equals(targetRefundConfig.getId()))
			{
				// if not, then update the candidates' refund-config and money; don't forget I_C_Invoice_Candidate_Assignment.C_Flatrate_RefundConfig_ID
				resetMoneyAmount(refundCandidate);
			}
			return result;
		}

		// refundMode == PER_SCALE
		final RefundContract refundContract = CollectionUtils.extractSingleElement(
				unassignedPairs,
				pair -> pair.getRefundInvoiceCandidate().getRefundContract());

		final List<RefundInvoiceCandidate> matchingRefundCandidates = refundInvoiceCandidateService.retrieveMatchingRefundCandidates(
				assignableInvoiceCandidate, refundContract);

		if (matchingRefundCandidates.size() > 1)
		{
			final UnassignResultBuilder resultBuilder = result.toBuilder();

//			final Comparator<UnassignedPairOfCandidates> comparingByMinQty = Comparator
//					.comparing(pair -> pair.getRefundInvoiceCandidate().getRefundConfig().getMinQty());
//			final ImmutableList<UnassignedPairOfCandidates> sortedByMinQtyDesc = matchingRefundCandidates
//					.stream()
//					.sorted(comparingByMinQty.reversed())
//					.collect(ImmutableList.toImmutableList());

			final Comparator<RefundInvoiceCandidate> comparingByMinQty = Comparator
					.comparing(r -> r.getRefundConfig().getMinQty());
			final ImmutableList<RefundInvoiceCandidate> sortedByMinQtyDesc = matchingRefundCandidates
					.stream()
					.sorted(comparingByMinQty.reversed())
					.collect(ImmutableList.toImmutableList());

			final RefundInvoiceCandidate highestRefundInvoiceCandidate = sortedByMinQtyDesc
					.get(sortedByMinQtyDesc.size() - 1);

			Quantity gap = Quantity.zero(assignableInvoiceCandidate.getQuantity().getUOM());

			boolean higherCandidateHasAssignedQty = highestRefundInvoiceCandidate.getAssignedQuantity().signum() > 0;

			for (int i = sortedByMinQtyDesc.size() - 2; i > 1; i--)
			{
				final RefundInvoiceCandidate refundInvoiceCandidate = sortedByMinQtyDesc.get(i);

				final Quantity assignableQty = refundInvoiceCandidate.computeAssignableQuantity();
				if (assignableQty.isInfinite() || assignableQty.signum() <= 0)
				{
					continue;
				}

				if (higherCandidateHasAssignedQty)
				{
					gap = gap.add(assignableQty);
				}

				higherCandidateHasAssignedQty = higherCandidateHasAssignedQty || refundInvoiceCandidate.getAssignedQuantity().signum() > 0;
			}
			// TODO: if per-scale: check if
			// * the "lowest" candidate now has an assignable quantity (i.e. a "gap");
			// * and there are "higher" candidates with an assigned quantity
			// if yes then
			// then get the quantity of that "lowest" candidate's gap
			// take a look at the "highest" candidate with an assigned quantity

			if (gap.signum() > 0)
			{
				final List<AssignableInvoiceCandidate> assignableCandidatesToReassign = getAssignableCandidates(refundContract, gap);
				for (final AssignableInvoiceCandidate assignableCandidateToReassign : assignableCandidatesToReassign)
				{
					final UpdateAssignmentResult updateAssignmentResult = updateAssignment(assignableCandidateToReassign);
					if (updateAssignmentResult.isUpdateWasDone())
					{
						resultBuilder.additionalChangedCandidate(updateAssignmentResult.getAssignableInvoiceCandidate());
					}
				}
			}
			return resultBuilder.build();
		}

		return result;
	}