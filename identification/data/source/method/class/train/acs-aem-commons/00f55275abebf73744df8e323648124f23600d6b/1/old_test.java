    @Test
    public void test_isProgressiveJpeg() {
        ValueMap progressiveTransforms = new ValueMapDecorator(new HashMap<String, Object>());

        // Disabled

        progressiveTransforms.put("enabled", false);
        assertFalse(servlet.isProgressiveJpeg("image/png", progressiveTransforms));

        progressiveTransforms.put("enabled", false);
        assertFalse(servlet.isProgressiveJpeg("image/jpg", progressiveTransforms));

        progressiveTransforms.put("enabled", false);
        assertFalse(servlet.isProgressiveJpeg("image/jpeg", progressiveTransforms));

        // Enabled

        progressiveTransforms.put("enabled", true);
        assertFalse(servlet.isProgressiveJpeg("image/png", progressiveTransforms));

        progressiveTransforms.put("enabled", true);
        assertTrue(servlet.isProgressiveJpeg("image/jpg", progressiveTransforms));

        progressiveTransforms.put("enabled", true);
        assertTrue(servlet.isProgressiveJpeg("image/jpeg", progressiveTransforms));

        // Invalid

        progressiveTransforms.put("enabled", true);
        assertFalse(servlet.isProgressiveJpeg(null, progressiveTransforms));

        progressiveTransforms.put("enabled", true);
        assertFalse(servlet.isProgressiveJpeg("", progressiveTransforms));

        progressiveTransforms.remove("enabled");
        assertFalse(servlet.isProgressiveJpeg("", progressiveTransforms));

    }