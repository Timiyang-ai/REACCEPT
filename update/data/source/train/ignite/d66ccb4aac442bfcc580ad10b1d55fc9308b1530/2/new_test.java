@Test
    public void testApply() {
        String[][] data = new String[][]{
            {"1", "Moscow", "A"},
            {"2", "Moscow", "B"},
            {"2", "Moscow", "B"},
        };

        StringEncoderPreprocessor<Integer, String[]> preprocessor = new StringEncoderPreprocessor<Integer, String[]>(
            new HashMap[]{new HashMap() {
                {
                    put("1", 1);
                    put("2", 0);
                }
            }, new HashMap() {
                {
                    put("Moscow", 0);
                }
            }, new HashMap() {
                {
                    put("A", 1);
                    put("B", 0);
                }
            }},
            (k, v) -> v,
            new HashSet() {
                {
                    add(0);
                    add(1);
                    add(2);
                }
            });

        double[][] postProcessedData = new double[][]{
            {1.0, 0.0, 1.0},
            {0.0, 0.0, 0.0},
            {0.0, 0.0, 0.0},
        };

       for (int i = 0; i < data.length; i++)
           assertArrayEquals(postProcessedData[i], preprocessor.apply(i, data[i]), 1e-8);
    }