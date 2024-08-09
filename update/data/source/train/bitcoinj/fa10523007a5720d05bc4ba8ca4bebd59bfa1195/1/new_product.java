@Override
        public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
        throws Exception {
            Message m = (Message)e.getMessage();
            
            // Allow event listeners to filter the message stream. Listeners are allowed to drop messages by    178             synchronized (listener) {
            // returning null.
            for (PeerEventListener listener : eventListeners) {
                synchronized (listener) {
                    m = listener.onPreMessageReceived(Peer.this, m);
                    if (m == null) break;
                }
            }

            if (m == null) return;

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
            } else if (m instanceof VersionMessage) {
                peerVersionMessage = (VersionMessage)m;
                EventListenerInvoker.invoke(eventListeners, new EventListenerInvoker<PeerEventListener>() {
                    @Override
                    public void invoke(PeerEventListener listener) {
                        listener.onPeerConnected(Peer.this, 1);
                    }
                });
            } else if (m instanceof VersionAck) {
                if (peerVersionMessage == null) {
                    throw new ProtocolException("got a version ack before version");
                }
                if (isAcked) {
                    throw new ProtocolException("got more than one version ack");
                }
                isAcked = true;
            } else {
                // TODO: Handle the other messages we can receive.
                log.warn("Received unhandled message: {}", m);
            }
        }