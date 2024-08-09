@Test
    public void testNearest() {
        System.out.println("nearest");
        for (int i = 0; i < data.length; i++) {
            Neighbor n1 = coverTree.nearest(data[i]);
            Neighbor n2 = naive.nearest(data[i]);
            assertEquals(n1.index, n2.index);
            assertEquals(n1.value, n2.value);
            assertEquals(n1.distance, n2.distance, 1E-7);
        }
    }