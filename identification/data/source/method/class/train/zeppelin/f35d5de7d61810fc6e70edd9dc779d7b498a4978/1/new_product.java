@GET
  @Path("package")
  public Response getAllPackageInfo() {
    return new JsonResponse(
        Response.Status.OK, "", helium.getAllPackageInfo()).build();
  }