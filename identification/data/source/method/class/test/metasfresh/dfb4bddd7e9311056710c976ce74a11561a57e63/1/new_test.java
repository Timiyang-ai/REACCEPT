@Test
	public void test_setInvoice()
	{
		final String esrImportLineText = "00201059931000000010501536417000120686900000040000012  190013011813011813012100015000400000000000000";

		final I_AD_Org org = getAD_Org();
		// org.setValue("105");
		// save(org);

		final I_ESR_Import esrImport = createImport();

		final I_C_BP_BankAccount account = newInstance(I_C_BP_BankAccount.class);

		account.setIsEsrAccount(true);
		account.setAD_Org_ID(Env.getAD_Org_ID(getCtx()));
		account.setAD_User_ID(Env.getAD_User_ID(getCtx()));
		account.setESR_RenderedAccountNo("01-059931-0");

		save(account);

		esrImport.setC_BP_BankAccount(account);

		final I_C_ReferenceNo_Type refNoType = newInstance(I_C_ReferenceNo_Type.class);
		refNoType.setName("InvoiceReference");
		save(refNoType);

		final I_C_BPartner partner = newInstance(I_C_BPartner.class);
		partner.setValue("partner1");
		partner.setAD_Org_ID(org.getAD_Org_ID());
		save(partner);

		esrImport.setAD_Org(org);

		save(esrImport);

		final I_C_DocType type = newInstance(I_C_DocType.class);
		type.setDocBaseType(X_C_DocType.DOCBASETYPE_ARInvoice);
		save(type);

		final CurrencyId currencyEUR = PlainCurrencyDAO.createCurrencyId(CurrencyCode.EUR);

		final I_C_Invoice invoice = newInstance(I_C_Invoice.class);
		invoice.setC_BPartner_ID(partner.getC_BPartner_ID());
		invoice.setAD_Org_ID(org.getAD_Org_ID());
		invoice.setDocumentNo("000120686");
		invoice.setAD_Org_ID(org.getAD_Org_ID());
		invoice.setGrandTotal(HUNDRET);
		invoice.setC_DocType_ID(type.getC_DocType_ID());
		invoice.setC_Currency_ID(currencyEUR.getRepoId());
		save(invoice);

		final I_C_ReferenceNo referenceNo = newInstance(I_C_ReferenceNo.class);
		referenceNo.setReferenceNo("0000000105015364170001206869"); // note that we include the check-digit 9 in here
		referenceNo.setC_ReferenceNo_Type(refNoType);
		referenceNo.setIsManual(true);
		referenceNo.setAD_Org_ID(org.getAD_Org_ID());
		save(referenceNo);

		final I_C_ReferenceNo_Doc esrReferenceNumberDocument = newInstance(I_C_ReferenceNo_Doc.class);
		esrReferenceNumberDocument.setAD_Table_ID(Services.get(IADTableDAO.class).retrieveTableId(I_C_Invoice.Table_Name));
		esrReferenceNumberDocument.setRecord_ID(invoice.getC_Invoice_ID());
		esrReferenceNumberDocument.setC_ReferenceNo(referenceNo);
		save(esrReferenceNumberDocument);

		// invoke the code under test
		esrImportBL.loadAndEvaluateESRImportStream(esrImport, new ByteArrayInputStream(esrImportLineText.getBytes()));
		refresh(esrImport, true);

		final I_ESR_ImportLine esrImportLine = ESRTestUtil.retrieveSingleLine(esrImport);

		assertThat(esrImportLine.getAmount(), comparesEqualTo(FOURTY)); // guard
		// guards
		assertThat(esrImportLine.getC_Invoice_ID()).as("Invoice not set correctly").isEqualTo(invoice.getC_Invoice_ID());
		assertThat(esrImportLine.getESR_Invoice_Grandtotal()).as("Incorrect grandtotal").isEqualByComparingTo(HUNDRET);
		assertThat(invoice.getGrandTotal()).as("Incorrect grandtotal").isEqualByComparingTo(HUNDRET);

		// guard: invoice has grandtotal=100; 10 already written off => 90 open; payment of 40 already allocated as of task 06677 => 50 open
		// TODO: write unit tests to further dig into the "matching" and "updateOpenAmount" topics
		assertThat(esrImportLine.getESR_Invoice_Openamt()).as("Incorrect invoice open amount").isEqualByComparingTo(SIXTY);

		final BigDecimal invoice2GrandTotal = new BigDecimal("123.56");

		final I_C_Invoice invoice2 = newInstance(I_C_Invoice.class);
		invoice2.setGrandTotal(invoice2GrandTotal);
		invoice2.setC_BPartner_ID(partner.getC_BPartner_ID());
		invoice2.setDocumentNo("000120688");
		invoice2.setAD_Org_ID(org.getAD_Org_ID());
		invoice2.setC_DocType_ID(type.getC_DocType_ID());
		save(invoice2);

		// create allocation over 100 (plus 20 writeoff)
		// note that PlainInvoiceDAO.retrieveAllocatedAmt() currently only checks for allocation lines, ignoring any hdr info.
		final I_C_AllocationLine allocAmt2 = newInstance(I_C_AllocationLine.class);
		allocAmt2.setWriteOffAmt(TWENTY);
		allocAmt2.setAmount(HUNDRET);
		allocAmt2.setC_Invoice_ID(invoice2.getC_Invoice_ID());
		save(allocAmt2);

		esrImportBL.setInvoice(esrImportLine, invoice2);

		assertThat(esrImportLine.getC_Invoice_ID()).as("Invoice not set correctly").isEqualTo(invoice2.getC_Invoice_ID());
		assertThat(invoice2GrandTotal).as("Incorrect grandtotal").isEqualByComparingTo(esrImportLine.getESR_Invoice_Grandtotal());

		// ts: note that we always subtract the line's (or lines' !) amount from the invoice's open amount
		assertThat(esrImportLine.getESR_Invoice_Openamt()).isEqualByComparingTo(new BigDecimal("3.56").subtract(esrImportLine.getAmount())); // this should be correct when we have a non-credit-memo
	}