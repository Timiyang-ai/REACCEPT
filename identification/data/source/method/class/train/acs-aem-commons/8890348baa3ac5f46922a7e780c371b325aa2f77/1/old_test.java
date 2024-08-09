    @Test
    public void test_getImageTransformersWithParams() {

        List<NamedImageTransformer> selectedNamedImageTransformers = new ArrayList<NamedImageTransformer>();
        selectedNamedImageTransformers.add(featureImageTransformer);
        selectedNamedImageTransformers.add(smallImageTransformer);

        final ValueMap imageTransformersWithParams = servlet.getImageTransformersWithParams(selectedNamedImageTransformers);

        assertEquals(IMAGE_TRANSFORM_RESIZE, imageTransformersWithParams.keySet().toArray()[0]);
        ValueMap resize = (ValueMap) imageTransformersWithParams.values().toArray()[0];
        assertEquals("width", resize.keySet().toArray()[0]);
        assertEquals("10", resize.values().toArray()[0]);
        assertEquals(1, resize.keySet().size());

        assertEquals(IMAGE_TRANSFORM_GREYSCALE, imageTransformersWithParams.keySet().toArray()[1]);
        ValueMap greyscale = (ValueMap) imageTransformersWithParams.values().toArray()[1];
        assertEquals("greyscale", greyscale.keySet().toArray()[0]);
        assertEquals("true", greyscale.values().toArray()[0]);
    }