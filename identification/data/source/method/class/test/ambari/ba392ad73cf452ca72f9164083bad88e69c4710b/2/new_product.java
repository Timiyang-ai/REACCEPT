@GET
  @Path("/listdir")
  @Produces(MediaType.APPLICATION_JSON)
  public Response listdir(@QueryParam("path") String path) throws Exception {
    try {
      return Response.ok(
          HdfsApi.fileStatusToJSON(getApi(context).listdir(path))).build();
    } catch (FileNotFoundException ex) {
      return Response.ok(Response.Status.NOT_FOUND.getStatusCode())
          .entity(ex.getMessage()).build();
    } catch (Throwable ex) {
      throw new Exception(ex.getMessage());
    }
  }