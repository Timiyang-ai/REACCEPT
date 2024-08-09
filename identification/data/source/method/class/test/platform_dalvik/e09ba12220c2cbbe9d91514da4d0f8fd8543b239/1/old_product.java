public synchronized void receive(DatagramPacket pack) throws IOException {
        checkClosedAndBind(true);

        boolean secure = true;

        InetAddress senderAddr = null;

        int senderPort = 0;
        DatagramPacket tempPack = new DatagramPacket(new byte[1], 1);
        boolean copy = false;

        SecurityManager security = System.getSecurityManager();
        if (address != null || security != null) { // The socket is connected
            // Check pack before peeking
            if (pack == null) {
                throw new NullPointerException();
            }
            secure = false;
            while (!secure) {
                copy = false;
                try {
                    senderPort = impl.peekData(tempPack);
                    senderAddr = tempPack.getAddress();
                } catch (SocketException e) {
                    if (e.getMessage().equals(
                            "The socket does not support the operation")) { //$NON-NLS-1$
                        tempPack = new DatagramPacket(new byte[pack.length],
                                pack.getLength());
                        impl.receive(tempPack);
                        senderAddr = tempPack.getAddress();
                        senderPort = tempPack.getPort();
                        copy = true;
                    } else {
                        throw e;
                    }
                }
                if (address == null) {
                    try {
                        security.checkAccept(senderAddr.getHostName(),
                                senderPort);
                        if (!copy) {
                            secure = true;
                        }
                        break;
                    } catch (SecurityException e) {
                        if (!copy) {
                            if (tempPack == null) {
                                tempPack = new DatagramPacket(
                                        new byte[pack.length], pack.length);
                            }
                            impl.receive(tempPack);
                        }
                    }
                } else if (port == senderPort && address.equals(senderAddr)) {
                    if (!copy) {
                        secure = true;
                    }
                    break;
                } else if (!copy) {
                    if (tempPack == null) {
                        tempPack = new DatagramPacket(new byte[pack.length],
                                pack.length);
                    }
                    impl.receive(tempPack);
                }
            }
        }
        if (copy) {
            System.arraycopy(tempPack.getData(), 0, pack.getData(), pack
                    .getOffset(), tempPack.getLength());
            pack.setLength(tempPack.getLength());
            pack.setAddress(tempPack.getAddress());
            pack.setPort(tempPack.getPort());
        }
        if (secure) {
            impl.receive(pack);
        }
    }