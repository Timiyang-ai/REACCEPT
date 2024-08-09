@POST
  @Consumes ( MediaType.MULTIPART_FORM_DATA )
  @Produces ( MediaType.TEXT_HTML )
  @Facet( name = "Unsupported" )
  public Response doPostImport( @FormDataParam ( "importDir" ) String importDir,
                                @FormDataParam ( "fileUpload" ) InputStream fileUpload,
                                @FormDataParam ( "overwriteFile" ) String overwriteFile,
                                @FormDataParam ( "overwriteAclPermissions" ) String overwriteAclPermissions,
                                @FormDataParam ( "applyAclPermissions" ) String applyAclPermission,
                                @FormDataParam ( "retainOwnership" ) String retainOwnership,
                                @FormDataParam ( "charSet" ) String charSet,
                                @FormDataParam ( "logLevel" ) String logLevel,
                                @FormDataParam ( "fileUpload" ) FormDataContentDisposition fileInfo,
                                @FormDataParam ( "fileNameOverride" ) String fileNameOverride ) {
    IRepositoryImportLogger importLogger = null;
    ByteArrayOutputStream importLoggerStream = new ByteArrayOutputStream();
    boolean logJobStarted = false;

    if (StringUtils.isBlank( charSet )) {
      charSet =  DEFAULT_CHAR_SET;
    }

    try {
      validateAccess();

      boolean overwriteFileFlag = ( "false".equals( overwriteFile ) ? false : true );
      boolean overwriteAclSettingsFlag = ( "true".equals( overwriteAclPermissions ) ? true : false );
      boolean applyAclSettingsFlag = ( "true".equals( applyAclPermission ) ? true : false );
      boolean retainOwnershipFlag = ( "true".equals( retainOwnership ) ? true : false );

      Level level = Level.toLevel( logLevel );
      ImportSession.getSession().setAclProperties( applyAclSettingsFlag, retainOwnershipFlag, overwriteAclSettingsFlag );

      //The fileNameOverride was added because the formDataContentDispositionfile object cannot reliable
      //contain non US-ASCII characters.  See RFC283 section 2.3 for details
      String fileName = fileNameOverride != null ? fileNameOverride : fileInfo.getFileName();

      RepositoryFileImportBundle.Builder bundleBuilder = new RepositoryFileImportBundle.Builder();
      bundleBuilder.input( fileUpload );
      bundleBuilder.charSet( charSet );
      bundleBuilder.hidden( false );
      bundleBuilder.path( importDir );
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
      importLogger.startJob( importLoggerStream, importDir, level );
      importer.importFile( bundle );

      // Flush the Mondrian cache to show imported data-sources.
      IMondrianCatalogService mondrianCatalogService =
          PentahoSystem.get( IMondrianCatalogService.class, "IMondrianCatalogService", PentahoSessionHolder
              .getSession() );
      mondrianCatalogService.reInit( PentahoSessionHolder.getSession() );
    } catch ( PentahoAccessControlException e ) {
      return Response.serverError().entity( e.toString() ).build();
    } catch( UnifiedRepositoryAccessDeniedException e ) {
      return Response.status( Response.Status.FORBIDDEN ).build();
    } catch ( Exception e ) {
      return Response.serverError().entity( e.toString() ).build();
    } finally {
      if ( logJobStarted == true ) {
        importLogger.endJob();
      }
    }
    String responseBody;
    try {
      responseBody = importLoggerStream.toString( charSet );
    } catch ( UnsupportedEncodingException e ) {
      LOGGER.error( "Encoding of response body is failed. (charSet=" + charSet + ")", e );
      responseBody = importLoggerStream.toString();
    }
    return Response.ok( responseBody, MediaType.TEXT_HTML ).build();
  }