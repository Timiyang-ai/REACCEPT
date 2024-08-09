@PUT
  @Path ( "/delete" )
  @Consumes ( { WILDCARD } )
  @StatusCodes ( {
      @ResponseCode ( code = 200, condition = "Successfully moved file to trash." ),
      @ResponseCode ( code = 500, condition = "Failure move the file to the trash." )
  } )
  public Response doDeleteFiles( String params ) {
    try {
      fileService.doDeleteFiles( params );
      return buildOkResponse();

    } catch ( Throwable t ) {
      return buildServerErrorResponse( t );
    }
  }