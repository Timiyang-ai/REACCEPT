@Test
    public void testCreateEngine() throws InterruptedException, ExecutionException {
        DefaultEngineFactory factory = new DefaultEngineFactory() {
            @Override
            protected IConnectorFactory createConnectorFactory(IPluginRegistry pluginRegistry) {
                return new IConnectorFactory() {
                    @Override
                    public IServiceConnector createConnector(ServiceRequest request, Service service, RequiredAuthType requiredAuthType) {
                        Assert.assertEquals("test", service.getEndpointType());
                        Assert.assertEquals("test:endpoint", service.getEndpoint());
                        IServiceConnector connector = new IServiceConnector() {

                            /**
                             * @see io.apiman.gateway.engine.IServiceConnector#connect(io.apiman.gateway.engine.beans.ServiceRequest, io.apiman.gateway.engine.async.IAsyncResultHandler)
                             */
                            @Override
                            public IServiceConnection connect(ServiceRequest request,
                                    IAsyncResultHandler<IServiceConnectionResponse> handler)
                                    throws ConnectorException {
                                final ServiceResponse response = new ServiceResponse();
                                response.setCode(200);
                                response.setMessage("OK"); //$NON-NLS-1$

                                mockServiceConnectionResponse = new MockServiceConnectionResponse() {

                                    @Override
                                    public void write(IApimanBuffer chunk) {
                                        handleBody(chunk);
                                    }

                                    @Override
                                    protected void handleHead(ServiceResponse head) {
                                        return;
                                    }

                                    @Override
                                    public ServiceResponse getHead() {
                                        return response;
                                    }

                                    @Override
                                    public void end() {
                                        handleEnd();
                                    }

                                    @Override
                                    public void transmit() {
                                        transmitHandler.handle((Void) null);
                                    }

                                    @Override
                                    public void abort() {
                                    }
                                };

                                IAsyncResult<IServiceConnectionResponse> mockResponseResultHandler = mock(IAsyncResult.class);
                                given(mockResponseResultHandler.isSuccess()).willReturn(true);
                                given(mockResponseResultHandler.isError()).willReturn(false);
                                given(mockResponseResultHandler.getResult()).willReturn(mockServiceConnectionResponse);

                                mockServiceConnection = mock(MockServiceConnection.class);
                                given(mockServiceConnection.getHead()).willReturn(request);

                                // Handle head
                                handler.handle(mockResponseResultHandler);

                                return mockServiceConnection;
                            }

                        };
                        return connector;
                    }
                };
            }
        };

        IEngine engine = factory.createEngine();
        Assert.assertNotNull(engine);

        // create a service
        Service service = new Service();
        service.setEndpointType("test");
        service.setEndpoint("test:endpoint");
        service.setOrganizationId("TestOrg");
        service.setServiceId("TestService");
        service.setVersion("1.0");
        // create an app
        Application app = new Application();
        app.setApplicationId("TestApp");
        app.setOrganizationId("TestOrg");
        app.setVersion("1.0");
        Contract contract = new Contract();
        contract.setApiKey("12345");
        contract.setPlan("Gold");
        contract.setServiceId("TestService");
        contract.setServiceOrgId("TestOrg");
        contract.setServiceVersion("1.0");
        contract.setPolicies(policyList);

        app.addContract(contract);

        // simple service/app config
        engine.getRegistry().publishService(service, new IAsyncResultHandler<Void>() {
            @Override
            public void handle(IAsyncResult<Void> result) {
            }
        });
        engine.getRegistry().registerApplication(app, new IAsyncResultHandler<Void>() {
            @Override
            public void handle(IAsyncResult<Void> result) {
            }
        });

        ServiceRequest request = new ServiceRequest();
        request.setApiKey("12345");
        request.setDestination("/");
        request.setType("TEST");

        IServiceRequestExecutor prExecutor = engine.executor(request, new IAsyncResultHandler<IEngineResult>() {

            @Override //At this point, we are either saying *fail* or *response connection is ready*
            public void handle(IAsyncResult<IEngineResult> result) {
                IEngineResult er = result.getResult();

                // No exception occurred
                Assert.assertTrue(result.isSuccess());

                // The chain evaluation succeeded
                Assert.assertNotNull(er);
                Assert.assertTrue(!er.isFailure());
                Assert.assertNotNull(er.getServiceResponse());
                Assert.assertEquals("OK", er.getServiceResponse().getMessage()); //$NON-NLS-1$

                er.bodyHandler(mockBodyHandler);
                er.endHandler(mockEndHandler);
            }
        });

        prExecutor.streamHandler(new IAsyncHandler<ISignalWriteStream>() {

            @Override
            public void handle(ISignalWriteStream streamWriter) {
                streamWriter.write(mockBufferInbound);
                streamWriter.end();
            }
        });

        transmitHandler = new IAsyncHandler<Void>() {

            @Override
            public void handle(Void result) {
                // NB: This is cheating slightly for testing purposes, we don't have real async here.
                // Only now start writing stuff, so user has had opportunity to set handlers
                mockServiceConnectionResponse.write(mockBufferOutbound);
                mockServiceConnectionResponse.end();
            }
        };

        prExecutor.execute();

        // Request handler should receive the mock inbound buffer once only
        verify(mockServiceConnection, times(1)).write(mockBufferInbound);

        // Ultimately user should receive the contrived response and end in order.
        InOrder order = inOrder(mockBodyHandler, mockEndHandler);
        order.verify(mockBodyHandler).handle(mockBufferOutbound);
        order.verify(mockEndHandler).handle((Void) null);
    }