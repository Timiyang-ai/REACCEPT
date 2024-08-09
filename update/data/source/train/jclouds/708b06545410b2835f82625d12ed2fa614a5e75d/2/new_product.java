@POST
   @Path("/action/relocate")
   @Produces(RELOCATE_VM_PARAMS)
   @Consumes(TASK)
   @JAXBResponseParser
   ListenableFuture<Task> relocateVm(@EndpointParam URI vAppURI,
                                     @BinderParam(BindToXMLPayload.class) RelocateParams params);