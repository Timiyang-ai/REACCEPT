@Test(expected = IllegalArgumentException.class)
	public void test_getDocAction_NonDocument()
	{
		final Properties ctx = Env.getCtx();
		final INonDocumentWithDocumentNo record = InterfaceWrapperHelper.create(ctx, INonDocumentWithDocumentNo.class, ITrx.TRXNAME_None);
		record.setDocumentNo("SomeDocumentNo");
		InterfaceWrapperHelper.save(record);

		docActionBL.getDocument(record);
	}