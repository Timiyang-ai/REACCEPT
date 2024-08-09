public void listen(InetAddress localEp) throws IOException
    {        
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        ServerSocket ss = serverChannel.socket();
        ss.bind(new InetSocketAddress(localEp, DatabaseDescriptor.getStoragePort()));
        serverChannel.configureBlocking(false);
        
        SelectionKeyHandler handler = new TcpConnectionHandler(localEp);

        SelectionKey key = SelectorManager.getSelectorManager().register(serverChannel, handler, SelectionKey.OP_ACCEPT);          
        endPoints_.add(localEp);            
        listenSockets_.put(localEp, key);             
    }