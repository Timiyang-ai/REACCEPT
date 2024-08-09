@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.TEXT_HTML)
	public Response doPostImport(
			@FormDataParam("importDir") String uploadDir,
			@FormDataParam("fileUpload") InputStream fileIS,
			@FormDataParam("overwrite") String overwrite,
			@FormDataParam("ignoreACLS") String ignoreACLS,
			@FormDataParam("retainOwnership") String retainOwnership,
			@FormDataParam("charSet") String charSet,
			@FormDataParam("fileUpload") FormDataContentDisposition fileInfo) {
	        IRepositoryImportLogger importLogger = null;
		    ByteArrayOutputStream importLoggerStream = new ByteArrayOutputStream();
			try {
				validateAccess();
				
				boolean overwriteFileFlag = ("true".equals(overwrite) ? true : false);
				boolean ignoreACLFlag = ("true".equals(ignoreACLS) ? true : false);
				boolean retainOwnershipFlag = ("true".equals(retainOwnership) ? true : false);
	
				RepositoryFileImportBundle.Builder bundleBuilder = new RepositoryFileImportBundle.Builder();
				bundleBuilder.input(fileIS);
				bundleBuilder.charSet(charSet == null?"UTF-8":charSet);
				bundleBuilder.hidden(false);
				bundleBuilder.path(uploadDir);
				bundleBuilder.overwrite(overwriteFileFlag);
				bundleBuilder.name(fileInfo.getFileName());
				IPlatformImportBundle bundle = bundleBuilder.build();
	
				NameBaseMimeResolver mimeResolver = PentahoSystem.get(NameBaseMimeResolver.class);
				bundleBuilder.mime(mimeResolver.resolveMimeForFileName(fileInfo.getFileName()));
	
				IPlatformImporter importer = PentahoSystem.get(IPlatformImporter.class);
				importLogger = importer.getRepositoryImportLogger();
				
				importLogger.startJob(importLoggerStream, "/import/Path"); //TODO!!!!! PENDING!!!! SET RIGHT PATH!!!!
				importer.importFile(bundle);
	
				// Flush the Mondrian cache to show imported datasources.
				IMondrianCatalogService mondrianCatalogService = PentahoSystem.get(
				IMondrianCatalogService.class, "IMondrianCatalogService",
				PentahoSessionHolder.getSession());
				mondrianCatalogService.reInit(PentahoSessionHolder.getSession());
			} catch (PentahoAccessControlException e) {
				return Response.serverError().entity(e.toString()).build();
			} catch (Exception e) {
				return Response.serverError().entity(e.toString()).build();
			} finally {
				importLogger.endJob();
			}
			//return Response.ok(Messages.getInstance().getString("FileResource.IMPORT_SUCCESS")).build();
			return Response.ok(importLoggerStream.toString()).build();
	}