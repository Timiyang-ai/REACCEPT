@Override
    synchronized public DatagramSocket socket() {
        if (null == socket) {
            socket = new DatagramSocketAdapter(
                    new PlainDatagramSocketImpl(fd, localPort), this);
        }
        return socket;
    }