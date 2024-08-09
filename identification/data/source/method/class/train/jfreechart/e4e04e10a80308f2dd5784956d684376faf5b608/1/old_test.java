@Test
    public void testEquals() {
        DefaultCategoryDataset d = new DefaultCategoryDataset();
        d.addValue(1.0, "R1", "C1");
        d.addValue(2.0, "R1", "C2");
        d.addValue(3.0, "R2", "C1");
        d.addValue(4.0, "R2", "C2");
        CategoryItemEntity e1 = new CategoryItemEntity(new Rectangle2D.Double(
                1.0, 2.0, 3.0, 4.0), "ToolTip", "URL", d, 1, "C2", 1);
        CategoryItemEntity e2 = new CategoryItemEntity(new Rectangle2D.Double(
                1.0, 2.0, 3.0, 4.0), "ToolTip", "URL", d, 1, "C2", 1);
        assertTrue(e1.equals(e2));

        e1.setArea(new Rectangle2D.Double(4.0, 3.0, 2.0, 1.0));
        assertFalse(e1.equals(e2));
        e2.setArea(new Rectangle2D.Double(4.0, 3.0, 2.0, 1.0));
        assertTrue(e1.equals(e2));

        e1.setToolTipText("New ToolTip");
        assertFalse(e1.equals(e2));
        e2.setToolTipText("New ToolTip");
        assertTrue(e1.equals(e2));

        e1.setURLText("New URL");
        assertFalse(e1.equals(e2));
        e2.setURLText("New URL");
        assertTrue(e1.equals(e2));

        e1.setCategory("C1");
        assertFalse(e1.equals(e2));
        e2.setCategory("C1");
        assertTrue(e1.equals(e2));

        e1.setCategoryIndex(0);
        assertFalse(e1.equals(e2));
        e2.setCategoryIndex(0);
        assertTrue(e1.equals(e2));

        e1.setSeries(0);
        assertFalse(e1.equals(e2));
        e2.setSeries(0);
        assertTrue(e1.equals(e2));

    }