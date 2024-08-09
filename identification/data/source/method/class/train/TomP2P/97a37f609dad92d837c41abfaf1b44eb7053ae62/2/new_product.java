public BootstrapBuilder peerAddress(final PeerAddress peerAddress) {
        if (peerAddress != null && peerAddress.peerId().equals(Number256.ZERO)) {
            logger.warn("Peer address with peer ID zero provided. Bootstrapping is impossible, because no peer with peer ID set to zero is allowed to exist.");
            return this;
        }
        this.peerAddress = peerAddress;
        return this;
    }