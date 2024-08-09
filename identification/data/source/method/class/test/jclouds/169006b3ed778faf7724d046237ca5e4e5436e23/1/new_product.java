@PUT
   @Produces(VM)
   @Consumes(TASK)
   @JAXBResponseParser
   ListenableFuture<Task> edit(@EndpointParam(parser = VmURNToHref.class) String vmUrn,
            @BinderParam(BindToXMLPayload.class) Vm vApp);