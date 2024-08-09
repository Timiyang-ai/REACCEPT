@GET
  @Produces("text/plain")
  @ApiOperation(value = "Returns a collection of all hosts", response = HostResponse.Wrapper.class, responseContainer = "List")
  @ApiImplicitParams({
    @ApiImplicitParam(name = QUERY_FIELDS, value = QUERY_FILTER_DESCRIPTION, defaultValue = "Hosts/*", dataType = "string", paramType = "query"),
    @ApiImplicitParam(name = QUERY_SORT, value = QUERY_SORT_DESCRIPTION, defaultValue = "Hosts/host_name.asc", dataType = "string", paramType = "query"),
    @ApiImplicitParam(name = QUERY_PAGE_SIZE, value = QUERY_PAGE_SIZE_DESCRIPTION, defaultValue = DEFAULT_PAGE_SIZE, dataType = "integer", paramType = "query"),
    @ApiImplicitParam(name = QUERY_FROM, value = QUERY_FROM_DESCRIPTION, allowableValues = QUERY_FROM_VALUES, defaultValue = DEFAULT_FROM, dataType = QUERY_FROM_TYPE, paramType = "query"),
    @ApiImplicitParam(name = QUERY_TO, value = QUERY_TO_DESCRIPTION, allowableValues = QUERY_TO_VALUES, dataType = QUERY_TO_TYPE, paramType = "query"),
  })
  @ApiResponses({
    @ApiResponse(code = HttpStatus.SC_OK, message = SUCCESSFUL_OPERATION),
    @ApiResponse(code = HttpStatus.SC_NOT_FOUND, message = CLUSTER_NOT_FOUND),
    @ApiResponse(code = HttpStatus.SC_UNAUTHORIZED, message = NOT_AUTHENTICATED),
    @ApiResponse(code = HttpStatus.SC_FORBIDDEN, message = PERMISSION_DENIED),
    @ApiResponse(code = HttpStatus.SC_INTERNAL_SERVER_ERROR, message = SERVER_ERROR),
  })
  public Response getHosts(String body, @Context HttpHeaders headers, @Context UriInfo ui) {
    return handleRequest(headers, body, ui, Request.Type.GET,
        createHostResource(m_clusterName, null));
  }