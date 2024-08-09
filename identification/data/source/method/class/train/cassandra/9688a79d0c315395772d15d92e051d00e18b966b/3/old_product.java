public List<InetAddress> calculateNaturalEndpoints(Token searchToken, TokenMetadata tokenMetadata)
    {
        List<InetAddress> endpoints = new ArrayList<InetAddress>(getReplicationFactor());

        for (Entry<String, Integer> dcEntry : datacenters.entrySet())
        {
            String dcName = dcEntry.getKey();
            int dcReplicas = dcEntry.getValue();

            // collect endpoints in this DC; add in bulk to token meta data for computational complexity
            // reasons (CASSANDRA-3831).
            Set<Pair<Token, InetAddress>> dcTokensToUpdate = new HashSet<Pair<Token, InetAddress>>();
            for (Entry<Token, InetAddress> tokenEntry : tokenMetadata.getTokenToEndpointMapForReading().entrySet())
            {
                if (snitch.getDatacenter(tokenEntry.getValue()).equals(dcName))
                    dcTokensToUpdate.add(Pair.create(tokenEntry.getKey(), tokenEntry.getValue()));
            }
            TokenMetadata dcTokens = new TokenMetadata();
            dcTokens.updateNormalTokens(dcTokensToUpdate);

            List<InetAddress> dcEndpoints = new ArrayList<InetAddress>(dcReplicas);
            Set<String> racks = new HashSet<String>();
            // first pass: only collect replicas on unique racks
            for (Iterator<Token> iter = TokenMetadata.ringIterator(dcTokens.sortedTokens(), searchToken, false);
                 dcEndpoints.size() < dcReplicas && iter.hasNext(); )
            {
                Token token = iter.next();
                InetAddress endpoint = dcTokens.getEndpoint(token);
                String rack = snitch.getRack(endpoint);
                if (!racks.contains(rack))
                {
                    dcEndpoints.add(endpoint);
                    racks.add(rack);
                }
            }

            // second pass: if replica count has not been achieved from unique racks, add nodes from duplicate racks
            for (Iterator<Token> iter = TokenMetadata.ringIterator(dcTokens.sortedTokens(), searchToken, false);
                 dcEndpoints.size() < dcReplicas && iter.hasNext(); )
            {
                Token token = iter.next();
                InetAddress endpoint = dcTokens.getEndpoint(token);
                if (!dcEndpoints.contains(endpoint))
                    dcEndpoints.add(endpoint);
            }

            if (logger.isDebugEnabled())
                logger.debug("{} endpoints in datacenter {} for token {} ",
                             new Object[] { StringUtils.join(dcEndpoints, ","), dcName, searchToken});
            endpoints.addAll(dcEndpoints);
        }

        return endpoints;
    }