	@Test
	public void unassignCandidate()
	{
		final AssignableInvoiceCandidate assignableInvoiceCandidate = refundTestTools.createAssignableCandidateWithAssignment();

		final AssignmentToRefundCandidate assignmentToRefundCandidate = singleElement(assignableInvoiceCandidate.getAssignmentsToRefundCandidates());
		final RefundInvoiceCandidate refundInvoiceCandidate = assignmentToRefundCandidate.getRefundInvoiceCandidate();

		// guards
		assertThat(assignmentToRefundCandidate.getQuantityAssigendToRefundCandidate().toBigDecimal()).isEqualByComparingTo(ONE);
		assertThat(refundInvoiceCandidate.getMoney().toBigDecimal()).isEqualByComparingTo(HUNDRED);
		assertThat(refundInvoiceCandidate.getAssignedQuantity().toBigDecimal()).isEqualByComparingTo(ONE);

		// invoke the method under test
		final UnassignResult result = invoiceCandidateAssignmentService.unassignCandidate(assignableInvoiceCandidate);

		final List<UnassignedPairOfCandidates> unAssignedPairsOfCandidates = result.getUnassignedPairs();

		assertThat(unAssignedPairsOfCandidates).hasSize(1);
		final UnassignedPairOfCandidates unAssignedPairOfCandidates = unAssignedPairsOfCandidates.get(0);

		final AssignableInvoiceCandidate unassignedAssignableInvoiceCandidate = unAssignedPairOfCandidates.getAssignableInvoiceCandidate();
		assertThat(unassignedAssignableInvoiceCandidate.isAssigned()).isFalse();

		final RefundInvoiceCandidate unAssignedRefundInvoiceCandidate = unAssignedPairOfCandidates.getRefundInvoiceCandidate();

		final InvoiceCandidateId invoiceCandidateId = unAssignedRefundInvoiceCandidate.getId();
		assertThat(invoiceCandidateId).isEqualTo(refundInvoiceCandidate.getId());
		assertThat(unAssignedRefundInvoiceCandidate.getMoney().toBigDecimal()).isEqualByComparingTo("98"); // we subtract 20% of 10 from 100; 20% is set in the refund config

		// make
		final I_C_Invoice_Candidate unAssignedRefundInvoiceCandidateRecord = RefundTestTools.retrieveRecord(invoiceCandidateId);
		assertThat(unAssignedRefundInvoiceCandidateRecord.getPriceActual()).isEqualByComparingTo("98");
	}