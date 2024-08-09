public void listen(InetAddress localEp) throws IOException
    {        
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        final ServerSocket ss = serverChannel.socket();
        ss.setReuseAddress(true);
        ss.bind(new InetSocketAddress(localEp, DatabaseDescriptor.getStoragePort()));

        new Thread(new Runnable()
        {
            public void run()
            {
                while (true)
                {
                    try
                    {
                        Socket socket = ss.accept();
                        new IncomingTcpConnection(socket).start();
                    }
                    catch (IOException e)
                    {
                        throw new RuntimeException(e);
                    }
                }
            }
        }, "ACCEPT-" + localEp).start();
    }