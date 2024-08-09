private void listen(InetAddress localEp) throws ConfigurationException
    {
        IInternodeAuthenticator authenticator = DatabaseDescriptor.getInternodeAuthenticator();
        int receiveBufferSize = DatabaseDescriptor.getInternodeRecvBufferSize();

        if (DatabaseDescriptor.getServerEncryptionOptions().internode_encryption != ServerEncryptionOptions.InternodeEncryption.none)
        {
            InetSocketAddress localAddr = new InetSocketAddress(localEp, DatabaseDescriptor.getSSLStoragePort());
            ChannelGroup channelGroup = new DefaultChannelGroup("EncryptedInternodeMessagingGroup", NettyFactory.executorForChannelGroups());
            InboundInitializer initializer = new InboundInitializer(authenticator, DatabaseDescriptor.getServerEncryptionOptions(), channelGroup);
            Channel encryptedChannel = NettyFactory.instance.createInboundChannel(localAddr, initializer, receiveBufferSize);
            serverChannels.add(new ServerChannel(encryptedChannel, channelGroup));
        }

        if (DatabaseDescriptor.getServerEncryptionOptions().internode_encryption != ServerEncryptionOptions.InternodeEncryption.all)
        {
            InetSocketAddress localAddr = new InetSocketAddress(localEp, DatabaseDescriptor.getStoragePort());
            ChannelGroup channelGroup = new DefaultChannelGroup("InternodeMessagingGroup", NettyFactory.executorForChannelGroups());
            InboundInitializer initializer = new InboundInitializer(authenticator, null, channelGroup);
            Channel channel = NettyFactory.instance.createInboundChannel(localAddr, initializer, receiveBufferSize);
            serverChannels.add(new ServerChannel(channel, channelGroup));
        }

        if (serverChannels.isEmpty())
            throw new IllegalStateException("no listening channels set up in MessagingService!");
    }