	@Test
	public void test_createResponse()
	{
		// Setup & create PrintJob
		final I_C_Print_Job printJob = helper.createPrintJob();
		helper.createPrintJobLine(printJob, printerRouting, "01");
		helper.createPrintJobInstructions(printJob);

		final I_C_Print_Package printPackageRequest = helper.createPrintPackageRequest();
		final I_C_Print_Package printPackageResponse = createPrintPackageRequestHandler.createResponse(printPackageRequest);
		Assert.assertNotNull("Print Package response shall be created", printPackageResponse);
	}