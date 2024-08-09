@Test
    public void testRank() {
        System.out.println("rank");
        SumSquaresRatio ssr = new SumSquaresRatio();
        double[] ratio = ssr.apply(Iris.x, Iris.y);
        assertEquals(4, ratio.length);
        assertEquals( 1.6226463, ratio[0], 1E-6);
        assertEquals( 0.6444144, ratio[1], 1E-6);
        assertEquals(16.0412833, ratio[2], 1E-6);
        assertEquals(13.0520327, ratio[3], 1E-6);
    }