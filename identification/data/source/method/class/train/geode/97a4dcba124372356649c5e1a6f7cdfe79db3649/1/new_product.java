private static GatewayReceiver createGatewayReceiver(Cache cache,
      GatewayReceiverFunctionArgs gatewayReceiverCreateArgs) {

    GatewayReceiverFactory gatewayReceiverFactory = cache.createGatewayReceiverFactory();

    Integer startPort = gatewayReceiverCreateArgs.getStartPort();
    if (startPort != null) {
      gatewayReceiverFactory.setStartPort(startPort);
    }

    Integer endPort = gatewayReceiverCreateArgs.getEndPort();
    if (endPort != null) {
      gatewayReceiverFactory.setEndPort(endPort);
    }

    String bindAddress = gatewayReceiverCreateArgs.getBindAddress();
    if (bindAddress != null) {
      gatewayReceiverFactory.setBindAddress(bindAddress);
    }

    Integer maxTimeBetweenPings = gatewayReceiverCreateArgs.getMaximumTimeBetweenPings();
    if (maxTimeBetweenPings != null) {
      gatewayReceiverFactory.setMaximumTimeBetweenPings(maxTimeBetweenPings);
    }

    Integer socketBufferSize = gatewayReceiverCreateArgs.getSocketBufferSize();
    if (socketBufferSize != null) {
      gatewayReceiverFactory.setSocketBufferSize(socketBufferSize);
    }

    Boolean manualStart = gatewayReceiverCreateArgs.isManualStart();
    if (manualStart != null) {
      gatewayReceiverFactory.setManualStart(manualStart);
    }

    String[] gatewayTransportFilters = gatewayReceiverCreateArgs.getGatewayTransportFilters();
    if (gatewayTransportFilters != null) {
      for (String gatewayTransportFilter : gatewayTransportFilters) {
        Class gatewayTransportFilterKlass = forName(gatewayTransportFilter,
            CliStrings.CREATE_GATEWAYRECEIVER__GATEWAYTRANSPORTFILTER);
        gatewayReceiverFactory.addGatewayTransportFilter(
            (GatewayTransportFilter) newInstance(gatewayTransportFilterKlass,
                CliStrings.CREATE_GATEWAYRECEIVER__GATEWAYTRANSPORTFILTER));
      }
    }

    String hostnameForSenders = gatewayReceiverCreateArgs.getHostnameForSenders();
    if (hostnameForSenders != null) {
      gatewayReceiverFactory.setHostnameForSenders(hostnameForSenders);
    }
    return gatewayReceiverFactory.create();
  }