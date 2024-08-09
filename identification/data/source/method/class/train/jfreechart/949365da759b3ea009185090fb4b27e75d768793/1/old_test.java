@Test
    public void testEquals() {
        PiePlot plot1 = new PiePlot();
        PiePlot plot2 = new PiePlot();
        assertTrue(plot1.equals(plot2));
        assertTrue(plot2.equals(plot1));

        // noDataMessage
        plot1.setNoDataMessage("No data XYZ");
        assertFalse(plot1.equals(plot2));
        plot2.setNoDataMessage("No data XYZ");
        assertTrue(plot1.equals(plot2));

        // noDataMessageFont
        plot1.setNoDataMessageFont(new Font("SansSerif", Font.PLAIN, 13));
        assertFalse(plot1.equals(plot2));
        plot2.setNoDataMessageFont(new Font("SansSerif", Font.PLAIN, 13));
        assertTrue(plot1.equals(plot2));

        // noDataMessagePaint
        plot1.setNoDataMessagePaint(new GradientPaint(1.0f, 2.0f, Color.red,
                3.0f, 4.0f, Color.blue));
        assertFalse(plot1.equals(plot2));
        plot2.setNoDataMessagePaint(new GradientPaint(1.0f, 2.0f, Color.red,
                3.0f, 4.0f, Color.blue));
        assertTrue(plot1.equals(plot2));

        // insets
        plot1.setInsets(new RectangleInsets(1.0, 2.0, 3.0, 4.0));
        assertFalse(plot1.equals(plot2));
        plot2.setInsets(new RectangleInsets(1.0, 2.0, 3.0, 4.0));
        assertTrue(plot1.equals(plot2));

        // outlineVisible
        plot1.setOutlineVisible(false);
        assertFalse(plot1.equals(plot2));
        plot2.setOutlineVisible(false);
        assertTrue(plot1.equals(plot2));

        // outlineStroke
        BasicStroke s = new BasicStroke(1.23f);
        plot1.setOutlineStroke(s);
        assertFalse(plot1.equals(plot2));
        plot2.setOutlineStroke(s);
        assertTrue(plot1.equals(plot2));

        // outlinePaint
        plot1.setOutlinePaint(new GradientPaint(1.0f, 2.0f, Color.yellow,
                3.0f, 4.0f, Color.green));
        assertFalse(plot1.equals(plot2));
        plot2.setOutlinePaint(new GradientPaint(1.0f, 2.0f, Color.yellow,
                3.0f, 4.0f, Color.green));
        assertTrue(plot1.equals(plot2));

        // backgroundPaint
        plot1.setBackgroundPaint(new GradientPaint(1.0f, 2.0f, Color.cyan,
                3.0f, 4.0f, Color.green));
        assertFalse(plot1.equals(plot2));
        plot2.setBackgroundPaint(new GradientPaint(1.0f, 2.0f, Color.cyan,
                3.0f, 4.0f, Color.green));
        assertTrue(plot1.equals(plot2));

        // backgroundImage
        plot1.setBackgroundImage(JFreeChart.INFO.getLogo());
        assertFalse(plot1.equals(plot2));
        plot2.setBackgroundImage(JFreeChart.INFO.getLogo());
        assertTrue(plot1.equals(plot2));

        // backgroundImageAlignment
        plot1.setBackgroundImageAlignment(Align.BOTTOM_RIGHT);
        assertFalse(plot1.equals(plot2));
        plot2.setBackgroundImageAlignment(Align.BOTTOM_RIGHT);
        assertTrue(plot1.equals(plot2));

        // backgroundImageAlpha
        plot1.setBackgroundImageAlpha(0.77f);
        assertFalse(plot1.equals(plot2));
        plot2.setBackgroundImageAlpha(0.77f);
        assertTrue(plot1.equals(plot2));

        // foregroundAlpha
        plot1.setForegroundAlpha(0.99f);
        assertFalse(plot1.equals(plot2));
        plot2.setForegroundAlpha(0.99f);
        assertTrue(plot1.equals(plot2));

        // backgroundAlpha
        plot1.setBackgroundAlpha(0.99f);
        assertFalse(plot1.equals(plot2));
        plot2.setBackgroundAlpha(0.99f);
        assertTrue(plot1.equals(plot2));

        // drawingSupplier
        plot1.setDrawingSupplier(new DefaultDrawingSupplier(
                new Paint[] {Color.blue}, new Paint[] {Color.red},
                new Stroke[] {new BasicStroke(1.1f)},
                new Stroke[] {new BasicStroke(9.9f)},
                new Shape[] {new Rectangle(1, 2, 3, 4)}));
        assertFalse(plot1.equals(plot2));
        plot2.setDrawingSupplier(new DefaultDrawingSupplier(
                new Paint[] {Color.blue}, new Paint[] {Color.red},
                new Stroke[] {new BasicStroke(1.1f)},
                new Stroke[] {new BasicStroke(9.9f)},
                new Shape[] {new Rectangle(1, 2, 3, 4)}));
        assertTrue(plot1.equals(plot2));
    }