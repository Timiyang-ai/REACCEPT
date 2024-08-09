	@Test
	public void retrieveOrCreateMatchingCandidate_retrieve()
	{
		final AssignableInvoiceCandidate assignableCandidate = refundTestTools.createAssignableCandidateWithAssignment();

		final RefundInvoiceCandidate refundInvoiceCandidate = assignableCandidate
				.getAssignmentsToRefundCandidates()
				.get(0)
				.getRefundInvoiceCandidate();

		// invoke the method under test
		final List<RefundInvoiceCandidate> result = refundInvoiceCandidateService.retrieveOrCreateMatchingRefundCandidates(
				assignableCandidate,
				refundInvoiceCandidate.getRefundContract());

		assertThat(result).hasSize(1);
		final RefundInvoiceCandidate resultElement = result.get(0);

		assertThat(resultElement).isEqualTo(refundInvoiceCandidate);
	}