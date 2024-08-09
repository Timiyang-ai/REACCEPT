@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.TEXT_HTML)
	public Response doPostImport(
			@FormDataParam("importDir") String uploadDir,
			@FormDataParam("fileUpload") InputStream fileIS,
			@FormDataParam("overwrite") String overwrite,
			@FormDataParam("ignoreACLS") String ignoreACLS,
			@FormDataParam("retainOwnership") String retainOwnership,
			@FormDataParam("fileUpload") FormDataContentDisposition fileInfo) {
		
			try {
				validateAccess();
				
				boolean overwriteFileFlag = ("true".equals(overwrite) ? true : false);
				boolean ignoreACLFlag = ("true".equals(ignoreACLS) ? true : false);
				boolean retainOwnershipFlag = ("true".equals(retainOwnership) ? true : false);
	
				RepositoryFileImportBundle.Builder bundleBuilder = new RepositoryFileImportBundle.Builder();
				bundleBuilder.input(fileIS);
				bundleBuilder.charSet("UTF-8");
				bundleBuilder.hidden(false);
				bundleBuilder.path(uploadDir);
				bundleBuilder.overwrite(overwriteFileFlag);
				bundleBuilder.name(fileInfo.getFileName());
				IPlatformImportBundle bundle = bundleBuilder.build();
	
				NameBaseMimeResolver mimeResolver = PentahoSystem.get(NameBaseMimeResolver.class);
				bundleBuilder.mime(mimeResolver.resolveMimeForFileName(fileInfo.getFileName()));
	
				IPlatformImporter importer = PentahoSystem.get(IPlatformImporter.class);
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
			}
			return Response.ok(Messages.getInstance().getString("FileResource.IMPORT_SUCCESS")).build();
	}