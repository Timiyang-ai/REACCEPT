@Test
    public void onRender_overlaid_drawsSmallestBarsLast() {
        XYSeries s1 = new SimpleXYSeries(SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "s1", -1, -2, 1, 2);
        XYSeries s2 = new SimpleXYSeries(SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "s2", -2, -1, 2, 1);

        BarRenderer renderer = setupRendererForTesting(s1, s2);
        renderer.setBarOrientation(BarRenderer.BarOrientation.OVERLAID);

        xyPlot.setUserRangeOrigin(0);
        xyPlot.calculateMinMaxVals();
        renderer.onRender(canvas, plotArea, s1, barFormatter, renderStack);

        verify(renderer, times(8))
                .drawBar(eq(canvas), barCaptor.capture(), rectCaptor.capture());

        // list of all bars drawn, in the exact order they were drawn.
        List<BarRenderer.Bar> bars = barCaptor.getAllValues();

        assertEquals(s2.getY(0), bars.get(0).getY());
        assertEquals(s1.getY(0), bars.get(1).getY());

        assertEquals(s1.getY(1), bars.get(2).getY());
        assertEquals(s2.getY(1), bars.get(3).getY());
    }