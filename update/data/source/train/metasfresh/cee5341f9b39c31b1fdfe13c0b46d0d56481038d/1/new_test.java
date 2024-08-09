@Test
	public void computeAssignableQuantity()
	{
		final RefundConfigBuilder configBuilder = refundTestTools
				.createAndInitConfigBuilder()
				.refundMode(RefundMode.PER_INDIVIDUAL_SCALE);
		final RefundConfig refundConfig1 = configBuilder.minQty(ZERO).build();
		final RefundConfig refundConfig2 = configBuilder.minQty(THIRTY).build();

		final RefundContract refundContract = RefundContract.builder()
				.startDate(NOW)
				.endDate(NOW.plusDays(5))
				.bPartnerId(BPARTNER_ID)
				.refundConfig(refundConfig1)
				.refundConfig(refundConfig2)
				.build();

		final RefundInvoiceCandidate refundInvoiceCandidate = RefundInvoiceCandidate
				.builder()
				.assignedQuantity(Quantity.of(TWENTY, refundTestTools.getUomRecord()))
				.refundConfigs(ImmutableList.of(refundConfig1))
				.bpartnerId(BPartnerId.ofRepoId(10))
				.invoiceableFrom(NOW)
				.money(Money.of(ONE, refundTestTools.getCurrency().getId()))
				.refundContract(refundContract)
				.build();

		// invoke the method under test
		final Quantity result = refundInvoiceCandidate.computeAssignableQuantity(refundConfig1);

		assertThat(result.getAsBigDecimal()).isEqualByComparingTo("9");
	}