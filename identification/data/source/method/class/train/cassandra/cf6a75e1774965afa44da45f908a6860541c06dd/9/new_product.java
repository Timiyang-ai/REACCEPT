public void listen(InetAddress localEp) throws IOException
    {
        socketThread = new SocketThread(getServerSocket(localEp), "ACCEPT-" + localEp);
        socketThread.start();
        listenGate.signalAll();
    }