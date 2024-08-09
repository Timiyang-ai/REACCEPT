@POST
   @Path("/action/recomposeVApp")
   @Produces(RECOMPOSE_VAPP_PARAMS)
   @Consumes(TASK)
   @JAXBResponseParser
   ListenableFuture<Task> recomposeVApp(@EndpointParam URI vAppURI,
                                        @BinderParam(BindToXMLPayload.class) RecomposeVAppParams params);