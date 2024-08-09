public RefundInvoiceCandidate retrieveOrCreateMatchingCandidate(
			@NonNull final AssignableInvoiceCandidate assignableInvoiceCandidate,
			@NonNull final RefundContract refundContract)
	{
		final RefundInvoiceCandidateQuery refundCandidateQuery = RefundInvoiceCandidateQuery
				.builder()
				.refundContract(refundContract)
				.invoicableFrom(assignableInvoiceCandidate.getInvoiceableFrom())
				.build();

		final List<RefundInvoiceCandidate> matchingCandidates = invoiceCandidateRepository.getRefundInvoiceCandidates(refundCandidateQuery);

		assignableInvoiceCandidate.getQuantity();

		final RefundInvoiceCandidate matchingCandidate = findMatchingCandidate(matchingCandidates, assignableInvoiceCandidate.getQuantity());
		if (matchingCandidate != null)
		{
			return matchingCandidate;
		}

		return invoiceCandidateRepository.createRefundInvoiceCandidate(assignableInvoiceCandidate, refundContract);
	}