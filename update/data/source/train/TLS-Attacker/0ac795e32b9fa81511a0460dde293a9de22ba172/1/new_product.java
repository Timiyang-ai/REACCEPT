private WorkflowTrace createHelloWorkflow(ConnectionEnd ourConnectionEnd) {
        WorkflowTrace workflowTrace = new WorkflowTrace(config);

        List<ProtocolMessage> messages = new LinkedList<>();
        ClientHelloMessage clientHello = null;
        if (config.getHighestProtocolVersion() == ProtocolVersion.DTLS10
                || config.getHighestProtocolVersion() == ProtocolVersion.DTLS12) {
            clientHello = new ClientHelloMessage(config);
            clientHello.setIncludeInDigest(false);
        } else {
            clientHello = new ClientHelloMessage(config);
        }
        messages.add(clientHello);

        workflowTrace.addTlsAction(MessageActionFactory.createAction(ourConnectionEnd, ConnectionEndType.CLIENT,
                messages));
        if (config.getHighestProtocolVersion() == ProtocolVersion.DTLS10
                || config.getHighestProtocolVersion() == ProtocolVersion.DTLS12) {

            HelloVerifyRequestMessage helloVerifyRequestMessage = new HelloVerifyRequestMessage(config);
            helloVerifyRequestMessage.setIncludeInDigest(false);
            messages = new LinkedList<>();

            messages.add(helloVerifyRequestMessage);
            workflowTrace.addTlsAction(MessageActionFactory.createAction(ourConnectionEnd, ConnectionEndType.SERVER,
                    messages));
            clientHello = new ClientHelloMessage(config);
            messages = new LinkedList<>();
            messages.add(clientHello);
            workflowTrace.addTlsAction(MessageActionFactory.createAction(ourConnectionEnd, ConnectionEndType.CLIENT,
                    messages));
        }
        messages = new LinkedList<>();
        messages.add(new ServerHelloMessage(config));

        if (config.getHighestProtocolVersion().isTLS13()) {
            messages.add(new EncryptedExtensionsMessage(config));
            if (config.isClientAuthentication()) {
                CertificateRequestMessage certRequest = new CertificateRequestMessage(config);
                messages.add(certRequest);
            }
            if (ourConnectionEnd.getConnectionEndType() == ConnectionEndType.CLIENT) {
                messages.add(new CertificateMessage());
            } else {
                messages.add(new CertificateMessage(config));
            }
            messages.add(new CertificateVerifyMessage(config));
            messages.add(new FinishedMessage(config));
        } else {
            if (config.getDefaultSelectedCipherSuite().isPSK_DHPSK() == false
                    && config.getDefaultSelectedCipherSuite().isSRP() == false) {
                if (ourConnectionEnd.getConnectionEndType() == ConnectionEndType.CLIENT) {
                    messages.add(new CertificateMessage());
                } else {
                    messages.add(new CertificateMessage(config));
                }
            }
            if (config.getDefaultSelectedCipherSuite().isEphemeral()) {
                addServerKeyExchangeMessage(messages);
            }
            if (config.getDefaultSelectedCipherSuite().isSRP()) {
                messages.add(new SRPServerKeyExchangeMessage(config));
            }
            if (config.isClientAuthentication()) {
                CertificateRequestMessage certRequest = new CertificateRequestMessage(config);
                messages.add(certRequest);
            }
            messages.add(new ServerHelloDoneMessage(config));
        }
        workflowTrace.addTlsAction(MessageActionFactory.createAction(ourConnectionEnd, ConnectionEndType.SERVER,
                messages));

        return workflowTrace;
    }