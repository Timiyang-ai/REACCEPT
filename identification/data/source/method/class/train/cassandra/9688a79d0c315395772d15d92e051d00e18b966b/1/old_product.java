@SuppressWarnings("serial")
    public List<InetAddress> calculateNaturalEndpoints(Token searchToken, TokenMetadata tokenMetadata)
    {
        // we want to preserve insertion order so that the first added endpoint becomes primary
        Set<InetAddress> replicas = new LinkedHashSet<>();
        // replicas we have found in each DC
        Map<String, Set<InetAddress>> dcReplicas = new HashMap<>(datacenters.size());
        for (Map.Entry<String, Integer> dc : datacenters.entrySet())
            dcReplicas.put(dc.getKey(), new HashSet<InetAddress>(dc.getValue()));

        Topology topology = tokenMetadata.getTopology();
        // all endpoints in each DC, so we can check when we have exhausted all the members of a DC
        Multimap<String, InetAddress> allEndpoints = topology.getDatacenterEndpoints();
        // all racks in a DC so we can check when we have exhausted all racks in a DC
        Map<String, Multimap<String, InetAddress>> racks = topology.getDatacenterRacks();
        assert !allEndpoints.isEmpty() && !racks.isEmpty() : "not aware of any cluster members";

        // tracks the racks we have already placed replicas in
        Map<String, Set<String>> seenRacks = new HashMap<>(datacenters.size());
        for (Map.Entry<String, Integer> dc : datacenters.entrySet())
            seenRacks.put(dc.getKey(), new HashSet<String>());

        // tracks the endpoints that we skipped over while looking for unique racks
        // when we relax the rack uniqueness we can append this to the current result so we don't have to wind back the iterator
        Map<String, Set<InetAddress>> skippedDcEndpoints = new HashMap<>(datacenters.size());
        for (Map.Entry<String, Integer> dc : datacenters.entrySet())
            skippedDcEndpoints.put(dc.getKey(), new LinkedHashSet<InetAddress>());

        Iterator<Token> tokenIter = TokenMetadata.ringIterator(tokenMetadata.sortedTokens(), searchToken, false);
        while (tokenIter.hasNext() && !hasSufficientReplicas(dcReplicas, allEndpoints))
        {
            Token next = tokenIter.next();
            InetAddress ep = tokenMetadata.getEndpoint(next);
            String dc = snitch.getDatacenter(ep);
            // have we already found all replicas for this dc?
            if (!datacenters.containsKey(dc) || hasSufficientReplicas(dc, dcReplicas, allEndpoints))
                continue;
            // can we skip checking the rack?
            if (seenRacks.get(dc).size() == racks.get(dc).keySet().size())
            {
                dcReplicas.get(dc).add(ep);
                replicas.add(ep);
            }
            else
            {
                String rack = snitch.getRack(ep);
                // is this a new rack?
                if (seenRacks.get(dc).contains(rack))
                {
                    skippedDcEndpoints.get(dc).add(ep);
                }
                else
                {
                    dcReplicas.get(dc).add(ep);
                    replicas.add(ep);
                    seenRacks.get(dc).add(rack);
                    // if we've run out of distinct racks, add the hosts we skipped past already (up to RF)
                    if (seenRacks.get(dc).size() == racks.get(dc).keySet().size())
                    {
                        Iterator<InetAddress> skippedIt = skippedDcEndpoints.get(dc).iterator();
                        while (skippedIt.hasNext() && !hasSufficientReplicas(dc, dcReplicas, allEndpoints))
                        {
                            InetAddress nextSkipped = skippedIt.next();
                            dcReplicas.get(dc).add(nextSkipped);
                            replicas.add(nextSkipped);
                        }
                    }
                }
            }
        }

        return new ArrayList<InetAddress>(replicas);
    }