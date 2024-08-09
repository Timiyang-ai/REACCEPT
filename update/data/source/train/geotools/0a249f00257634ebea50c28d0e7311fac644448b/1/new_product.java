public boolean validate(
            SimpleFeature feature, SimpleFeatureType type, ValidationResults results) {
        LOGGER.setLevel(Level.ALL);

        LineString line = null;
        try {
            line = getDefaultLineString(feature);
        } catch (ClassCastException unLine) {
            results.error(feature, "Geometry is required to be a LineString");
            // System.out.println(feature.getID() + "  name: " + getName());
            // System.out.println(feature.getID() + "   ref: " + getTypeRef());
            // System.out.println(feature.getID() + "   ref: " + getTypeRefs());
        }
        if (line == null) {
            // Ignore null geometry (user can check with nullZero )
            return true;
        }
        if (line.getNumPoints() < 2) {
            results.warning(feature, "LineString contains too few points");
            return false;
        }
        GeometryFactory gf = new GeometryFactory();

        int numPoints = line.getNumPoints();

        // break up the LineString into line segments
        LineString[] segments = new LineString[numPoints - 1];

        for (int i = 0; i < (numPoints - 1); i++) {
            Coordinate[] coords =
                    new Coordinate[] {line.getCoordinateN(i), line.getCoordinateN(i + 1)};
            segments[i] = gf.createLineString(coords);
        }

        // intersect all of the line segments with each other
        for (int i = 0; i < segments.length; i++) // for each line segment
        {
            for (int j = 0; j < segments.length; j++) // intersect with every other line segment
            {
                if ((i != j) && ((i - 1) != j) && ((i + 1) != j)) // if they aren't the same segment
                {
                    if (segments[i].crosses(segments[j])) // changed to crosses - bowens
                    {
                        // log the error and return
                        results.error(feature, "LineString crossed itself");
                        return false;
                    }
                }
            }
        }
        return true;
    }