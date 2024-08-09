@Override
    public void applyDelegate(Config config) {

        if ((acceptingConnectionEnds == null) || (connectingConnectionEnds == null)) {
            // Though {accepting,connecting}ConnectionEnds are required
            // parameters we can get here if we call applyDelegate
            // manually, e.g. in tests.
            throw new ParameterException("{accepting|connecting}ConnectionEnds is empty!");
        }

        config.clearConnectionEnds();

        for (String conEndStr : acceptingConnectionEnds) {
            ServerConnectionEnd serverConEnd = new ServerConnectionEnd();

            String[] parsedPort = conEndStr.split(":");
            switch (parsedPort.length) {
                case 1:
                    serverConEnd.setAlias("accept:" + parsedPort[0]);
                    serverConEnd.setPort(parsePort(parsedPort[0]));
                    break;
                case 2:
                    serverConEnd.setAlias(parsedPort[0]);
                    serverConEnd.setPort(parsePort(parsedPort[1]));
                    break;
                default:
                    throw new ConfigurationException("Could not parse provided accepting connection" + " end: "
                            + conEndStr + ". Expected [CONNECTION_ALIAS:]<PORT>");
            }
            config.addConnectionEnd(serverConEnd);
        }

        for (String conEndStr : connectingConnectionEnds) {
            ClientConnectionEnd clientConEnd = new ClientConnectionEnd();

            String[] parsedHost = conEndStr.split(":");
            switch (parsedHost.length) {
                case 2:
                    clientConEnd.setHostname(parsedHost[0]);
                    clientConEnd.setPort(parsePort(parsedHost[1]));
                    clientConEnd.setAlias(conEndStr);
                    break;
                case 3:
                    clientConEnd.setAlias(parsedHost[0]);
                    clientConEnd.setHostname(parsedHost[1]);
                    clientConEnd.setPort(parsePort(parsedHost[2]));
                    break;
                default:
                    throw new ConfigurationException("Could not parse provided server address: " + conEndStr
                            + ". Expected [CONNECTION_ALIAS:]<HOSTNAME>:<PORT>");
            }
            config.addConnectionEnd(clientConEnd);
        }
    }