@POST
   @Path("users")
   ListenableFuture<User> createUser(@BinderParam(BindToJsonPayload.class) User user);