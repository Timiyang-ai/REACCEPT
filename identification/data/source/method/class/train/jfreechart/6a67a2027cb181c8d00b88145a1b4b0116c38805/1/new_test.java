@Test
    public void testEquals() {
        // have to use a concrete subclass...
        BarRenderer r1 = new BarRenderer();
        BarRenderer r2 = new BarRenderer();
        assertTrue(r1.equals(r2));
        assertTrue(r2.equals(r1));

        // seriesVisibleList
        r1.setSeriesVisible(2, Boolean.TRUE);
        assertFalse(r1.equals(r2));
        r2.setSeriesVisible(2, Boolean.TRUE);
        assertTrue(r1.equals(r2));

        // defaultSeriesVisible
        r1.setDefaultSeriesVisible(false);
        assertFalse(r1.equals(r2));
        r2.setDefaultSeriesVisible(false);
        assertTrue(r1.equals(r2));

        // seriesVisibleInLegendList
        r1.setSeriesVisibleInLegend(1, Boolean.TRUE);
        assertFalse(r1.equals(r2));
        r2.setSeriesVisibleInLegend(1, Boolean.TRUE);
        assertTrue(r1.equals(r2));

        // defaultSeriesVisibleInLegend
        r1.setDefaultSeriesVisibleInLegend(false);
        assertFalse(r1.equals(r2));
        r2.setDefaultSeriesVisibleInLegend(false);
        assertTrue(r1.equals(r2));

        // paintList
        r1.setSeriesPaint(0, new GradientPaint(1.0f, 2.0f, Color.RED,
                3.0f, 4.0f, Color.WHITE));
        assertFalse(r1.equals(r2));
        r2.setSeriesPaint(0, new GradientPaint(1.0f, 2.0f, Color.RED,
                3.0f, 4.0f, Color.WHITE));
        assertTrue(r1.equals(r2));

        // defaultPaint
        r1.setDefaultPaint(new GradientPaint(1.0f, 2.0f, Color.BLUE,
                3.0f, 4.0f, Color.RED));
        assertFalse(r1.equals(r2));
        r2.setDefaultPaint(new GradientPaint(1.0f, 2.0f, Color.BLUE,
                3.0f, 4.0f, Color.RED));
        assertTrue(r1.equals(r2));

        // fillPaintList
        r1.setSeriesFillPaint(0, new GradientPaint(1.0f, 2.0f, Color.BLUE,
                3.0f, 4.0f, Color.RED));
        assertFalse(r1.equals(r2));
        r2.setSeriesFillPaint(0, new GradientPaint(1.0f, 2.0f, Color.BLUE,
                3.0f, 4.0f, Color.RED));
        assertTrue(r1.equals(r2));

        // defaultFillPaint
        r1.setDefaultFillPaint(new GradientPaint(1.0f, 2.0f, Color.BLUE,
                3.0f, 4.0f, Color.RED));
        assertFalse(r1.equals(r2));
        r2.setDefaultFillPaint(new GradientPaint(1.0f, 2.0f, Color.BLUE,
                3.0f, 4.0f, Color.RED));
        assertTrue(r1.equals(r2));

        // outlinePaintList
        r1.setSeriesOutlinePaint(0, new GradientPaint(1.0f, 2.0f, Color.BLUE,
                3.0f, 4.0f, Color.RED));
        assertFalse(r1.equals(r2));
        r2.setSeriesOutlinePaint(0, new GradientPaint(1.0f, 2.0f, Color.BLUE,
                3.0f, 4.0f, Color.RED));
        assertTrue(r1.equals(r2));

        // defaultOutlinePaint
        r1.setDefaultOutlinePaint(new GradientPaint(1.0f, 2.0f, Color.BLUE,
                3.0f, 4.0f, Color.RED));
        assertFalse(r1.equals(r2));
        r2.setDefaultOutlinePaint(new GradientPaint(1.0f, 2.0f, Color.BLUE,
                3.0f, 4.0f, Color.RED));
        assertTrue(r1.equals(r2));

        // stroke
        Stroke s = new BasicStroke(3.21f);

        // strokeList
        r1.setSeriesStroke(1, s);
        assertFalse(r1.equals(r2));
        r2.setSeriesStroke(1, s);
        assertTrue(r1.equals(r2));

        // defaultStroke
        r1.setDefaultStroke(s);
        assertFalse(r1.equals(r2));
        r2.setDefaultStroke(s);
        assertTrue(r1.equals(r2));

        // outlineStrokeList
        r1.setSeriesOutlineStroke(0, s);
        assertFalse(r1.equals(r2));
        r2.setSeriesOutlineStroke(0, s);
        assertTrue(r1.equals(r2));

        // defaultOutlineStroke
        r1.setDefaultOutlineStroke(s);
        assertFalse(r1.equals(r2));
        r2.setDefaultOutlineStroke(s);
        assertTrue(r1.equals(r2));

        // shapeList
        r1.setSeriesShape(1, new Ellipse2D.Double(1, 2, 3, 4));
        assertFalse(r1.equals(r2));
        r2.setSeriesShape(1, new Ellipse2D.Double(1, 2, 3, 4));
        assertTrue(r1.equals(r2));

        // defaultShape
        r1.setDefaultShape(new Ellipse2D.Double(1, 2, 3, 4));
        assertFalse(r1.equals(r2));
        r2.setDefaultShape(new Ellipse2D.Double(1, 2, 3, 4));
        assertTrue(r1.equals(r2));

        // itemLabelsVisibleList
        r1.setSeriesItemLabelsVisible(1, Boolean.TRUE);
        assertFalse(r1.equals(r2));
        r2.setSeriesItemLabelsVisible(1, Boolean.TRUE);
        assertTrue(r1.equals(r2));

        // baseItemLabelsVisible
        r1.setDefaultItemLabelsVisible(true);
        assertFalse(r1.equals(r2));
        r2.setDefaultItemLabelsVisible(true);
        assertTrue(r1.equals(r2));

        // itemLabelFontList
        r1.setSeriesItemLabelFont(1, new Font("Serif", Font.BOLD, 9));
        assertFalse(r1.equals(r2));
        r2.setSeriesItemLabelFont(1, new Font("Serif", Font.BOLD, 9));
        assertTrue(r1.equals(r2));

        // defaultItemLabelFont
        r1.setDefaultItemLabelFont(new Font("Serif", Font.PLAIN, 10));
        assertFalse(r1.equals(r2));
        r2.setDefaultItemLabelFont(new Font("Serif", Font.PLAIN, 10));
        assertTrue(r1.equals(r2));

        // itemLabelPaintList
        r1.setSeriesItemLabelPaint(0, new GradientPaint(1.0f, 2.0f, Color.RED,
                3.0f, 4.0f, Color.GRAY));
        assertFalse(r1.equals(r2));
        r2.setSeriesItemLabelPaint(0, new GradientPaint(1.0f, 2.0f, Color.RED,
                3.0f, 4.0f, Color.GRAY));
        assertTrue(r1.equals(r2));

        // defaultItemLabelPaint
        r1.setDefaultItemLabelPaint(new GradientPaint(1.0f, 2.0f, Color.RED,
                3.0f, 4.0f, Color.GRAY));
        assertFalse(r1.equals(r2));
        r2.setDefaultItemLabelPaint(new GradientPaint(1.0f, 2.0f, Color.RED,
                3.0f, 4.0f, Color.GRAY));
        assertTrue(r1.equals(r2));

        // positiveItemLabelPositionList;
        r1.setSeriesPositiveItemLabelPosition(0, new ItemLabelPosition());
        assertFalse(r1.equals(r2));
        r2.setSeriesPositiveItemLabelPosition(0, new ItemLabelPosition());
        assertTrue(r1.equals(r2));

        // defaultPositiveItemLabelPosition;
        r1.setDefaultPositiveItemLabelPosition(new ItemLabelPosition(
                ItemLabelAnchor.INSIDE10, TextAnchor.BASELINE_RIGHT));
        assertFalse(r1.equals(r2));
        r2.setDefaultPositiveItemLabelPosition(new ItemLabelPosition(
                ItemLabelAnchor.INSIDE10, TextAnchor.BASELINE_RIGHT));
        assertTrue(r1.equals(r2));

        // negativeItemLabelPositionList;
        r1.setSeriesNegativeItemLabelPosition(1, new ItemLabelPosition(
                ItemLabelAnchor.INSIDE10, TextAnchor.BASELINE_RIGHT));
        assertFalse(r1.equals(r2));
        r2.setSeriesNegativeItemLabelPosition(1, new ItemLabelPosition(
                ItemLabelAnchor.INSIDE10, TextAnchor.BASELINE_RIGHT));
        assertTrue(r1.equals(r2));

        // defaultNegativeItemLabelPosition;
        r1.setDefaultNegativeItemLabelPosition(new ItemLabelPosition(
                ItemLabelAnchor.INSIDE10, TextAnchor.BASELINE_RIGHT));
        assertFalse(r1.equals(r2));
        r2.setDefaultNegativeItemLabelPosition(new ItemLabelPosition(
                ItemLabelAnchor.INSIDE10, TextAnchor.BASELINE_RIGHT));
        assertTrue(r1.equals(r2));

        // itemLabelAnchorOffset
        r1.setItemLabelAnchorOffset(3.0);
        assertFalse(r1.equals(r2));
        r2.setItemLabelAnchorOffset(3.0);
        assertTrue(r1.equals(r2));

        // createEntitiesList;
        r1.setSeriesCreateEntities(0, Boolean.TRUE);
        assertFalse(r1.equals(r2));
        r2.setSeriesCreateEntities(0, Boolean.TRUE);
        assertTrue(r1.equals(r2));

        // baseCreateEntities;
        r1.setDefaultCreateEntities(false);
        assertFalse(r1.equals(r2));
        r2.setDefaultCreateEntities(false);
        assertTrue(r1.equals(r2));

        // legendShape
        r1.setLegendShape(0, new Ellipse2D.Double(1.0, 2.0, 3.0, 4.0));
        assertFalse(r1.equals(r2));
        r2.setLegendShape(0, new Ellipse2D.Double(1.0, 2.0, 3.0, 4.0));
        assertTrue(r1.equals(r2));

        // baseLegendShape
        r1.setDefaultLegendShape(new Ellipse2D.Double(5.0, 6.0, 7.0, 8.0));
        assertFalse(r1.equals(r2));
        r2.setDefaultLegendShape(new Ellipse2D.Double(5.0, 6.0, 7.0, 8.0));
        assertTrue(r1.equals(r2));

        // legendTextFont
        r1.setLegendTextFont(0, new Font("Dialog", Font.PLAIN, 7));
        assertFalse(r1.equals(r2));
        r2.setLegendTextFont(0, new Font("Dialog", Font.PLAIN, 7));
        assertTrue(r1.equals(r2));

        // defaultLegendTextFont
        r1.setDefaultLegendTextFont(new Font("Dialog", Font.PLAIN, 7));
        assertFalse(r1.equals(r2));
        r2.setDefaultLegendTextFont(new Font("Dialog", Font.PLAIN, 7));
        assertTrue(r1.equals(r2));

        // legendTextPaint
        r1.setLegendTextPaint(0, new GradientPaint(1.0f, 2.0f, Color.BLUE,
                3.0f, 4.0f, Color.RED));
        assertFalse(r1.equals(r2));
        r2.setLegendTextPaint(0, new GradientPaint(1.0f, 2.0f, Color.BLUE,
                3.0f, 4.0f, Color.RED));
        assertTrue(r1.equals(r2));

        // defaultOutlinePaint
        r1.setDefaultLegendTextPaint(new GradientPaint(1.0f, 2.0f, Color.BLUE,
                3.0f, 4.0f, Color.RED));
        assertFalse(r1.equals(r2));
        r2.setDefaultLegendTextPaint(new GradientPaint(1.0f, 2.0f, Color.BLUE,
                3.0f, 4.0f, Color.RED));
        assertTrue(r1.equals(r2));
    }