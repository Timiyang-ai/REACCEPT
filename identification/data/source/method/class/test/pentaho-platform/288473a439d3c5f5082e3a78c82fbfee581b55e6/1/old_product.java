@POST
  @Path ( "/file" )
  @Consumes ( { MediaType.MULTIPART_FORM_DATA } )
  @Produces ( MediaType.TEXT_PLAIN )
  @StatusCodes ( {
    @ResponseCode ( code = 200, condition = "Successfully publish the file." ),
    @ResponseCode ( code = 403, condition = "Failure to publish the file due to permissions." ),
    @ResponseCode ( code = 422, condition = "Failure to publish the file due to failed validation." ),
    @ResponseCode ( code = 500, condition = "Failure to publish the file due to a server error." ), } )
  @Facet( name = "Unsupported" )
  public Response writeFileWithEncodedName( @FormDataParam( "importPath" ) String pathId,
                                            @FormDataParam( "fileUpload" ) InputStream fileContents,
                                            @FormDataParam( "overwriteFile" ) Boolean overwriteFile,
                                            @FormDataParam( "fileUpload" ) FormDataContentDisposition fileInfo ) {
    try {
      String decodedPath = URLDecoder.decode( pathId, "UTF-8" );
      if ( invalidPath( decodedPath ) ) {
        final int UNPROCESSABLE_ENTITY = 422;
        return Response.status( UNPROCESSABLE_ENTITY )
          .type( MediaType.TEXT_PLAIN_TYPE )
          .entity( "Cannot publish [" + decodedPath + "] because it contains reserved character(s)" )
          .build();
      }
      repositoryPublishService.publishFile( decodedPath, fileContents, overwriteFile );
    } catch ( PentahoAccessControlException e ) {
      return buildStatusResponse( UNAUTHORIZED, PlatformImportException.PUBLISH_USERNAME_PASSWORD_FAIL );
    } catch ( PlatformImportException e ) {
      logger.error( e );
      return buildStatusResponse( PRECONDITION_FAILED, e.getErrorStatus() );
    } catch ( Exception e ) {
      logger.error( e );
      return buildServerErrorResponse( PlatformImportException.PUBLISH_GENERAL_ERROR );
    }
    return buildPlainTextOkResponse( "SUCCESS" );
  }