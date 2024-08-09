private void listen(InetAddress localEp, ServerEncryptionOptions serverEncryptionOptions) throws ConfigurationException
    {
        IInternodeAuthenticator authenticator = DatabaseDescriptor.getInternodeAuthenticator();
        int receiveBufferSize = DatabaseDescriptor.getInternodeRecvBufferSize();

        // this is the legacy socket, for letting peer nodes that haven't upgrade yet connect to this node.
        // should only occur during cluster upgrade. we can remove this block at 5.0!
        if (serverEncryptionOptions.enabled && serverEncryptionOptions.enable_legacy_ssl_storage_port)
        {
            // clone the encryption options, and explicitly set the optional field to false
            // (do not allow non-TLS connections on the legacy ssl port)
            ServerEncryptionOptions legacyEncOptions = new ServerEncryptionOptions(serverEncryptionOptions);
            legacyEncOptions.optional = false;

            InetSocketAddress localAddr = new InetSocketAddress(localEp, DatabaseDescriptor.getSSLStoragePort());
            ChannelGroup channelGroup = new DefaultChannelGroup("LegacyEncryptedInternodeMessagingGroup", NettyFactory.executorForChannelGroups());
            InboundInitializer initializer = new InboundInitializer(authenticator, legacyEncOptions, channelGroup);
            Channel encryptedChannel = NettyFactory.instance.createInboundChannel(localAddr, initializer, receiveBufferSize);
            serverChannels.add(new ServerChannel(encryptedChannel, channelGroup, localAddr, ServerChannel.SecurityLevel.REQUIRED));
        }

        // this is for the socket that can be plain, only ssl, or optional plain/ssl
        InetSocketAddress localAddr = new InetSocketAddress(localEp, DatabaseDescriptor.getStoragePort());
        ChannelGroup channelGroup = new DefaultChannelGroup("InternodeMessagingGroup", NettyFactory.executorForChannelGroups());
        InboundInitializer initializer = new InboundInitializer(authenticator, serverEncryptionOptions, channelGroup);
        Channel channel = NettyFactory.instance.createInboundChannel(localAddr, initializer, receiveBufferSize);
        ServerChannel.SecurityLevel securityLevel = !serverEncryptionOptions.enabled ? ServerChannel.SecurityLevel.NONE :
                                                    serverEncryptionOptions.optional ? ServerChannel.SecurityLevel.OPTIONAL :
                                                    ServerChannel.SecurityLevel.REQUIRED;
        serverChannels.add(new ServerChannel(channel, channelGroup, localAddr, securityLevel));
    }