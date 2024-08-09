@Override
   @Test
   public void testCreateConnectionFactory_CompleteList() throws Exception
   {
      JMSServerControl control = createManagementControl();
      control.createConnectionFactory("test", //name
                                      true, // ha
                                      false, // useDiscovery
                                      1, // cfType
                                      "invm", // connectorNames
                                      "tst", // jndiBindins
                                      "tst", // clientID
                                      1, // clientFailureCheckPeriod
                                      1,  // connectionTTL
                                      1, // callTimeout
                                      1, // callFailoverTimeout
                                      1, // minLargeMessageSize
                                      true, // compressLargeMessages
                                      1, // consumerWindowSize
                                      1, // consumerMaxRate
                                      1, // confirmationWindowSize
                                      1, // ProducerWindowSize
                                      1, // producerMaxRate
                                      true, // blockOnACK
                                      true, // blockOnDurableSend
                                      true, // blockOnNonDurableSend
                                      true, // autoGroup
                                      true, // preACK
                                      HornetQClient.DEFAULT_CONNECTION_LOAD_BALANCING_POLICY_CLASS_NAME, // loadBalancingClassName
                                      1, // transactionBatchSize
                                      1, // dupsOKBatchSize
                                      true, // useGlobalPools
                                      1, // scheduleThreadPoolSize
                                      1, // threadPoolMaxSize
                                      1, // retryInterval
                                      1, // retryIntervalMultiplier
                                      1, // maxRetryInterval
                                      1, // reconnectAttempts
                                      true, // failoverOnInitialConnection
                                      "tst"); // groupID


      HornetQQueueConnectionFactory cf = (HornetQQueueConnectionFactory)context.lookup("tst");

      assertEquals(true, cf.isHA());
      assertEquals("tst", cf.getClientID());
      assertEquals(1, cf.getClientFailureCheckPeriod());
      assertEquals(1, cf.getConnectionTTL());
      assertEquals(1, cf.getCallTimeout());
      assertEquals(1, cf.getCallFailoverTimeout());
      assertEquals(1, cf.getMinLargeMessageSize());
      assertEquals(true, cf.isCompressLargeMessage());
      assertEquals(1, cf.getConsumerWindowSize());
      assertEquals(1, cf.getConfirmationWindowSize());
      assertEquals(1, cf.getProducerWindowSize());
      assertEquals(1, cf.getProducerMaxRate());
      assertEquals(true, cf.isBlockOnAcknowledge());
      assertEquals(true, cf.isBlockOnDurableSend());
      assertEquals(true, cf.isBlockOnNonDurableSend());
      assertEquals(true, cf.isAutoGroup());
      assertEquals(true, cf.isPreAcknowledge());
      assertEquals(HornetQClient.DEFAULT_CONNECTION_LOAD_BALANCING_POLICY_CLASS_NAME, cf.getConnectionLoadBalancingPolicyClassName());
      assertEquals(1, cf.getTransactionBatchSize());
      assertEquals(1, cf.getDupsOKBatchSize());
      assertEquals(true, cf.isUseGlobalPools());
      assertEquals(1, cf.getScheduledThreadPoolMaxSize());
      assertEquals(1, cf.getThreadPoolMaxSize());
      assertEquals(1, cf.getRetryInterval());
      assertEquals(1.0, cf.getRetryIntervalMultiplier(), 0.000001);
      assertEquals(1, cf.getMaxRetryInterval());
      assertEquals(1, cf.getReconnectAttempts());
      assertEquals(true, cf.isFailoverOnInitialConnection());
      assertEquals("tst", cf.getGroupID());

   }