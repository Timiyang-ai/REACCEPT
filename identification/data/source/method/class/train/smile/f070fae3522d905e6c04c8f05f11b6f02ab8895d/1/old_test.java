@Test
    public void testRange() {
        System.out.println("range");
        List<Neighbor<double[], double[]>> n1 = new ArrayList<>();
        List<Neighbor<double[], double[]>> n2 = new ArrayList<>();
        for (int i = 0; i < data.length; i++) {
            coverTree.range(data[i], 0.5, n1);
            naive.range(data[i], 0.5, n2);
            Collections.sort(n1);
            Collections.sort(n2);
            assertEquals(n1.size(), n2.size());
            for (int j = 0; j < n1.size(); j++) {
                assertEquals(n1.get(j).index, n2.get(j).index);
                assertEquals(n1.get(j).value, n2.get(j).value);
                assertEquals(n1.get(j).distance, n2.get(j).distance, 1E-7);
            }
            n1.clear();
            n2.clear();
        }
    }