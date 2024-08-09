@PUT
  @Path( "/delete" )
  @Consumes( { WILDCARD } )
  public Response doDeleteFiles( String params ) {
    try {
      fileResourceService.doDeleteFiles( params );
      return Response.ok().build();

    } catch ( Throwable t ) {
      return Response.serverError().entity( t.getMessage() ).build();
    }
  }