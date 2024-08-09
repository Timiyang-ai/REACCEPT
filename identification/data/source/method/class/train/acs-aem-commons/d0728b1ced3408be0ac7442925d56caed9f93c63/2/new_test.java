    @Test
    public void test_getQuality() {
        ValueMap qualityTransforms = new ValueMapDecorator(new HashMap<String, Object>());

        qualityTransforms.put("quality", 0);
        assertEquals(0D, servlet.getQuality("image/jpg", qualityTransforms), 0);

        qualityTransforms.put("quality", 100);
        assertEquals(1D, servlet.getQuality("image/jpg", qualityTransforms), 0);

        qualityTransforms.put("quality", 50);
        assertEquals(0.5D, servlet.getQuality("image/jpg", qualityTransforms), 0);

        qualityTransforms.put("quality", 101);
        assertEquals(.82D, servlet.getQuality("image/jpg", qualityTransforms), 0);

        qualityTransforms.put("quality", -1);
        assertEquals(.82D, servlet.getQuality("image/jpg", qualityTransforms), 0);

        /* Gifs */
        
        qualityTransforms.put("quality", 0);
        assertEquals(0D, servlet.getQuality("image/gif", qualityTransforms), 0);

        qualityTransforms.put("quality", 100);
        assertEquals(255D, servlet.getQuality("image/gif", qualityTransforms), 0);

        qualityTransforms.put("quality", 50);
        assertEquals(127.5D, servlet.getQuality("image/gif", qualityTransforms), 0);

        qualityTransforms.put("quality", 101);
        assertEquals(209.1D, servlet.getQuality("image/gif", qualityTransforms), 0);

        qualityTransforms.put("quality", -1);
        assertEquals(209.1D, servlet.getQuality("image/gif", qualityTransforms), 0);

    }