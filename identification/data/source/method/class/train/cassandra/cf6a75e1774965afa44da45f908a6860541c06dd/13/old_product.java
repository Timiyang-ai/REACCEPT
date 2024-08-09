public void listen(InetAddress localEp) throws IOException, ConfigurationException
    {
        callbacks.reset(); // hack to allow tests to stop/restart MS
        for (ServerSocket ss : getServerSocket(localEp))
        {
            SocketThread th = new SocketThread(ss, "ACCEPT-" + localEp);
            th.start();
            socketThreads.add(th);
        }
        listenGate.signalAll();
    }