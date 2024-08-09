@PUT
   @Produces(VM)
   @Consumes(TASK)
   @JAXBResponseParser
   ListenableFuture<Task> editVm(@EndpointParam URI vmURI,
                                   @BinderParam(BindToXMLPayload.class) Vm vApp);