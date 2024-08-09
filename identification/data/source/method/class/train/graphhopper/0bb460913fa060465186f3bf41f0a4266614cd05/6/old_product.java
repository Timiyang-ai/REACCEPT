public LocationIDResult lookup( double lat, double lon )
    {
        // To handle multiple results one the same edge properly we have to use *this* QueryGraph,
        // which already contains virtual edges and nodes!
        LocationIDResult res = locIndex.findClosest(this, lat, lon, edgeFilter);
        lookup(res);
        return res;
    }