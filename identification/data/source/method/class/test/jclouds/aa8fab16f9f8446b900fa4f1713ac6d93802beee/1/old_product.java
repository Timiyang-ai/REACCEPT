@Named("deleteSecurityGroup")
   @GET
   @QueryParams(keys = "command", values = "deleteSecurityGroup")
   @Fallback(VoidOnNotFoundOr404.class)
   void deleteSecurityGroup(@QueryParam("id") String id);