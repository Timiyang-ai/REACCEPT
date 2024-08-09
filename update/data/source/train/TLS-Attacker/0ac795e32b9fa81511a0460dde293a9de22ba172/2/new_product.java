@Override
    public void applyDelegate(Config config) {

        if ((inboundConnectionStr == null) || (outboundConnectionStr == null)) {
            // Though {inbound, outbound}Connections are required parameters we
            // can get
            // here if we call applyDelegate manually, e.g. in tests.
            throw new ParameterException("{inbound|outbound}ConnectionStr is empty!");
        }

        InboundConnection inboundConnection = config.getDefaultServerConnection();
        if (inboundConnection == null) {
            inboundConnection = new InboundConnection();
            config.setDefaultServerConnection(inboundConnection);
        }
        String[] parsedPort = inboundConnectionStr.split(":");
        switch (parsedPort.length) {
            case 1:
                inboundConnection.setAlias("accept:" + parsedPort[0]);
                inboundConnection.setPort(parsePort(parsedPort[0]));
                break;
            case 2:
                inboundConnection.setAlias(parsedPort[0]);
                inboundConnection.setPort(parsePort(parsedPort[1]));
                break;
            default:
                throw new ConfigurationException("Could not parse provided accepting connection" + " end: "
                        + inboundConnectionStr + ". Expected [CONNECTION_ALIAS:]<PORT>");
        }
        config.setDefaultServerConnection(inboundConnection);

        OutboundConnection outboundConnection = config.getDefaultClientConnection();
        if (outboundConnection == null) {
            outboundConnection = new OutboundConnection();
            config.setDefaultClientConnection(outboundConnection);
        }
        String[] parsedHost = outboundConnectionStr.split(":");
        switch (parsedHost.length) {
            case 2:
                outboundConnection.setHostname(parsedHost[0]);
                outboundConnection.setPort(parsePort(parsedHost[1]));
                outboundConnection.setAlias(outboundConnectionStr);
                break;
            case 3:
                outboundConnection.setAlias(parsedHost[0]);
                outboundConnection.setHostname(parsedHost[1]);
                outboundConnection.setPort(parsePort(parsedHost[2]));
                break;
            default:
                throw new ConfigurationException("Could not parse provided server address: " + outboundConnectionStr
                        + ". Expected [CONNECTION_ALIAS:]<HOSTNAME>:<PORT>");
        }
        config.setDefaultClientConnection(outboundConnection);
    }