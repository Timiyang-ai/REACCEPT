	@Test
	@SuppressWarnings("unused")
	public void test_retrievePPOrdersWithMissingICs()
	{
		final I_PP_Order ppOrder1 = createPP_Order(X_PP_Order.DOCSTATUS_Completed, null, false);
		final I_PP_Order ppOrder2 = createPP_Order(X_PP_Order.DOCSTATUS_Completed, MaterialTrackingPPOrderBL.C_DocType_DOCSUBTYPE_QualityInspection, false);
		final I_PP_Order ppOrder3 = createPP_Order(X_PP_Order.DOCSTATUS_Closed, null, false);
		final I_PP_Order ppOrder4 = createPP_Order(X_PP_Order.DOCSTATUS_Closed, MaterialTrackingPPOrderBL.C_DocType_DOCSUBTYPE_QualityInspection, false);
		final I_PP_Order ppOrder5 = createPP_Order(X_PP_Order.DOCSTATUS_Closed, null, true);
		final I_PP_Order ppOrder6 = createPP_Order(X_PP_Order.DOCSTATUS_Closed, MaterialTrackingPPOrderBL.C_DocType_DOCSUBTYPE_QualityInspection, true);

		final List<I_PP_Order> resultExpected = Arrays.asList(ppOrder3, ppOrder4);
		test_retrievePPOrdersWithMissingICs(resultExpected);
	}