@POST
   @Path("/server/clone/format/json")
   @SelectJson("server")
   @Consumes(MediaType.APPLICATION_JSON)
   ListenableFuture<ServerDetails> cloneServer(@FormParam("serverid") String serverid,
                                               @FormParam("hostname") String hostname,
                                               CloneServerOptions... options);