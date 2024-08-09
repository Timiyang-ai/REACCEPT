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

        // baseSeriesVisible
        r1.setBaseSeriesVisible(false);
        assertFalse(r1.equals(r2));
        r2.setBaseSeriesVisible(false);
        assertTrue(r1.equals(r2));

        // seriesVisibleInLegendList
        r1.setSeriesVisibleInLegend(1, Boolean.TRUE);
        assertFalse(r1.equals(r2));
        r2.setSeriesVisibleInLegend(1, Boolean.TRUE);
        assertTrue(r1.equals(r2));

        // baseSeriesVisibleInLegend
        r1.setBaseSeriesVisibleInLegend(false);
        assertFalse(r1.equals(r2));
        r2.setBaseSeriesVisibleInLegend(false);
        assertTrue(r1.equals(r2));

        // paintList
        r1.setSeriesPaint(0, new GradientPaint(1.0f, 2.0f, Color.RED,
                3.0f, 4.0f, Color.WHITE));
        assertFalse(r1.equals(r2));
        r2.setSeriesPaint(0, new GradientPaint(1.0f, 2.0f, Color.RED,
                3.0f, 4.0f, Color.WHITE));
        assertTrue(r1.equals(r2));

        // basePaint
        r1.setBasePaint(new GradientPaint(1.0f, 2.0f, Color.BLUE,
                3.0f, 4.0f, Color.RED));
        assertFalse(r1.equals(r2));
        r2.setBasePaint(new GradientPaint(1.0f, 2.0f, Color.BLUE,
                3.0f, 4.0f, Color.RED));
        assertTrue(r1.equals(r2));

        // fillPaintList
        r1.setSeriesFillPaint(0, new GradientPaint(1.0f, 2.0f, Color.BLUE,
                3.0f, 4.0f, Color.RED));
        assertFalse(r1.equals(r2));
        r2.setSeriesFillPaint(0, new GradientPaint(1.0f, 2.0f, Color.BLUE,
                3.0f, 4.0f, Color.RED));
        assertTrue(r1.equals(r2));

        // baseFillPaint
        r1.setBaseFillPaint(new GradientPaint(1.0f, 2.0f, Color.BLUE,
                3.0f, 4.0f, Color.RED));
        assertFalse(r1.equals(r2));
        r2.setBaseFillPaint(new GradientPaint(1.0f, 2.0f, Color.BLUE,
                3.0f, 4.0f, Color.RED));
        assertTrue(r1.equals(r2));

        // outlinePaintList
        r1.setSeriesOutlinePaint(0, new GradientPaint(1.0f, 2.0f, Color.BLUE,
                3.0f, 4.0f, Color.RED));
        assertFalse(r1.equals(r2));
        r2.setSeriesOutlinePaint(0, new GradientPaint(1.0f, 2.0f, Color.BLUE,
                3.0f, 4.0f, Color.RED));
        assertTrue(r1.equals(r2));

        // baseOutlinePaint
        r1.setBaseOutlinePaint(new GradientPaint(1.0f, 2.0f, Color.BLUE,
                3.0f, 4.0f, Color.RED));
        assertFalse(r1.equals(r2));
        r2.setBaseOutlinePaint(new GradientPaint(1.0f, 2.0f, Color.BLUE,
                3.0f, 4.0f, Color.RED));
        assertTrue(r1.equals(r2));

        // stroke
        Stroke s = new BasicStroke(3.21f);

        // strokeList
        r1.setSeriesStroke(1, s);
        assertFalse(r1.equals(r2));
        r2.setSeriesStroke(1, s);
        assertTrue(r1.equals(r2));

        // baseStroke
        r1.setBaseStroke(s);
        assertFalse(r1.equals(r2));
        r2.setBaseStroke(s);
        assertTrue(r1.equals(r2));

        // outlineStrokeList
        r1.setSeriesOutlineStroke(0, s);
        assertFalse(r1.equals(r2));
        r2.setSeriesOutlineStroke(0, s);
        assertTrue(r1.equals(r2));

        // baseOutlineStroke
        r1.setBaseOutlineStroke(s);
        assertFalse(r1.equals(r2));
        r2.setBaseOutlineStroke(s);
        assertTrue(r1.equals(r2));

        // shapeList
        r1.setSeriesShape(1, new Ellipse2D.Double(1, 2, 3, 4));
        assertFalse(r1.equals(r2));
        r2.setSeriesShape(1, new Ellipse2D.Double(1, 2, 3, 4));
        assertTrue(r1.equals(r2));

        // baseShape
        r1.setBaseShape(new Ellipse2D.Double(1, 2, 3, 4));
        assertFalse(r1.equals(r2));
        r2.setBaseShape(new Ellipse2D.Double(1, 2, 3, 4));
        assertTrue(r1.equals(r2));

        // itemLabelsVisibleList
        r1.setSeriesItemLabelsVisible(1, Boolean.TRUE);
        assertFalse(r1.equals(r2));
        r2.setSeriesItemLabelsVisible(1, Boolean.TRUE);
        assertTrue(r1.equals(r2));

        // baseItemLabelsVisible
        r1.setBaseItemLabelsVisible(true);
        assertFalse(r1.equals(r2));
        r2.setBaseItemLabelsVisible(true);
        assertTrue(r1.equals(r2));

        // itemLabelFontList
        r1.setSeriesItemLabelFont(1, new Font("Serif", Font.BOLD, 9));
        assertFalse(r1.equals(r2));
        r2.setSeriesItemLabelFont(1, new Font("Serif", Font.BOLD, 9));
        assertTrue(r1.equals(r2));

        // baseItemLabelFont
        r1.setBaseItemLabelFont(new Font("Serif", Font.PLAIN, 10));
        assertFalse(r1.equals(r2));
        r2.setBaseItemLabelFont(new Font("Serif", Font.PLAIN, 10));
        assertTrue(r1.equals(r2));

        // itemLabelPaintList
        r1.setSeriesItemLabelPaint(0, new GradientPaint(1.0f, 2.0f, Color.RED,
                3.0f, 4.0f, Color.GRAY));
        assertFalse(r1.equals(r2));
        r2.setSeriesItemLabelPaint(0, new GradientPaint(1.0f, 2.0f, Color.RED,
                3.0f, 4.0f, Color.GRAY));
        assertTrue(r1.equals(r2));

        // baseItemLabelPaint
        r1.setBaseItemLabelPaint(new GradientPaint(1.0f, 2.0f, Color.RED,
                3.0f, 4.0f, Color.GRAY));
        assertFalse(r1.equals(r2));
        r2.setBaseItemLabelPaint(new GradientPaint(1.0f, 2.0f, Color.RED,
                3.0f, 4.0f, Color.GRAY));
        assertTrue(r1.equals(r2));

        // positiveItemLabelPositionList;
        r1.setSeriesPositiveItemLabelPosition(0, new ItemLabelPosition());
        assertFalse(r1.equals(r2));
        r2.setSeriesPositiveItemLabelPosition(0, new ItemLabelPosition());
        assertTrue(r1.equals(r2));

        // basePositiveItemLabelPosition;
        r1.setBasePositiveItemLabelPosition(new ItemLabelPosition(
                ItemLabelAnchor.INSIDE10, TextAnchor.BASELINE_RIGHT));
        assertFalse(r1.equals(r2));
        r2.setBasePositiveItemLabelPosition(new ItemLabelPosition(
                ItemLabelAnchor.INSIDE10, TextAnchor.BASELINE_RIGHT));
        assertTrue(r1.equals(r2));

        // negativeItemLabelPositionList;
        r1.setSeriesNegativeItemLabelPosition(1, new ItemLabelPosition(
                ItemLabelAnchor.INSIDE10, TextAnchor.BASELINE_RIGHT));
        assertFalse(r1.equals(r2));
        r2.setSeriesNegativeItemLabelPosition(1, new ItemLabelPosition(
                ItemLabelAnchor.INSIDE10, TextAnchor.BASELINE_RIGHT));
        assertTrue(r1.equals(r2));

        // baseNegativeItemLabelPosition;
        r1.setBaseNegativeItemLabelPosition(new ItemLabelPosition(
                ItemLabelAnchor.INSIDE10, TextAnchor.BASELINE_RIGHT));
        assertFalse(r1.equals(r2));
        r2.setBaseNegativeItemLabelPosition(new ItemLabelPosition(
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
        r1.setBaseCreateEntities(false);
        assertFalse(r1.equals(r2));
        r2.setBaseCreateEntities(false);
        assertTrue(r1.equals(r2));

        // legendShape
        r1.setLegendShape(0, new Ellipse2D.Double(1.0, 2.0, 3.0, 4.0));
        assertFalse(r1.equals(r2));
        r2.setLegendShape(0, new Ellipse2D.Double(1.0, 2.0, 3.0, 4.0));
        assertTrue(r1.equals(r2));

        // baseLegendShape
        r1.setBaseLegendShape(new Ellipse2D.Double(5.0, 6.0, 7.0, 8.0));
        assertFalse(r1.equals(r2));
        r2.setBaseLegendShape(new Ellipse2D.Double(5.0, 6.0, 7.0, 8.0));
        assertTrue(r1.equals(r2));

        // legendTextFont
        r1.setLegendTextFont(0, new Font("Dialog", Font.PLAIN, 7));
        assertFalse(r1.equals(r2));
        r2.setLegendTextFont(0, new Font("Dialog", Font.PLAIN, 7));
        assertTrue(r1.equals(r2));

        // baseLegendTextFont
        r1.setBaseLegendTextFont(new Font("Dialog", Font.PLAIN, 7));
        assertFalse(r1.equals(r2));
        r2.setBaseLegendTextFont(new Font("Dialog", Font.PLAIN, 7));
        assertTrue(r1.equals(r2));

        // legendTextPaint
        r1.setLegendTextPaint(0, new GradientPaint(1.0f, 2.0f, Color.BLUE,
                3.0f, 4.0f, Color.RED));
        assertFalse(r1.equals(r2));
        r2.setLegendTextPaint(0, new GradientPaint(1.0f, 2.0f, Color.BLUE,
                3.0f, 4.0f, Color.RED));
        assertTrue(r1.equals(r2));

        // baseOutlinePaint
        r1.setBaseLegendTextPaint(new GradientPaint(1.0f, 2.0f, Color.BLUE,
                3.0f, 4.0f, Color.RED));
        assertFalse(r1.equals(r2));
        r2.setBaseLegendTextPaint(new GradientPaint(1.0f, 2.0f, Color.BLUE,
                3.0f, 4.0f, Color.RED));
        assertTrue(r1.equals(r2));
    }