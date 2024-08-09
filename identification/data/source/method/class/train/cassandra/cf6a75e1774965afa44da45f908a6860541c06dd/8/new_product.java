public void listen(InetAddress localEp) throws IOException, ConfigurationException
    {
        for (ServerSocket ss: getServerSocket(localEp))
        {
            SocketThread th = new SocketThread(ss, "ACCEPT-" + localEp);
            th.start();
            socketThreads.add(th);
        }
        listenGate.signalAll();
    }