public List<RefundInvoiceCandidate> retrieveOrCreateMatchingCandidates(
			@NonNull final AssignableInvoiceCandidate assignableCandidate,
			@NonNull final RefundContract refundContract)
	{
		final RefundInvoiceCandidateQuery refundCandidateQuery = RefundInvoiceCandidateQuery
				.builder()
				.refundContract(refundContract)
				.invoicableFrom(assignableCandidate.getInvoiceableFrom())
				.build();

		final List<RefundInvoiceCandidate> existingCandidates = invoiceCandidateRepository.getRefundInvoiceCandidates(refundCandidateQuery);
		final ImmutableMap<RefundConfig, RefundInvoiceCandidate> refundConfig2existingCandidate = Maps.uniqueIndex(existingCandidates, RefundInvoiceCandidate::getRefundConfig);

		// the new refund candidate has no assigned quantity (besides, in the nearest future, the qty of 'invoiceCandidate')
		final List<RefundConfig> relevantRefundConfigs = refundContract.getRelevantRefundConfigs(assignableCandidate.getQuantity().getAsBigDecimal());

		final TreeSet<RefundInvoiceCandidate> result = new TreeSet<RefundInvoiceCandidate>(Comparator.comparing(c -> c.getRefundConfig().getMinQty()));

		final ImmutableList.Builder<RefundConfig> refundConfigsThatNeedCandidates = ImmutableList.builder();
		for (final RefundConfig relevantRefundConfig : relevantRefundConfigs)
		{
			final RefundInvoiceCandidate existingRelevantRefundCandidate = refundConfig2existingCandidate.get(relevantRefundConfig);
			if (existingRelevantRefundCandidate != null)
			{
				result.add(existingRelevantRefundCandidate);
			}
			else
			{
				refundConfigsThatNeedCandidates.add(relevantRefundConfig);
			}
		}

		final List<RefundInvoiceCandidate> newRefundCandidates = refundInvoiceCandidateFactory.createRefundInvoiceCandidates(
				assignableCandidate,
				refundContract,
				refundConfigsThatNeedCandidates.build());
		result.addAll(newRefundCandidates);

		return ImmutableList.copyOf(result);
	}