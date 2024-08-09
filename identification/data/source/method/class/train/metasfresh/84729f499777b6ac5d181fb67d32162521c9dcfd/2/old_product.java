protected boolean writeOffDunningCandidate(final I_C_Dunning_Candidate candidate, final String writeOffDescription)
	{
		final Properties ctx = InterfaceWrapperHelper.getCtx(candidate);
		final String trxName = InterfaceWrapperHelper.getTrxName(candidate);

		// services
		final IADTableDAO adTableDAO = Services.get(IADTableDAO.class);
		final IInvoiceBL invoiceBL = Services.get(IInvoiceBL.class);
		final IDunningEventDispatcher dunningEventDispatcher = Services.get(IDunningEventDispatcher.class);
		
		if (candidate.getAD_Table_ID() == adTableDAO.retrieveTableId(I_C_Invoice.Table_Name))
		{
			final I_C_Invoice invoice = InterfaceWrapperHelper.create(ctx, candidate.getRecord_ID(), I_C_Invoice.class, trxName);
			Check.assumeNotNull(invoice, "No invoice found for candidate {0}", candidate);
			
			invoiceBL.writeOffInvoice(invoice, candidate.getOpenAmt(), writeOffDescription);

			dunningEventDispatcher.fireDunningCandidateEvent(IInvoiceSourceBL.EVENT_AfterInvoiceWriteOff, candidate);

			return true;
		}

		// other document types are ignored for now

		return false;
	}