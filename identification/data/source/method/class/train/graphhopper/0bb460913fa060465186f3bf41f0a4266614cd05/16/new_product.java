public void lookup( List<QueryResult> resList )
    {
        if (isInitialized())
            throw new IllegalStateException("Call lookup only once. Otherwise you'll have problems for queries sharing the same edge.");

        virtualEdges = new ArrayList<EdgeIteratorState>(resList.size() * 2);
        virtualNodes = new PointList(resList.size());
        queryResults = new ArrayList<QueryResult>(resList.size());

        TIntObjectMap<List<QueryResult>> edge2res = new TIntObjectHashMap<List<QueryResult>>(resList.size());

        // Phase 1
        // calculate snapped point and swap direction of closest edge if necessary
        for (QueryResult res : resList)
        {
            // Do not create virtual node for a query result if it is directly on a tower node or not found
            EdgeIteratorState closestEdge = res.getClosestEdge();
            if (res.getSnappedPosition() == QueryResult.Position.TOWER || closestEdge == null)
                continue;

            int base = closestEdge.getBaseNode();

            // Force the identical direction for all closest edges. 
            // It is important to sort multiple results for the same edge by its wayIndex
            boolean doReverse = base > closestEdge.getAdjNode();
            if (base == closestEdge.getAdjNode())
            {
                // check for special case #162 where adj == base and force direction via latitude comparison
                PointList pl = closestEdge.fetchWayGeometry(0);
                if (pl.size() > 1)
                    doReverse = pl.getLatitude(0) > pl.getLatitude(pl.size() - 1);
            }

            if (doReverse)
            {
                closestEdge = closestEdge.detach(true);
                PointList fullPL = closestEdge.fetchWayGeometry(3);
                res.setClosestEdge(closestEdge);
                if (res.getSnappedPosition() == QueryResult.Position.PILLAR)
                    // ON pillar node                
                    res.setWayIndex(fullPL.getSize() - res.getWayIndex() - 1);
                else
                    // for case "OFF pillar node"
                    res.setWayIndex(fullPL.getSize() - res.getWayIndex() - 2);

                if (res.getWayIndex() < 0)
                    throw new IllegalStateException("Problem with wayIndex while reversing closest edge:" + closestEdge + ", " + res);
            }

            // find multiple results on same edge
            int edgeId = closestEdge.getEdge();
            List<QueryResult> list = edge2res.get(edgeId);
            if (list == null)
            {
                list = new ArrayList<QueryResult>(5);
                edge2res.put(edgeId, list);
            }
            list.add(res);
        }

        // Phase 2 - now it is clear which points cut one edge
        // 1. create point lists
        // 2. create virtual edges between virtual nodes and its neighbor (virtual or normal nodes)
        edge2res.forEachValue(new TObjectProcedure<List<QueryResult>>()
        {
            @Override
            public boolean execute( List<QueryResult> results )
            {
                // we can expect at least one entry in the results
                EdgeIteratorState closestEdge = results.get(0).getClosestEdge();
                final PointList fullPL = closestEdge.fetchWayGeometry(3);
                int baseNode = closestEdge.getBaseNode();
                // sort results on the same edge by the wayIndex and if equal by distance to pillar node
                Collections.sort(results, new Comparator<QueryResult>()
                {
                    @Override
                    public int compare( QueryResult o1, QueryResult o2 )
                    {
                        int diff = o1.getWayIndex() - o2.getWayIndex();
                        if (diff == 0)
                        {
                            // sort by distance from snappedPoint to fullPL.get(wayIndex) if wayIndex is identical
                            GHPoint p1 = o1.getSnappedPoint();
                            GHPoint p2 = o2.getSnappedPoint();
                            if (p1.equals(p2))
                                return 0;

                            double fromLat = fullPL.getLatitude(o1.getWayIndex());
                            double fromLon = fullPL.getLongitude(o1.getWayIndex());
                            if (distCalc.calcNormalizedDist(fromLat, fromLon, p1.lat, p1.lon)
                                    > distCalc.calcNormalizedDist(fromLat, fromLon, p2.lat, p2.lon))
                                return 1;
                            return -1;
                        }
                        return diff;
                    }
                });

                GHPoint prevPoint = fullPL.toGHPoint(0);
                int adjNode = closestEdge.getAdjNode();
                long reverseFlags = closestEdge.detach(true).getFlags();
                int prevWayIndex = 1;
                int prevNodeId = baseNode;
                int counter = 0;
                int virtNodeId = virtualNodes.getSize() + mainNodes;
                // Create base and adjacent PointLists for all virtual nodes!
                // We do so via inserting them at the correct position of fullPL and cutting the                
                // fullPL into the right pieces.
                for (QueryResult res : results)
                {
                    if (res.getClosestEdge().getBaseNode() != baseNode)
                        throw new IllegalStateException("Base nodes have to be identical but were not: " + closestEdge + " vs " + res.getClosestEdge());

                    queryResults.add(res);
                    GHPoint currSnapped = res.getSnappedPoint();
                    createEdges(prevPoint, prevWayIndex,
                            res.getSnappedPoint(), res.getWayIndex(),
                            fullPL, closestEdge, prevNodeId, virtNodeId, reverseFlags);
                    virtualNodes.add(currSnapped.lat, currSnapped.lon);

                    // add edges again to set adjacent edges for newVirtNodeId
                    if (counter > 0)
                    {
                        virtualEdges.add(virtualEdges.get(virtualEdges.size() - 2));
                        virtualEdges.add(virtualEdges.get(virtualEdges.size() - 2));
                    }

                    res.setClosestNode(virtNodeId);
                    prevNodeId = virtNodeId;
                    prevWayIndex = res.getWayIndex() + 1;
                    prevPoint = currSnapped;
                    counter++;
                    virtNodeId++;
                }

                // two edges between last result and adjacent node are still missing
                createEdges(prevPoint, prevWayIndex, fullPL.toGHPoint(fullPL.getSize() - 1), fullPL.getSize() - 1,
                        fullPL, closestEdge, virtNodeId - 1, adjNode, reverseFlags);

                return true;
            }
        });
    }