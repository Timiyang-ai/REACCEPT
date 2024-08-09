@POST
  @Path(ID_PARAM + WRITE)
  @ApiOperation(value = "Writes to the given output stream associated with the id",
      response = java.lang.Integer.class)
  @Consumes(MediaType.APPLICATION_OCTET_STREAM)
  public Response write(@PathParam("id") final Integer id, final InputStream is) {
    return RestUtils.call(new RestUtils.RestCallable<Long>() {
      @Override
      public Long call() throws Exception {
        FileOutStream os = mStreamCache.getOutStream(id);
        if (os != null) {
          return ByteStreams.copy(is, os);
        }
        throw new IllegalArgumentException("stream does not exist");
      }
    });
  }