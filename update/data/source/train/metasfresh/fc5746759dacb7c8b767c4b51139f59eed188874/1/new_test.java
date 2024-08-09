@Test
	public void unassignCandidate_perScaleConfig2()
	{
		final ImmutableMap<BigDecimal, AssignableInvoiceCandidate> preparedAssignableCandidates = commonSetupForUnassignWithPerScaleConfig();

		// invoke the method under test
		final UnassignResult result = invoiceCandidateAssignmentService.unassignCandidate(preparedAssignableCandidates.get(TEN));

		assertThat(result.getAssignableCandidate().getAssignmentsToRefundCandidates()).isEmpty();
		assertThat(result.getUnassignedPairs()).hasSize(1);

		// the candidate with seven was also affected
		assertThat(result.getAdditionalChangedCandidates()).hasSize(1);
		assertThat(result.getAdditionalChangedCandidates().get(0).getId()).isEqualTo(preparedAssignableCandidates.get(SEVEN).getId());

		final AssignableInvoiceCandidate reloadedAssignableCandidateWithTen = assignableInvoiceCandidateRepository.getById(preparedAssignableCandidates.get(TEN).getId());
		assertThat(reloadedAssignableCandidateWithTen.getAssignmentsToRefundCandidates()).isEmpty();

		final AssignableInvoiceCandidate reloadedAssignableCandidateWithSeven = assignableInvoiceCandidateRepository
				.getById(preparedAssignableCandidates.get(SEVEN).getId())
				.toBuilder()
				.assignmentsToRefundCandidates(
						assignmentToRefundCandidateRepository.getAssignmentsToRefundCandidate(preparedAssignableCandidates.get(SEVEN).getId()))
				.build();

		final I_C_UOM uom = refundTestTools.getUomRecord();
		final CurrencyId currentId = refundTestTools.getCurrency().getId();

		final AssignmentExpectation expectation = AssignmentExpectation
				.builder()
				.quantityAssignedToRefundCandidate(Quantity.of(SEVEN, uom)) // the assignable candidate with 10 is still completely assigned
				.quantityOfRefundCandidate(Quantity.of(SEVEN, uom)) // used to have 14 and 4 of those belonged to the assignable candidate with 7
				.moneyAssignedToRefundCandidate(Money.of(TWO, currentId))
				.moneyOfRefundCandidate(Money.of(HUNDRED.add(TWO), currentId))
				.build();

		assertMoneyAndQuantityAssignments(reloadedAssignableCandidateWithSeven, expectation);
	}