public void tagWithTwoDashes() {
        Component c = readDesign("<vaadin--button></vaadin--button>");
        Assert.assertTrue(c.getClass() == Button.class);
    }