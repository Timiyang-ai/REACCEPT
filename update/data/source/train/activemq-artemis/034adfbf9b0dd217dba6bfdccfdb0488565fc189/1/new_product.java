private ActiveMQConnectionFactory createConnectionFactory()
   {
      ActiveMQConnectionFactory cf =
         ActiveMQJMSClient.createConnectionFactoryWithoutHA(JMSFactoryType.CF,
                                                            new TransportConfiguration(NETTY_CONNECTOR_FACTORY));

      connectionFactories.add(cf);
      return cf;
   }