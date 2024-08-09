@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.TEXT_HTML)
	public Response doPostImport(@FormDataParam("importDir") String uploadDir,
			@FormDataParam("fileUpload") InputStream fileIS,
			@FormDataParam("fileUpload") FormDataContentDisposition fileInfo) {
		try {
			validateAccess();
			final org.pentaho.platform.plugin.services.importexport.ImportProcessor importProcessor = new org.pentaho.platform.plugin.services.importexport.SimpleImportProcessor(
					uploadDir, null);
			// TODO - create a SolutionRepositoryImportHandler which delegates
			// to these three automatically
			importProcessor
					.addImportHandler(new org.pentaho.platform.plugin.services.importexport.MondrianImportHandler(
							repository));
			importProcessor
					.addImportHandler(new org.pentaho.platform.plugin.services.importexport.MetadataImportHandler(
							repository));
			importProcessor
					.addImportHandler(new org.pentaho.platform.plugin.services.importexport.DefaultImportHandler(
							repository));
			if (fileInfo.getFileName().toLowerCase().endsWith(".zip")) {
				importProcessor
						.setImportSource(new org.pentaho.platform.plugin.services.importexport.legacy.ZipSolutionRepositoryImportSource(
								new ZipInputStream(fileIS), "UTF-8"));
			} else {
				final File outFile = File.createTempFile("import", null);
				convertInputStreamToFile(outFile, fileIS);
				outFile.deleteOnExit();
				importProcessor
						.setImportSource(new org.pentaho.platform.plugin.services.importexport.legacy.FileSolutionRepositoryImportSource(
								outFile, fileInfo.getFileName(), "UTF-8"));
			}
			importProcessor.performImport();

			// Flush the Mondrian cache to show imported datasources.
			IMondrianCatalogService mondrianCatalogService = PentahoSystem.get(
					IMondrianCatalogService.class, "IMondrianCatalogService",
					PentahoSessionHolder.getSession());
			mondrianCatalogService.reInit(PentahoSessionHolder.getSession());
		} catch (org.pentaho.platform.plugin.services.importexport.ImportException e) {
			return Response.serverError().entity(e.toString()).build();
		} catch (org.pentaho.platform.plugin.services.importexport.InitializationException e) {
			return Response.serverError().entity(e.toString()).build();
		} catch (IOException e) {
			return Response.serverError().entity(e.toString()).build();
		} catch (PentahoAccessControlException e) {
			return Response.serverError().entity(e.toString()).build();
		}

		return Response
				.ok(Messages.getInstance().getString(
						"FileResource.IMPORT_SUCCESS")).build();
	}