@POST
  @Consumes( MediaType.MULTIPART_FORM_DATA )
  @Produces( MediaType.TEXT_HTML )
  public Response doPostImport( @FormDataParam( "importDir" ) String uploadDir,
      @FormDataParam( "fileUpload" ) InputStream fileIS, @FormDataParam( "overwriteFile" ) String overwriteFile,
      @FormDataParam( "overwriteAclPermissions" ) String overwriteAclPermissions,
      @FormDataParam( "applyAclPermissions" ) String applyAclPermission,
      @FormDataParam( "retainOwnership" ) String retainOwnership, @FormDataParam( "charSet" ) String pCharSet,
      @FormDataParam( "logLevel" ) String logLevel, @FormDataParam( "fileUpload" ) FormDataContentDisposition fileInfo ) {
    IRepositoryImportLogger importLogger = null;
    ByteArrayOutputStream importLoggerStream = new ByteArrayOutputStream();
    boolean logJobStarted = false;
    try {
      validateAccess();

      boolean overwriteFileFlag = ( "false".equals( overwriteFile ) ? false : true );
      boolean overwriteAclSettingsFlag = ( "true".equals( overwriteAclPermissions ) ? true : false );
      boolean applyAclSettingsFlag = ( "true".equals( applyAclPermission ) ? true : false );
      boolean retainOwnershipFlag = ( "true".equals( retainOwnership ) ? true : false );

      Level level = Level.toLevel( logLevel );
      ImportSession.getSession().setAclProperties( applyAclSettingsFlag, retainOwnershipFlag, overwriteAclSettingsFlag );

      String charSet = pCharSet == null ? DEFAULT_CHAR_SET : pCharSet;
      String fileName = new String( fileInfo.getFileName().getBytes(), charSet );

      RepositoryFileImportBundle.Builder bundleBuilder = new RepositoryFileImportBundle.Builder();
      bundleBuilder.input( fileIS );
      bundleBuilder.charSet( charSet );
      bundleBuilder.hidden( false );
      bundleBuilder.path( uploadDir );
      bundleBuilder.overwriteFile( overwriteFileFlag );
      bundleBuilder.applyAclSettings( applyAclSettingsFlag );
      bundleBuilder.overwriteAclSettings( overwriteAclSettingsFlag );
      bundleBuilder.retainOwnership( retainOwnershipFlag );
      bundleBuilder.name( fileName );
      IPlatformImportBundle bundle = bundleBuilder.build();

      NameBaseMimeResolver mimeResolver = PentahoSystem.get( NameBaseMimeResolver.class );
      bundleBuilder.mime( mimeResolver.resolveMimeForFileName( fileName ) );

      IPlatformImporter importer = PentahoSystem.get( IPlatformImporter.class );
      importLogger = importer.getRepositoryImportLogger();

      final String mimeType =
          bundle.getMimeType() != null ? bundle.getMimeType() : mimeResolver.resolveMimeForBundle( bundle );
      if ( mimeType == null ) {
        return Response.ok( "INVALID_MIME_TYPE", MediaType.TEXT_HTML ).build();
      }

      logJobStarted = true;
      importLogger.startJob( importLoggerStream, uploadDir, level );
      importer.importFile( bundle );

      // Flush the Mondrian cache to show imported data-sources.
      IMondrianCatalogService mondrianCatalogService =
          PentahoSystem.get( IMondrianCatalogService.class, "IMondrianCatalogService", PentahoSessionHolder
              .getSession() );
      mondrianCatalogService.reInit( PentahoSessionHolder.getSession() );
    } catch ( PentahoAccessControlException e ) {
      return Response.serverError().entity( e.toString() ).build();
    } catch ( Exception e ) {
      return Response.serverError().entity( e.toString() ).build();
    } finally {
      if ( logJobStarted == true ) {
        importLogger.endJob();
      }
    }
    return Response.ok( importLoggerStream.toString(), MediaType.TEXT_HTML ).build();
  }