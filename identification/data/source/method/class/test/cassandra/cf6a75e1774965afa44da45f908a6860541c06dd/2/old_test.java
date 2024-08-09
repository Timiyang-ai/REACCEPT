    private void listen(ServerEncryptionOptions serverEncryptionOptions, boolean listenOnBroadcastAddr) throws InterruptedException
    {
        InetAddress listenAddress = FBUtilities.getJustLocalAddress();
        if (listenOnBroadcastAddr)
        {
            DatabaseDescriptor.setShouldListenOnBroadcastAddress(true);
            listenAddress = InetAddresses.increment(FBUtilities.getBroadcastAddressAndPort().address);
            DatabaseDescriptor.setListenAddress(listenAddress);
            FBUtilities.reset();
        }

        InboundConnectionSettings settings = new InboundConnectionSettings()
                                             .withEncryption(serverEncryptionOptions);
        InboundSockets connections = new InboundSockets(settings);
        try
        {
            connections.open().await();
            Assert.assertTrue(connections.isListening());

            Set<InetAddressAndPort> expect = new HashSet<>();
            expect.add(InetAddressAndPort.getByAddressOverrideDefaults(listenAddress, DatabaseDescriptor.getStoragePort()));
            if (settings.encryption.enable_legacy_ssl_storage_port)
                expect.add(InetAddressAndPort.getByAddressOverrideDefaults(listenAddress, DatabaseDescriptor.getSSLStoragePort()));
            if (listenOnBroadcastAddr)
            {
                expect.add(InetAddressAndPort.getByAddressOverrideDefaults(FBUtilities.getBroadcastAddressAndPort().address, DatabaseDescriptor.getStoragePort()));
                if (settings.encryption.enable_legacy_ssl_storage_port)
                    expect.add(InetAddressAndPort.getByAddressOverrideDefaults(FBUtilities.getBroadcastAddressAndPort().address, DatabaseDescriptor.getSSLStoragePort()));
            }

            Assert.assertEquals(expect.size(), connections.sockets().size());

            final int legacySslPort = DatabaseDescriptor.getSSLStoragePort();
            for (InboundSockets.InboundSocket socket : connections.sockets())
            {
                Assert.assertEquals(serverEncryptionOptions.enabled, socket.settings.encryption.enabled);
                Assert.assertEquals(serverEncryptionOptions.optional, socket.settings.encryption.optional);
                if (!serverEncryptionOptions.enabled)
                    Assert.assertFalse(legacySslPort == socket.settings.bindAddress.port);
                if (legacySslPort == socket.settings.bindAddress.port)
                    Assert.assertFalse(socket.settings.encryption.optional);
                Assert.assertTrue(socket.settings.bindAddress.toString(), expect.remove(socket.settings.bindAddress));
            }
        }
        finally
        {
            connections.close().await();
            Assert.assertFalse(connections.isListening());
        }
    }