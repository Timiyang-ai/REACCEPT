@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.TEXT_HTML)
	public Response doPostImport(
			@FormDataParam("importDir") String uploadDir,
			@FormDataParam("fileUpload") InputStream fileIS,
			@FormDataParam("overwriteFile") String overwriteFile,
			@FormDataParam("overwriteAclPermissions") String overwriteAclPermissions,
			@FormDataParam("applyAclPermissions") String applyAclPermission,
			@FormDataParam("retainOwnership") String retainOwnership,
			@FormDataParam("charSet") String charSet,
			@FormDataParam("logLevel") String logLevel,
			@FormDataParam("fileUpload") FormDataContentDisposition fileInfo) {
	      IRepositoryImportLogger importLogger = null;
		    ByteArrayOutputStream importLoggerStream = new ByteArrayOutputStream();
			try {
				validateAccess();
				
				boolean overwriteFileFlag = ("false".equals(overwriteFile) ? false : true);
				boolean overwriteAclSettingsFlag = ("true".equals(overwriteAclPermissions) ? true : false);
				boolean applyAclSettingsFlag = ("true".equals(applyAclPermission) ? true : false);
				boolean retainOwnershipFlag = ("true".equals(retainOwnership) ? true : false);
				
				Level level = Level.toLevel(logLevel);
				ImportSession importSession = ImportSession.getSession();
				importSession.setApplyAclSettings(applyAclSettingsFlag);
				importSession.setRetainOwnership(retainOwnershipFlag);
				importSession.setOverwriteAclSettings(overwriteAclSettingsFlag);
	
				RepositoryFileImportBundle.Builder bundleBuilder = new RepositoryFileImportBundle.Builder();
				bundleBuilder.input(fileIS);
				bundleBuilder.charSet(charSet == null?"UTF-8":charSet);
				bundleBuilder.hidden(false);
				bundleBuilder.path(uploadDir);
				bundleBuilder.overwriteFile(overwriteFileFlag);
				bundleBuilder.applyAclSettings(applyAclSettingsFlag);
				bundleBuilder.overwriteAclSettings(overwriteAclSettingsFlag);
				bundleBuilder.retainOwnership(retainOwnershipFlag);
				bundleBuilder.name(fileInfo.getFileName());
				IPlatformImportBundle bundle = bundleBuilder.build();
	
				NameBaseMimeResolver mimeResolver = PentahoSystem.get(NameBaseMimeResolver.class);
				bundleBuilder.mime(mimeResolver.resolveMimeForFileName(fileInfo.getFileName()));
	
				IPlatformImporter importer = PentahoSystem.get(IPlatformImporter.class);
				importLogger = importer.getRepositoryImportLogger();
				
				importLogger.startJob(importLoggerStream, uploadDir, level); 
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
			return Response.ok(importLoggerStream.toString(), MediaType.TEXT_HTML).build();
	}