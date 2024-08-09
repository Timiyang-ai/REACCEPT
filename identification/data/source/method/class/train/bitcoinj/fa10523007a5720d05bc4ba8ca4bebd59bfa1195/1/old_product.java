public void run() throws PeerException {
        // This should be called in the network loop thread for this peer
        if (conn == null)
            throw new RuntimeException("please call connect() first");

        running = true;

        try {
            while (running) {
                Message m = conn.readMessage();

                // Allow event listeners to filter the message stream. Listeners are allowed to drop messages by
                // returning null.
                for (PeerEventListener listener : eventListeners) {
                    synchronized (listener) {
                        m = listener.onPreMessageReceived(this, m);
                        if (m == null) break;
                    }
                }
                if (m == null) continue;

                if (m instanceof InventoryMessage) {
                    processInv((InventoryMessage) m);
                } else if (m instanceof Block) {
                    processBlock((Block) m);
                } else if (m instanceof Transaction) {
                    processTransaction((Transaction) m);
                } else if (m instanceof GetDataMessage) {
                    processGetData((GetDataMessage) m);
                } else if (m instanceof AddressMessage) {
                    // We don't care about addresses of the network right now. But in future,
                    // we should save them in the wallet so we don't put too much load on the seed nodes and can
                    // properly explore the network.
                } else if (m instanceof HeadersMessage) {
                    processHeaders((HeadersMessage) m);
                } else if (m instanceof AlertMessage) {
                    processAlert((AlertMessage)m);
                } else {
                    // TODO: Handle the other messages we can receive.
                    log.warn("Received unhandled message: {}", m);
                }
            }
        } catch (IOException e) {
            if (!running) {
                // This exception was expected because we are tearing down the socket as part of quitting.
                log.info("{}: Shutting down peer loop", address);
            } else {
                disconnect();
                throw new PeerException(e);
            }
        } catch (ProtocolException e) {
            disconnect();
            throw new PeerException(e);
        } catch (RuntimeException e) {
            log.error("Unexpected exception in peer loop", e);
            disconnect();
            throw e;
        }

        disconnect();
    }