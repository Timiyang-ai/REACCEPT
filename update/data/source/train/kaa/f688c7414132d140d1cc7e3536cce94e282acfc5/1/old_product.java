@ApiOperation(value = "Get CTL schema by it's id",
      notes = "Returns a CTL schema. Only authorized users are allowed to perform this operation.")
  @ApiResponses(value = {
      @ApiResponse(code = 401, message = "The user is not authenticated or invalid credentials were provided"),
      @ApiResponse(code = 403, message = "The tenant ID of the CTL schema does not match the tenant ID of the authenticated user"),
      @ApiResponse(code = 404, message = "A CTL schema with the specified id does not exist."),
      @ApiResponse(code = 500, message = "An unexpected error occurred on the server side")})
  @RequestMapping(value = "CTL/getSchemaById", params = {"id"}, method = RequestMethod.GET)
  @ResponseBody
  public CTLSchemaDto getCTLSchemaById(
      @ApiParam(name = "id", value = "A unique CTL schema identifier", required = true)
      @RequestParam String id) throws KaaAdminServiceException {
    return ctlService.getCtlSchemaById(id);
  }