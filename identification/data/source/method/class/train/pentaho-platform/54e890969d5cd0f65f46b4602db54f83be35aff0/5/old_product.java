@PUT
  @Path( "/delete" )
  @Consumes( { WILDCARD } )
  @StatusCodes({
      @ResponseCode( code = 200, condition = "Successful deletion of file." ),
      @ResponseCode( code = 500, condition = "Failure to delete the file." )
  })
  public Response doDeleteFiles( String params ) {
    try {
      fileService.doDeleteFiles( params );
      return buildOkResponse();

    } catch ( Throwable t ) {
      return buildServerErrorResponse( t );
    }
  }