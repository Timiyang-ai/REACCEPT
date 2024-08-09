@Override
    public void applyDelegate(Config config) {
        for (String port : listenPorts) {
            ConnectionEnd clientCon = new ConnectionEnd();
            clientCon.setConnectionEndType(ConnectionEndType.SERVER);

            String[] parsedPort = port.split(":");
            switch (parsedPort.length) {
                case 1:
                    clientCon.setAlias("client:" + parsedPort[0]);
                    clientCon.setPort(Integer.parseInt(parsedPort[0]));
                    break;
                case 2:
                    clientCon.setAlias(parsedPort[0]);
                    clientCon.setPort(Integer.parseInt(parsedPort[1]));
                    break;
                default:
                    throw new ConfigurationException("Could not parse provided listen port: " + port
                            + ". Expected [CONNECTION_ALIAS:]<PORT>");
            }
            config.addConnectionEnd(clientCon);
        }

        for (String host : serverHosts) {
            ConnectionEnd serverCon = new ConnectionEnd();
            serverCon.setConnectionEndType(ConnectionEndType.CLIENT);

            String[] parsedHost = host.split(":");
            switch (parsedHost.length) {
                case 2:
                    serverCon.setHostname(parsedHost[0]);
                    serverCon.setPort(Integer.parseInt(parsedHost[1]));
                    serverCon.setAlias(host);
                    break;
                case 3:
                    serverCon.setAlias(parsedHost[0]);
                    serverCon.setHostname(parsedHost[1]);
                    serverCon.setPort(Integer.parseInt(parsedHost[2]));
                    break;
                default:
                    throw new ConfigurationException("Could not parse provided server address: " + host
                            + ". Expected [CONNECTION_ALIAS:]<HOSTNAME>:<PORT>");
            }
            config.addConnectionEnd(serverCon);
        }
    }