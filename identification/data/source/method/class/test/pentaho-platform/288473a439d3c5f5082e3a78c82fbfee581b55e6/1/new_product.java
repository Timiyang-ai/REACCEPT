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
    Optional<Properties> fileProperties = Optional.of( new Properties() );
    fileProperties.get().setProperty( "overwriteFile", String.valueOf( overwriteFile ) );
    return writeFile( pathId, fileContents, fileInfo, fileProperties );
  }