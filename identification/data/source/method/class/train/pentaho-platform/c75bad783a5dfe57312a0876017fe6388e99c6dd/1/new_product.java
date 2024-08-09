@POST
  @Path( "/invoke" )
  @Consumes( { TEXT_PLAIN } )
  @StatusCodes( {
      @ResponseCode( code = 200, condition = "Action invoked successfully." ),
      @ResponseCode( code = 400, condition = "Bad input - could not invoke action." ),
      @ResponseCode( code = 401, condition = "User does not have permissions to invoke action" ),
      @ResponseCode( code = 500, condition = "Error while retrieving system resources." ),
    } )
  public Response invokeAction(
    @QueryParam( ActionUtil.INVOKER_ASYNC_EXEC ) @DefaultValue( ActionUtil.INVOKER_DEFAULT_ASYNC_EXEC_VALUE ) String async,
    @QueryParam( ActionUtil.INVOKER_ACTIONID ) String actionId,
    @QueryParam( ActionUtil.INVOKER_ACTIONCLASS ) String actionClass,
    @QueryParam( ActionUtil.INVOKER_ACTIONUSER ) String actionUser,
    final String actionParams ) {

    // https://docs.oracle.com/javase/7/docs/api/java/lang/Boolean.html#parseBoolean(java.lang.String)
    boolean isAsyncExecution = Boolean.parseBoolean( async );
    int httpStatus = HttpStatus.SC_INTERNAL_SERVER_ERROR; // default ( pessimistic )

    if ( isAsyncExecution ) {

      // default scenario for execution
      executorService.submit( createCallable( actionId, actionClass, actionUser, actionParams ) );
      httpStatus = HttpStatus.SC_ACCEPTED;

    } else {

      try {

        IActionInvokeStatus status = createCallable( actionId, actionClass, actionUser, actionParams ).call();
        httpStatus = ( status != null && status.getThrowable() == null ) ? HttpStatus.SC_OK : HttpStatus.SC_INTERNAL_SERVER_ERROR;

      } catch ( Throwable t ) {
        getLogger().error( t );
      }
    }

    return Response.status( httpStatus ).build();
  }