@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.TEXT_HTML)
	public Response doPostImport(@FormDataParam("importDir") String uploadDir,
			@FormDataParam("fileUpload") InputStream fileIS,
			@FormDataParam("fileUpload") FormDataContentDisposition fileInfo) {
		Map<String, org.pentaho.platform.plugin.services.importexport.Converter> converters = new HashMap<String, org.pentaho.platform.plugin.services.importexport.Converter>();
		org.pentaho.platform.plugin.services.importexport.StreamConverter streamConverter = new org.pentaho.platform.plugin.services.importexport.StreamConverter();
		converters.put("prpt", streamConverter); //$NON-NLS-1$
		converters.put("mondrian.xml", streamConverter); //$NON-NLS-1$
		converters.put("kjb", streamConverter); //$NON-NLS-1$
		converters.put("ktr", streamConverter); //$NON-NLS-1$
		converters.put("report", streamConverter); //$NON-NLS-1$
		converters.put("rptdesign", streamConverter); //$NON-NLS-1$
		converters.put("svg", streamConverter); //$NON-NLS-1$
		converters.put("url", streamConverter); //$NON-NLS-1$
		converters.put("xaction", streamConverter); //$NON-NLS-1$
		converters.put("xanalyzer", streamConverter); //$NON-NLS-1$
		converters.put("xcdf", streamConverter); //$NON-NLS-1$
		converters.put("xdash", streamConverter); //$NON-NLS-1$
		converters.put("xreportspec", streamConverter); //$NON-NLS-1$
		converters.put("waqr.xaction", streamConverter); //$NON-NLS-1$
		converters.put("xwaqr", streamConverter);//$NON-NLS-1$
		converters.put("gif", streamConverter); //$NON-NLS-1$
		converters.put("css", streamConverter); //$NON-NLS-1$
		converters.put("html", streamConverter); //$NON-NLS-1$
		converters.put("htm", streamConverter); //$NON-NLS-1$
		converters.put("jpg", streamConverter); //$NON-NLS-1$
		converters.put("jpeg", streamConverter); //$NON-NLS-1$
		converters.put("js", streamConverter); //$NON-NLS-1$
		converters.put("cfg.xml", streamConverter); //$NON-NLS-1$
		converters.put("jrxml", streamConverter); //$NON-NLS-1$
		converters.put("png", streamConverter); //$NON-NLS-1$
		converters.put("properties", streamConverter); //$NON-NLS-1$
		converters.put("sql", streamConverter); //$NON-NLS-1$
		converters.put("xmi", streamConverter); //$NON-NLS-1$
		converters.put("xml", streamConverter); //$NON-NLS-1$
		converters.put("cda", streamConverter); //$NON-NLS-1$

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