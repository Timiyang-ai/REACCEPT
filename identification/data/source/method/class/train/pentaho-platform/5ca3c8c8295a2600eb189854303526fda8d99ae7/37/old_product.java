@POST
  @Consumes ( MediaType.MULTIPART_FORM_DATA )
  @Produces ( MediaType.TEXT_HTML )
  public Response doPostImport( @FormDataParam ( "importDir" ) String uploadDir,
                                @FormDataParam ( "fileUpload" ) InputStream fileIS, @FormDataParam ( "overwriteFile" ) String overwriteFile,
                                @FormDataParam ( "overwriteAclPermissions" ) String overwriteAclPermissions,
                                @FormDataParam ( "applyAclPermissions" ) String applyAclPermission,
                                @FormDataParam ( "retainOwnership" ) String retainOwnership, @FormDataParam ( "charSet" ) String pCharSet,
                                @FormDataParam ( "logLevel" ) String logLevel, @FormDataParam ( "fileUpload" ) FormDataContentDisposition fileInfo ) {
    IRepositoryImportLogger importLogger = null;
    ByteArrayOutputStream importLoggerStream = new ByteArrayOutputStream();
    boolean logJobStarted = false;

    String charSet = pCharSet == null ? DEFAULT_CHAR_SET : pCharSet;

    try {
      validateAccess();

      boolean overwriteFileFlag = ( "false".equals( overwriteFile ) ? false : true );
      boolean overwriteAclSettingsFlag = ( "true".equals( overwriteAclPermissions ) ? true : false );
      boolean applyAclSettingsFlag = ( "true".equals( applyAclPermission ) ? true : false );
      boolean retainOwnershipFlag = ( "true".equals( retainOwnership ) ? true : false );

      Level level = Level.toLevel( logLevel );
      ImportSession.getSession().setAclProperties( applyAclSettingsFlag, retainOwnershipFlag, overwriteAclSettingsFlag );

      String fileName = URLDecoder.decode( new String( fileInfo.getFileName().getBytes(), charSet ), charSet );

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

      IPlatformImportMimeResolver mimeResolver = PentahoSystem.get( IPlatformImportMimeResolver.class );
      String mimeTypeFromFile = mimeResolver.resolveMimeForFileName( fileName );
      if ( mimeTypeFromFile == null ) {
        return Response.ok( "INVALID_MIME_TYPE", MediaType.TEXT_HTML ).build();
      }
      bundleBuilder.mime( mimeTypeFromFile );

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

      // Flush the IOlapService
      IOlapService olapService =
          PentahoSystem.get( IOlapService.class, "IOlapService", PentahoSessionHolder.getSession() ); //$NON-NLS-1$
      olapService.flushAll( PentahoSessionHolder.getSession() );

    } catch ( PentahoAccessControlException e ) {
      return Response.serverError().entity( e.toString() ).build();
    } catch ( Exception e ) {
      return Response.serverError().entity( e.toString() ).build();
    } finally {
      if ( logJobStarted == true ) {
        importLogger.endJob();
      }
    }
    String responseBody = null;
    try {
      responseBody = importLoggerStream.toString( charSet );
    } catch ( UnsupportedEncodingException e ) {
      LOGGER.error( "Encoding of response body is failed. (charSet=" + charSet + ")", e );
      responseBody = importLoggerStream.toString();
    }
    return Response.ok( responseBody, MediaType.TEXT_HTML ).build();
  }