public void tagWithTwoDashes() {
        Component c = readDesign("<v--button></v--button>");
        Assert.assertTrue(c.getClass() == Button.class);
    }