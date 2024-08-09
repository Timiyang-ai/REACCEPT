public boolean execute(Set<InetAddressAndPort> peers, Function<InetAddressAndPort, String> getDatacenterSource)
    {
        if (peers == null || this.timeoutNanos < 0)
            return true;

        // make a copy of the set, to avoid mucking with the input (in case it's a sensitive collection)
        peers = new HashSet<>(peers);
        InetAddressAndPort localAddress = FBUtilities.getBroadcastAddressAndPort();
        String localDc = getDatacenterSource.apply(localAddress);

        peers.remove(localAddress);
        if (peers.isEmpty())
            return true;

        // make a copy of the datacenter mapping (in case gossip updates happen during this method or some such)
        Map<InetAddressAndPort, String> peerToDatacenter = new HashMap<>();
        SetMultimap<String, InetAddressAndPort> datacenterToPeers = HashMultimap.create();

        for (InetAddressAndPort peer : peers)
        {
            String datacenter = getDatacenterSource.apply(peer);
            peerToDatacenter.put(peer, datacenter);
            datacenterToPeers.put(datacenter, peer);
        }

        // In the case where we do not want to block startup on remote datacenters (e.g. because clients only use
        // LOCAL_X consistency levels), we remove all other datacenter hosts from the mapping and we only wait
        // on the remaining local datacenter.
        if (!blockForRemoteDcs)
        {
            datacenterToPeers.keySet().retainAll(Collections.singleton(localDc));
            logger.info("Blocking coordination until only a single peer is DOWN in the local datacenter, timeout={}s",
                        TimeUnit.NANOSECONDS.toSeconds(timeoutNanos));
        }
        else
        {
            logger.info("Blocking coordination until only a single peer is DOWN in each datacenter, timeout={}s",
                        TimeUnit.NANOSECONDS.toSeconds(timeoutNanos));
        }

        AckMap acks = new AckMap(3);
        Map<String, CountDownLatch> dcToRemainingPeers = new HashMap<>(datacenterToPeers.size());
        for (String datacenter: datacenterToPeers.keys())
        {
            dcToRemainingPeers.put(datacenter,
                                   new CountDownLatch(Math.max(datacenterToPeers.get(datacenter).size() - 1, 0)));
        }

        long startNanos = System.nanoTime();

        // set up a listener to react to new nodes becoming alive (in gossip), and account for all the nodes that are already alive
        Set<InetAddressAndPort> alivePeers = Collections.newSetFromMap(new ConcurrentHashMap<>());
        AliveListener listener = new AliveListener(alivePeers, dcToRemainingPeers, acks, peerToDatacenter::get);
        Gossiper.instance.register(listener);

        // send out a ping message to open up the non-gossip connections to all peers. Note that this sends the
        // ping messages to _all_ peers, not just the ones we block for in dcToRemainingPeers.
        sendPingMessages(peers, dcToRemainingPeers, acks, peerToDatacenter::get);

        for (InetAddressAndPort peer : peers)
        {
            if (Gossiper.instance.isAlive(peer) && alivePeers.add(peer) && acks.incrementAndCheck(peer))
            {
                String datacenter = peerToDatacenter.get(peer);
                // We have to check because we might only have the local DC in the map
                if (dcToRemainingPeers.containsKey(datacenter))
                    dcToRemainingPeers.get(datacenter).countDown();
            }
        }

        boolean succeeded = true;
        for (String datacenter: dcToRemainingPeers.keySet())
        {
            long remainingNanos = Math.max(1, timeoutNanos - (System.nanoTime() - startNanos));
            succeeded &= Uninterruptibles.awaitUninterruptibly(dcToRemainingPeers.get(datacenter),
                                                               remainingNanos, TimeUnit.NANOSECONDS);
        }

        Gossiper.instance.unregister(listener);

        Map<String, Long> numDown = dcToRemainingPeers.entrySet().stream()
                                                      .collect(Collectors.toMap(Map.Entry::getKey,
                                                                                e -> e.getValue().getCount()));

        if (succeeded)
        {
            logger.info("Ensured sufficient healthy connections with {} after {} milliseconds",
                        numDown.keySet(), TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNanos));
        }
        else
        {
            logger.warn("Timed out after {} milliseconds, was waiting for remaining peers to connect: {}",
                        TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNanos), numDown);
        }

        return succeeded;
    }