@PUT
  @Consumes(MediaType.MULTIPART_FORM_DATA)
  @Produces(MediaType.APPLICATION_JSON)
  public Response uploadFile(
      @FormDataParam("file") InputStream uploadedInputStream,
      @FormDataParam("file") FormDataContentDisposition contentDisposition,
      @FormDataParam("path") String path) throws Exception {
    if (!path.endsWith("/"))
      path = path + "/";
    String filePath = path + contentDisposition.getFileName();
    uploadFile(filePath, uploadedInputStream);
    return Response.ok(
        HdfsApi.fileStatusToJSON(getApi(context).getFileStatus(filePath)))
        .build();
  }