public I_C_Print_Package createResponse(final I_C_Print_Package printPackage)
	{
		final Properties envCtxToUse = InterfaceWrapperHelper.getCtx(printPackage);

		// create/update information for Druck-Clients
		final IPrintClientsBL printClientsBL = Services.get(IPrintClientsBL.class);
		final IPrintPackageBL printPackageBL = Services.get(IPrintPackageBL.class);

		printClientsBL
				.createPrintClientsEntry(
						envCtxToUse,
						printPackageBL.getHostKeyOrNull(envCtxToUse));

		boolean packageUpdated = false;
		final I_C_Print_Package responsePrintPackage;
		try
		{
			packageUpdated = updatePrintPackage(printPackage);
		}
		finally
		{
			if (packageUpdated)
			{
				responsePrintPackage = printPackage;
			}
			else
			{
				logger.fine("There was no update on print package. Deleting it");
				InterfaceWrapperHelper.delete(printPackage);
				responsePrintPackage = null;
			}
		}

		return responsePrintPackage;
	}