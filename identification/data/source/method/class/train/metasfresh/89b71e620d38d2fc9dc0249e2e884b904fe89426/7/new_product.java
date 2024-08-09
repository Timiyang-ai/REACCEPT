public AssignableInvoiceCandidate ofRecord(@Nullable final I_C_Invoice_Candidate assignableRecord)
	{
		final ICurrencyDAO currencyDAO = Services.get(ICurrencyDAO.class);

		final InvoiceCandidateId invoiceCandidateId = InvoiceCandidateId.ofRepoId(assignableRecord.getC_Invoice_Candidate_ID());

		final Timestamp invoicableFromDate = getValueOverrideOrValue(assignableRecord, I_C_Invoice_Candidate.COLUMNNAME_DateToInvoice);
		final BigDecimal moneyAmount = assignableRecord
				.getNetAmtInvoiced()
				.add(assignableRecord.getNetAmtToInvoice());

		final I_C_Currency currencyRecord = currencyDAO.getById(CurrencyId.ofRepoId(assignableRecord.getC_Currency_ID()));
		final CurrencyId currencyId = CurrencyId.ofRepoId(currencyRecord.getC_Currency_ID());
		final int precision = currencyRecord.getStdPrecision();
		final Money money = Money.of(stripTrailingDecimalZeros(moneyAmount), currencyId);

		final Quantity quantity = extractQuantity(assignableRecord);
		final Quantity quantityOld = extractQuantity(createOld(assignableRecord, I_C_Invoice_Candidate.class));

		final List<AssignmentToRefundCandidate> assignments = assignmentToRefundCandidateRepository.getAssignmentsByAssignableCandidateId(invoiceCandidateId);

		final AssignableInvoiceCandidate invoiceCandidate = AssignableInvoiceCandidate.builder()
				.id(invoiceCandidateId)
				.bpartnerLocationId(BPartnerLocationId.ofRepoId(assignableRecord.getBill_BPartner_ID(),assignableRecord.getBill_Location_ID()))
				.invoiceableFrom(TimeUtil.asLocalDate(invoicableFromDate))
				.money(money)
				.precision(precision)
				.quantity(quantity)
				.quantityOld(quantityOld)
				.productId(ProductId.ofRepoId(assignableRecord.getM_Product_ID()))
				.assignmentsToRefundCandidates(assignments)
				.build();

		return invoiceCandidate;
	}