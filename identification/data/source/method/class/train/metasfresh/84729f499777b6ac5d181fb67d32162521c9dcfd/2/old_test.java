	@Test
	public void writeOffDunningCandidate_NotAnInvoice()
	{
		final I_C_Dunning_Candidate candidate = db.newInstance(I_C_Dunning_Candidate.class);
		candidate.setAD_Table_ID(MTable.getTable_ID(I_C_Order.Table_Name));
		InterfaceWrapperHelper.save(candidate);

		final boolean executed = invoiceSourceBL.writeOffDunningCandidate(candidate, null);
		Assert.assertFalse("Only invoices are supported for write-off", executed);
	}