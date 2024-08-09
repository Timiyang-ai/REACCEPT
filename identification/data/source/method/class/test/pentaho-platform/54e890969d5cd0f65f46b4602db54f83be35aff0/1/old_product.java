@PUT
  @Path("/delete")
  @Consumes({ WILDCARD })
  public Response doDeleteFiles(String params) {
    String[] sourceFileIds = params.split("[,]"); //$NON-NLS-1$
    try {
      for (int i = 0; i < sourceFileIds.length; i++) {
        repoWs.deleteFile(sourceFileIds[i], null);
      }
      return Response.ok().build();
    } catch (Throwable t) {
      t.printStackTrace();
      return Response.serverError().build();
    }
  }