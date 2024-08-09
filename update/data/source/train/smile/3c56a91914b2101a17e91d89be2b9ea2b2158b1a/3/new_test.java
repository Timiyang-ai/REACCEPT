@Test
    public void testColSd() {
        System.out.println("colSd");
        double[][] data = {
            {1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0},
            {1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0},
            {1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0},
        };

        data = Math.transpose(data);

        assertEquals(2.73861, Math.colSds(data)[0], 1E-5);
        assertEquals(2.73861, Math.colSds(data)[1], 1E-5);
        assertEquals(2.73861, Math.colSds(data)[2], 1E-5);
    }