@Override
    synchronized public DatagramSocket socket() {
        if (null == socket) {
            socket = new DatagramSocketAdapter(SocketImplProvider
                    .getDatagramSocketImpl(fd, localPort), this);
        }
        return socket;
    }