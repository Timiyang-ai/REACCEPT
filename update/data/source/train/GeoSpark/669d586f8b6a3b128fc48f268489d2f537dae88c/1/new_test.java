@Test
    public void testIntersects() throws Exception {
        Circle circle = new Circle(makePoint(0.0,0.0), 0.5);
        assertTrue(circle.intersects(makePoint(0, 0)));
        assertTrue(circle.intersects(makePoint(0.1, 0.2)));
        assertFalse(circle.intersects(makePoint(0.4, 0.4)));
        assertFalse(circle.intersects(makePoint(-1, 0.4)));

        assertTrue(circle.intersects(parseWkt("MULTIPOINT ((0.1 0.1), (0.2 0.4))")));
        assertTrue(circle.intersects(parseWkt("MULTIPOINT ((0.1 0.1), (1.2 0.4))")));
        assertFalse(circle.intersects(parseWkt("MULTIPOINT ((1.1 0.1), (0.2 1.4))")));

        // Polygon is fully contained within the circle
        assertTrue(circle.intersects(parseWkt("POLYGON ((-0.1 0.1, 0 0.4, 0.1 0.2, -0.1 0.1))")));
        assertTrue(circle.intersects(parseWkt("POLYGON ((-0.5 0, 0 0.5, 0.5 0, -0.5 0))")));
        // Polygon boundary intersects the circle
        assertTrue(circle.intersects(parseWkt("POLYGON ((0 0, 1 1, 1 0, 0 0))")));
        // Polygon contains the circle
        assertTrue(circle.intersects(parseWkt("POLYGON ((-1 -1, -1 1, 1 1, 1.5 0.5, 1 -1, -1 -1))")));
        // Polygon with a hole intersects the circle, but doesn't contain circle center
        assertTrue(circle.intersects(parseWkt("POLYGON ((-1 -1, -1 1, 1 1, 1 -1, -1 -1), " +
            "(-0.1 -0.1, 0.1 -0.1, 0.1 0.1, -0.1 0.1, -0.1 -0.1))")));

        // No intersection
        assertFalse(circle.intersects(parseWkt("POLYGON ((0.4 0.4, 0.4 0.45, 0.45 0.45, 0.45 0.4, 0.4 0.4))")));
        assertFalse(circle.intersects(parseWkt("POLYGON ((-1 0, -1 1, 0 1, 0 2, -1 2, -1 0))")));
        assertFalse(circle.intersects(parseWkt("POLYGON ((-1 -1, -1 1, 1 1, 1 -1, -1 -1), " +
            "(-0.6 -0.6, 0.6 -0.6, 0.6 0.6, -0.6 0.6, -0.6 -0.6))")));

        assertTrue(circle.intersects(parseWkt("MULTIPOLYGON (((-0.1 0.1, 0 0.4, 0.1 0.2, -0.1 0.1)), " +
            "((-0.5 0, 0 0.5, 0.5 0, -0.5 0)))")));
        assertTrue(circle.intersects(parseWkt("MULTIPOLYGON (((-0.1 0.1, 0 0.4, 0.1 0.2, -0.1 0.1)), " +
            "((-1 0, -1 1, 0 1, 0 2, -1 2, -1 0)))")));
        assertFalse(circle.intersects(parseWkt("MULTIPOLYGON (((0.4 0.4, 0.4 0.45, 0.45 0.45, 0.45 0.4, 0.4 0.4)), " +
            "((-1 0, -1 1, 0 1, 0 2, -1 2, -1 0)))")));

        // Line intersects at 2 points
        assertTrue(circle.intersects(parseWkt("LINESTRING (-1 -1, 1 1)")));
        // Line intersects at one point
        assertTrue(circle.intersects(parseWkt("LINESTRING (-1 0.5, 1 0.5)")));
        // Line is fully within the circle
        assertTrue(circle.intersects(parseWkt("LINESTRING (0 0, 0.1 0.2)")));

        // No intersection
        assertFalse(circle.intersects(parseWkt("LINESTRING (0.4 0.4, 1 1)")));
        assertFalse(circle.intersects(parseWkt("LINESTRING (-0.4 -0.4, -2 -3.2)")));
        assertFalse(circle.intersects(parseWkt("LINESTRING (0.1 0.5, 1 0.5)")));

        assertTrue(circle.intersects(parseWkt("MULTILINESTRING ((-1 -1, 1 1), (-1 0.5, 1 0.5))")));
        assertTrue(circle.intersects(parseWkt("MULTILINESTRING ((-1 -1, 1 1), (0.4 0.4, 1 1))")));
        assertFalse(circle.intersects(parseWkt("MULTILINESTRING ((0.1 0.5, 1 0.5), (0.4 0.4, 1 1))")));
    }