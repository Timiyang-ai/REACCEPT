public void add(final I_M_ReceiptSchedule_Alloc rsa)
	{
		//
		// Create HUReceiptLineCandidate Part
		final IHUContext huContext = getHUContext();
		final I_M_HU tuHU = rsa.getM_TU_HU();
		final HUReceiptLinePartAttributes partAttributes = HUReceiptLinePartAttributes.newInstance(huContext, tuHU);
		final HUReceiptLinePartCandidate receiptLinePartToAdd = new HUReceiptLinePartCandidate(partAttributes);
		receiptLinePartToAdd.add(rsa);

		//
		// Add it
		add(receiptLinePartToAdd);
	}