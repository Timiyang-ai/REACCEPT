@Override
    public boolean intersects(Geometry other) {
        // short-circuit test
        Envelope otherEnvelope = other.getEnvelopeInternal();
        if (! getEnvelopeInternal().intersects(otherEnvelope)) {
            return false;
        }

        if (other instanceof Point) {
            return covers((Point) other);
        }

        if (other instanceof LineString) {
            return intersects((LineString) other);
        }

        if (other instanceof Polygon) {
            return intersects((Polygon) other);
        }

        if (other instanceof GeometryCollection) {
            GeometryCollection collection = (GeometryCollection) other;
            for (int i=0; i<collection.getNumGeometries(); i++) {
                if (intersects(collection.getGeometryN(i))) {
                    return true;
                }
            }
            return false;
        }

        throw new IllegalArgumentException("Circle.intersects() doesn't support geometry type " +
            other.getGeometryType());
    }