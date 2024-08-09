@Test
    public void testCreateHelloWorkflow() {
        WorkflowTrace helloWorkflow;
        MessageAction firstAction;
        MessageAction messageAction1;
        MessageAction messageAction2;
        ReceiveAction lastAction;
        ClientHelloMessage clientHelloMessage;

        // Invariants Test: We will always obtain a WorkflowTrace containing at
        // least two TLS-Actions with exactly one message for the first
        // TLS-Action and at least one message for the last TLS-Action, which
        // would be the basic Client/Server-Hello:
        WorkflowConfigurationFactory factory = new WorkflowConfigurationFactory(config);
        helloWorkflow = factory.createWorkflowTrace(WorkflowTraceType.HELLO, RunningModeType.CLIENT);

        Assert.assertThat(helloWorkflow.getMessageActions().size(), Matchers.greaterThanOrEqualTo(2));

        firstAction = helloWorkflow.getMessageActions().get(0);

        Assert.assertEquals(ReceiveAction.class, helloWorkflow.getLastMessageAction().getClass());

        lastAction = (ReceiveAction) helloWorkflow.getLastMessageAction();

        Assert.assertEquals(1, firstAction.getMessages().size());
        Assert.assertThat(lastAction.getExpectedMessages().size(), Matchers.greaterThanOrEqualTo(1));

        Assert.assertEquals(firstAction.getMessages().get(0).getClass(),
                de.rub.nds.tlsattacker.core.protocol.message.ClientHelloMessage.class);
        Assert.assertEquals(lastAction.getExpectedMessages().get(0).getClass(),
                de.rub.nds.tlsattacker.core.protocol.message.ServerHelloMessage.class);

        // Variants Test: if (highestProtocolVersion == DTLS10)
        config.setHighestProtocolVersion(ProtocolVersion.DTLS10);
        config.setClientAuthentication(false);
        workflowConfigurationFactory = new WorkflowConfigurationFactory(config);
        helloWorkflow = workflowConfigurationFactory.createWorkflowTrace(WorkflowTraceType.HELLO,
                RunningModeType.CLIENT);

        firstAction = helloWorkflow.getMessageActions().get(0);
        clientHelloMessage = (ClientHelloMessage) firstAction.getMessages().get(0);
        Assert.assertFalse(clientHelloMessage.getIncludeInDigest());

        Assert.assertThat(helloWorkflow.getMessageActions().size(), Matchers.greaterThanOrEqualTo(4));
        Assert.assertNotNull(helloWorkflow.getMessageActions().get(1));
        Assert.assertNotNull(helloWorkflow.getMessageActions().get(2));
        messageAction1 = helloWorkflow.getMessageActions().get(1);
        messageAction2 = helloWorkflow.getMessageActions().get(2);

        Assert.assertEquals(ReceiveAction.class, messageAction1.getClass());
        Assert.assertEquals(HelloVerifyRequestMessage.class, ((ReceiveAction) messageAction1).getExpectedMessages()
                .get(0).getClass());
        Assert.assertEquals(ClientHelloMessage.class, messageAction2.getMessages().get(0).getClass());

        // if (highestProtocolVersion != TLS13)
        lastAction = (ReceiveAction) helloWorkflow.getLastMessageAction();
        Assert.assertEquals(lastAction.getExpectedMessages().get(1).getClass(),
                de.rub.nds.tlsattacker.core.protocol.message.CertificateMessage.class);

        // if config.getDefaultSelectedCipherSuite().isEphemeral()
        config.setHighestProtocolVersion(ProtocolVersion.DTLS10);
        config.setClientAuthentication(true);
        config.setDefaultSelectedCipherSuite(CipherSuite.TLS_DHE_DSS_WITH_AES_256_GCM_SHA384);
        workflowConfigurationFactory = new WorkflowConfigurationFactory(config);
        helloWorkflow = workflowConfigurationFactory.createWorkflowTrace(WorkflowTraceType.HELLO,
                RunningModeType.CLIENT);

        lastAction = (ReceiveAction) helloWorkflow.getLastMessageAction();
        Assert.assertNotNull(lastAction.getExpectedMessages().get(2));
        Assert.assertEquals(lastAction.getExpectedMessages().get(3).getClass(),
                de.rub.nds.tlsattacker.core.protocol.message.CertificateRequestMessage.class);
    }