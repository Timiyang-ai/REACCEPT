@Nonnull
    public Set<NodeID> broadcastBlock(@Nonnull final Block block, @Nullable final Set<NodeID> skip) {
        Metrics.broadcastBlock(block);

        final Set<NodeID> res = new HashSet<>();
        final BlockIdentifier bi = new BlockIdentifier(block.getHash(), block.getNumber());
        final EthMessage newBlock = new RskMessage(new BlockMessage(block));
        final EthMessage newBlockHashes = new RskMessage(new NewBlockHashesMessage(Arrays.asList(bi)));
        synchronized (activePeers) {
            // Get a randomized list with all the peers that don't have the block yet.
            activePeers.values().forEach(c -> logger.trace("RSK activePeers: {}", c));
            final Vector<Channel> peers = activePeers.values().stream()
                    .filter(p -> skip == null || !skip.contains(new NodeID(p.getNodeId())))
                    .collect(Collectors.toCollection(() -> new Vector<>()));
            Collections.shuffle(peers);

            int sqrt = (int) Math.floor(Math.sqrt(peers.size()));
            for (int i = 0; i < sqrt; i++) {
                Channel peer = peers.get(i);
                res.add(new NodeID(peer.getNodeId()));
                logger.trace("RSK propagate: {}", peer);
                peer.sendMessage(newBlock);
            }
            for (int i = sqrt; i < peers.size(); i++) {
                Channel peer = peers.get(i);
                logger.trace("RSK announce: {}", peer);
                peer.sendMessage(newBlockHashes);
            }
        }

        return res;
    }