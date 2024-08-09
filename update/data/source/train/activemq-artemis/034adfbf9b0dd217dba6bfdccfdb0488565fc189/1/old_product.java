private HornetQConnectionFactory createConnectionFactory()
   {
      HornetQConnectionFactory cf =
         HornetQJMSClient.createConnectionFactoryWithoutHA(JMSFactoryType.CF,
                                                           new TransportConfiguration(NETTY_CONNECTOR_FACTORY));

      connectionFactories.add(cf);
      return cf;
   }