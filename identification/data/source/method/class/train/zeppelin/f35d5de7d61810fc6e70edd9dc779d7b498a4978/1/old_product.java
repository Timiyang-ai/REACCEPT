@GET
  @Path("all")
  public Response getAll() {
    return new JsonResponse(Response.Status.OK, "", helium.getAllPackageInfo()).build();
  }