@Test
    public void testEquals() {

        LineAndShapeRenderer r1 = new LineAndShapeRenderer();
        LineAndShapeRenderer r2 = new LineAndShapeRenderer();
        assertEquals(r1, r2);

        r1.setDefaultLinesVisible(!r1.getDefaultLinesVisible());
        assertFalse(r1.equals(r2));
        r2.setDefaultLinesVisible(r1.getDefaultLinesVisible());
        assertTrue(r1.equals(r2));

        r1.setSeriesLinesVisible(1, true);
        assertFalse(r1.equals(r2));
        r2.setSeriesLinesVisible(1, true);
        assertTrue(r1.equals(r2));

        r1.setDefaultShapesVisible(!r1.getDefaultShapesVisible());
        assertFalse(r1.equals(r2));
        r2.setDefaultShapesVisible(r1.getDefaultShapesVisible());
        assertTrue(r1.equals(r2));

        r1.setSeriesShapesVisible(1, true);
        assertFalse(r1.equals(r2));
        r2.setSeriesShapesVisible(1, true);
        assertTrue(r1.equals(r2));

        r1.setSeriesShapesFilled(1, true);
        assertFalse(r1.equals(r2));
        r2.setSeriesShapesFilled(1, true);
        assertTrue(r1.equals(r2));

        r1.setDefaultShapesFilled(false);
        assertFalse(r1.equals(r2));
        r2.setDefaultShapesFilled(false);
        assertTrue(r1.equals(r2));

        r1.setUseOutlinePaint(true);
        assertFalse(r1.equals(r2));
        r2.setUseOutlinePaint(true);
        assertTrue(r1.equals(r2));

        r1.setUseSeriesOffset(true);
        assertFalse(r1.equals(r2));
        r2.setUseSeriesOffset(true);
        assertTrue(r1.equals(r2));

        r1.setItemMargin(0.14);
        assertFalse(r1.equals(r2));
        r2.setItemMargin(0.14);
        assertTrue(r1.equals(r2));

    }