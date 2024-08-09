public synchronized void receive(DatagramPacket pack) throws IOException {
        checkClosedAndBind(true);

        InetAddress senderAddr;
        int senderPort;
        DatagramPacket tempPack = new DatagramPacket(new byte[1], 1);

        // means that we have received the packet into the temporary buffer
        boolean copy = false;

        SecurityManager security = System.getSecurityManager();

        if (address != null || security != null) {
            // The socket is connected or we need to check security permissions

            // Check pack before peeking
            if (pack == null) {
                throw new NullPointerException();
            }

            // iterate over incoming packets
            while (true) {
                copy = false;

                // let's get sender's address and port
                try {
                    senderPort = impl.peekData(tempPack);
                    senderAddr = tempPack.getAddress();
                } catch (SocketException e) {
                    if (e.getMessage().equals(
                            "The socket does not support the operation")) { //$NON-NLS-1$
                        // receive packet to temporary buffer
                        tempPack = new DatagramPacket(new byte[pack.capacity],
                                pack.capacity);
                        impl.receive(tempPack);
                        // tempPack's length field is now updated, capacity is unchanged
                        // let's extract address & port
                        senderAddr = tempPack.getAddress();
                        senderPort = tempPack.getPort();
                        copy = true;
                    } else {
                        throw e;
                    }
                }

                if (address == null) {
                    // if we are not connected let's check if we are allowed to
                    // receive packets from sender's address and port
                    try {
                        security.checkAccept(senderAddr.getHostName(),
                                senderPort);
                        // address & port are valid
                        break;
                    } catch (SecurityException e) {
                        if (!copy) {
                            // drop this packet and continue
                            impl.receive(tempPack);
                        }
                    }
                } else if (port == senderPort && address.equals(senderAddr)) {
                    // we are connected and the packet came
                    // from the address & port we are connected to
                    break;
                } else if (!copy) {
                    // drop packet and continue
                    impl.receive(tempPack);
                }
            }
        }

        if (copy) {
            System.arraycopy(tempPack.getData(), 0, pack.getData(), pack
                    .getOffset(), tempPack.getLength());
            // we shouldn't update the pack's capacity field in order to be
            // compatible with RI
            pack.length = tempPack.length;
            pack.setAddress(tempPack.getAddress());
            pack.setPort(tempPack.getPort());
        } else {
            pack.setLength(pack.capacity);
            impl.receive(pack);
            // pack's length field is now updated by native code call;
            // pack's capacity field is unchanged
        }
    }