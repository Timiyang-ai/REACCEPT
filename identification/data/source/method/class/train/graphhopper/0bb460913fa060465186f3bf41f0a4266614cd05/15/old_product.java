public void lookup( List<LocationIDResult> resList )
    {
        if (isInitialized())
            throw new IllegalStateException("Call lookup only once. Otherwise you'll have problems for queries sharing the same edge.");

        virtualEdges = new ArrayList<EdgeIteratorState>(resList.size() * 2);
        virtualNodes = new PointList(resList.size());
        queryResults = new ArrayList(resList.size());

        TIntObjectMap<List<LocationIDResult>> edge2res = new TIntObjectHashMap<List<LocationIDResult>>(resList.size());

        // Phase 1
        // calculate snapped point and swap direction of closest edge if necessary
        for (LocationIDResult res : resList)
        {
            // Do not create virtual node for a query result if it is directly on a tower node or not found
            EdgeIteratorState closestEdge = res.getClosestEdge();
            if (res.getSnappedPosition() == LocationIDResult.Position.TOWER || closestEdge == null)
                continue;

            int base = closestEdge.getBaseNode();

            // identical direction for all closest edges
            // important to sort multiple results for the same edge by its wayIndex
            if (base > closestEdge.getAdjNode())
            {
                EdgeIteratorState reverseEdge = mainGraph.getEdgeProps(closestEdge.getEdge(), base);
                // #111 reverse edge can be null if real edges are disconnected while CH-prepare
                if (reverseEdge != null)
                {
                    closestEdge = reverseEdge;
                    PointList fullPL = reverseEdge.fetchWayGeometry(3);
                    res.setClosestEdge(reverseEdge);
                    if (res.getSnappedPosition() == LocationIDResult.Position.PILLAR)
                        // ON pillar node                
                        res.setWayIndex(fullPL.getSize() - res.getWayIndex() - 1);
                    else
                        // for case "OFF pillar node"
                        res.setWayIndex(fullPL.getSize() - res.getWayIndex() - 2);

                    if (res.getWayIndex() < 0)
                        throw new IllegalStateException("Problem with wayIndex while reversing closest edge:" + closestEdge + ", " + res);
                } else
                {
                    List<LocationIDResult> tmp = edge2res.get(closestEdge.getEdge());
                    if (tmp != null && tmp.size() > 1)
                        throw new IllegalStateException("No reverse edge can be created but multiple of them were found!? " + resList);
                }
            }

            // find multiple results on same edge
            int edgeId = closestEdge.getEdge();
            List<LocationIDResult> list = edge2res.get(edgeId);
            if (list == null)
            {
                list = new ArrayList<LocationIDResult>(5);
                edge2res.put(edgeId, list);
            }
            list.add(res);
        }

        // Phase 2 - now it is clear which points cut one edge
        // 1. create point lists
        // 2. create virtual edges between virtual nodes and its neighbor (virtual or normal nodes)
        edge2res.forEachValue(new TObjectProcedure<List<LocationIDResult>>()
        {
            @Override
            public boolean execute( List<LocationIDResult> results )
            {
                // we can expect at least one entry in the results
                EdgeIteratorState closestEdge = results.get(0).getClosestEdge();
                final PointList fullPL = closestEdge.fetchWayGeometry(3);
                int baseNode = closestEdge.getBaseNode();
                final EdgeIteratorState reverseState = mainGraph.getEdgeProps(closestEdge.getEdge(), baseNode);
                // sort results on the same edge by the wayIndex and if equal by distance to pillar node
                Collections.sort(results, new Comparator<LocationIDResult>()
                {
                    @Override
                    public int compare( LocationIDResult o1, LocationIDResult o2 )
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
                int reverseFlags = 0;
                // #111 avoid this
                if (reverseState != null)
                    reverseFlags = reverseState.getFlags();
                int prevWayIndex = 1;
                int prevNodeId = baseNode;
                int counter = 0;
                int virtNodeId = virtualNodes.getSize() + mainNodes;
                // Create base and adjacent PointLists for all virtual nodes!
                // We do so via inserting them at the correct position of fullPL and cutting the                
                // fullPL into the right pieces.
                for (LocationIDResult res : results)
                {
                    if (res.getClosestEdge().getBaseNode() != baseNode)
                        throw new IllegalStateException("Base nodes have to be identical was were not: " + closestEdge + " vs " + res.getClosestEdge());

                    queryResults.add(res);
                    GHPoint currSnapped = res.getSnappedPoint();
                    boolean onEdge = res.getSnappedPosition() == LocationIDResult.Position.EDGE;
                    createEdges(prevPoint, prevWayIndex,
                            res.getSnappedPoint(), res.getWayIndex(),
                            onEdge, fullPL, closestEdge, prevNodeId, virtNodeId, reverseFlags);
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
                        false, fullPL, closestEdge, virtNodeId - 1, adjNode, reverseFlags);

                return true;
            }
        });
    }