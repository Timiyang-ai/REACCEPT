public void listen(InetAddress localEp) throws IOException, ConfigurationException
    {
        socketThread = new SocketThread(getServerSocket(localEp), "ACCEPT-" + localEp);
        socketThread.start();
        listenGate.signalAll();
    }