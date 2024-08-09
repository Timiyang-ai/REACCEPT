public List<InetAddressAndPort> calculateNaturalEndpoints(Token searchToken, TokenMetadata tokenMetadata)
    {
        // we want to preserve insertion order so that the first added endpoint becomes primary
        Set<InetAddressAndPort> replicas = new LinkedHashSet<>();
        Set<Pair<String, String>> seenRacks = new HashSet<>();

        Topology topology = tokenMetadata.getTopology();
        // all endpoints in each DC, so we can check when we have exhausted all the members of a DC
        Multimap<String, InetAddressAndPort> allEndpoints = topology.getDatacenterEndpoints();
        // all racks in a DC so we can check when we have exhausted all racks in a DC
        Map<String, Multimap<String, InetAddressAndPort>> racks = topology.getDatacenterRacks();
        assert !allEndpoints.isEmpty() && !racks.isEmpty() : "not aware of any cluster members";

        int dcsToFill = 0;
        Map<String, DatacenterEndpoints> dcs = new HashMap<>(datacenters.size() * 2);

        // Create a DatacenterEndpoints object for each non-empty DC.
        for (Map.Entry<String, Integer> en : datacenters.entrySet())
        {
            String dc = en.getKey();
            int rf = en.getValue();
            int nodeCount = sizeOrZero(allEndpoints.get(dc));

            if (rf <= 0 || nodeCount <= 0)
                continue;

            DatacenterEndpoints dcEndpoints = new DatacenterEndpoints(rf, sizeOrZero(racks.get(dc)), nodeCount, replicas, seenRacks);
            dcs.put(dc, dcEndpoints);
            ++dcsToFill;
        }

        Iterator<Token> tokenIter = TokenMetadata.ringIterator(tokenMetadata.sortedTokens(), searchToken, false);
        while (dcsToFill > 0 && tokenIter.hasNext())
        {
            Token next = tokenIter.next();
            InetAddressAndPort ep = tokenMetadata.getEndpoint(next);
            Pair<String, String> location = topology.getLocation(ep);
            DatacenterEndpoints dcEndpoints = dcs.get(location.left);
            if (dcEndpoints != null && dcEndpoints.addEndpointAndCheckIfDone(ep, location))
                --dcsToFill;
        }
        return new ArrayList<>(replicas);
    }