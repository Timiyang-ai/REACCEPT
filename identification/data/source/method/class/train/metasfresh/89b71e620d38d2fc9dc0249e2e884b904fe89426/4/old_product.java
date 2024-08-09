public AssignableInvoiceCandidate ofRecord(@Nullable final I_C_Invoice_Candidate assignableRecord)
	{
		final InvoiceCandidateId invoiceCandidateId = InvoiceCandidateId.ofRepoId(assignableRecord.getC_Invoice_Candidate_ID());

		final Timestamp invoicableFromDate = getValueOverrideOrValue(assignableRecord, I_C_Invoice_Candidate.COLUMNNAME_DateToInvoice);
		final BigDecimal moneyAmount = assignableRecord
				.getNetAmtInvoiced()
				.add(assignableRecord.getNetAmtToInvoice());

		final I_C_Currency currencyRecord = assignableRecord.getC_Currency();
		final CurrencyId currencyId = CurrencyId.ofRepoId(currencyRecord.getC_Currency_ID());
		final int precision = currencyRecord.getStdPrecision();

		final Money money = Money.of(moneyAmount, currencyId);

		final Quantity quantity = Quantity.of(
				assignableRecord.getQtyToInvoice().add(assignableRecord.getQtyInvoiced()),
				assignableRecord.getM_Product().getC_UOM());

		final AssignableInvoiceCandidate invoiceCandidate = AssignableInvoiceCandidate.builder()
				.id(invoiceCandidateId)
				.bpartnerId(BPartnerId.ofRepoId(assignableRecord.getBill_BPartner_ID()))
				.invoiceableFrom(TimeUtil.asLocalDate(invoicableFromDate))
				.money(money)
				.precision(precision)
				.quantity(quantity)
				.productId(ProductId.ofRepoId(assignableRecord.getM_Product_ID()))
				.build();

		return invoiceCandidate;
	}