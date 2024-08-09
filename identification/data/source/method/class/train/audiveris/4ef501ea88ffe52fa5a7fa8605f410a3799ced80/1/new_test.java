@Test
    public void testProcess ()
    {
        System.out.println("process");

        ByteProcessor image = createImage();
        TableUtil.dump("input:", image);

        Table dists = new ChamferDistance.Short().computeToBack(image);
        TableUtil.dump("Dists:", dists);

        WatershedGrayLevel instance = new WatershedGrayLevel(dists, true);
        int step = 1;
        boolean[][] result = instance.process(step);
        TableUtil.dump("watershed:", result);

        // Apply watershed line on initial image
        merge(image, result);
        TableUtil.dump("regions", image);
    }