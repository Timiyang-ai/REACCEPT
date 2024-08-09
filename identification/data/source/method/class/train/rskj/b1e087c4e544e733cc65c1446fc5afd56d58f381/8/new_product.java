@Nonnull
    public Set<NodeID> broadcastBlock(@Nonnull final Block block) {

        final Set<NodeID> nodesIdsBroadcastedTo = new HashSet<>();
        final BlockIdentifier bi = new BlockIdentifier(block.getHash().getBytes(), block.getNumber());
        final RskMessage newBlock = new RskMessage(new BlockMessage(block));
        final RskMessage newBlockHashes = new RskMessage(new NewBlockHashesMessage(Arrays.asList(bi)));
        synchronized (activePeersLock){
            // Get a randomized list with all the peers that don't have the block yet.
            activePeers.values().forEach(c -> logger.trace("RSK activePeers: {}", c));
            List<Channel> peers = new ArrayList<>(activePeers.values());
            Collections.shuffle(peers);

            int sqrt = (int) Math.floor(Math.sqrt(peers.size()));
            for (int i = 0; i < sqrt; i++) {
                Channel peer = peers.get(i);
                nodesIdsBroadcastedTo.add(peer.getNodeId());
                logger.trace("RSK propagate: {}", peer);
                peer.sendMessage(newBlock);
            }
            for (int i = sqrt; i < peers.size(); i++) {
                Channel peer = peers.get(i);
                logger.trace("RSK announce: {}", peer);
                peer.sendMessage(newBlockHashes);
            }
        }

        return nodesIdsBroadcastedTo;
    }