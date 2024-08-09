	@Test
	public void ofRecord_AssignableInvoiceCandidate()
	{
		// invoke the method under test
		final AssignableInvoiceCandidate ofRecord = assignableInvoiceCandidateFactory.ofRecord(assignableIcRecord);

		assertThat(ofRecord.getBpartnerLocationId().getBpartnerId().getRepoId()).isEqualTo(BPartnerRecordID);

		assertThat(ofRecord.getProductId().getRepoId()).isEqualTo(productRecord.getM_Product_ID());

		// TODO move to dedicated test case
		// assertThat(cast.getAssignmentsToRefundCandidates().get(0).getRefundInvoiceCandidate().getId().getRepoId()).isEqualTo(refundContractIcRecord.getC_Invoice_Candidate_ID());
		assertThat(ofRecord.getMoney().toBigDecimal()).isEqualByComparingTo(TEN);
		assertThat(ofRecord.getInvoiceableFrom()).isEqualTo(TimeUtil.asLocalDate(dateToInvoiceOfAssignableCand));
	}