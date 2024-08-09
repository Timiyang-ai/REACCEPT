@POST
  @Path( "/runInBackground" )
  @Consumes( { TEXT_PLAIN } )
  @StatusCodes( {
      @ResponseCode( code = 200, condition = "Action invoked successfully." ),
      @ResponseCode( code = 400, condition = "Bad input - could not invoke action." ),
      @ResponseCode( code = 401, condition = "User does not have permissions to invoke action" ),
      @ResponseCode( code = 500, condition = "Error while retrieving system resources." ),
    } )
  public Response runInBackground(
    @QueryParam( ActionUtil.INVOKER_ACTIONID ) String actionId,
    @QueryParam( ActionUtil.INVOKER_ACTIONCLASS ) String actionClass,
    @QueryParam( ActionUtil.INVOKER_ACTIONUSER ) String actionUser,
    final String actionParams ) {

    executorService.submit( createRunnable( actionId, actionClass, actionUser, actionParams ) );
    return Response.status( HttpStatus.SC_ACCEPTED ).build();
  }