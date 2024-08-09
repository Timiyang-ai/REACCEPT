public boolean execute(Set<InetAddressAndPort> peers)
    {
        if (targetPercent == 0 || peers == null)
            return true;

        // make a copy of the set, to avoid mucking with the input (in case it's a sensitive collection)
        peers = new HashSet<>(peers);
        peers.remove(FBUtilities.getBroadcastAddressAndPort());

        if (peers.isEmpty())
            return true;

        logger.info("choosing to block until {}% of the {} known peers are marked alive and connections are established; max time to wait = {} seconds",
                    targetPercent, peers.size(), TimeUnit.NANOSECONDS.toSeconds(timeoutNanos));

        long startNanos = System.nanoTime();

        AckMap acks = new AckMap(3);
        int target = (int) ((targetPercent / 100.0) * peers.size());
        CountDownLatch latch = new CountDownLatch(target);

        // set up a listener to react to new nodes becoming alive (in gossip), and account for all the nodes that are already alive
        Set<InetAddressAndPort> alivePeers = Sets.newSetFromMap(new ConcurrentHashMap<>());
        AliveListener listener = new AliveListener(alivePeers, latch, acks);
        Gossiper.instance.register(listener);

        // send out a ping message to open up the non-gossip connections
        sendPingMessages(peers, latch, acks);

        for (InetAddressAndPort peer : peers)
            if (Gossiper.instance.isAlive(peer) && alivePeers.add(peer) && acks.incrementAndCheck(peer))
                latch.countDown();

        boolean succeeded = Uninterruptibles.awaitUninterruptibly(latch, timeoutNanos, TimeUnit.NANOSECONDS);
        Gossiper.instance.unregister(listener);

        int connected = peers.size() - (int) latch.getCount();
        logger.info("After waiting/processing for {} milliseconds, {} out of {} peers ({}%) have been marked alive and had connections established",
                    TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNanos),
                    connected,
                    peers.size(),
                    connected / (peers.size()) * 100.0);
        return succeeded;
    }