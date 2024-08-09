    public void test_merge() {
        BitIntSet setA = new BitIntSet(32);
        int[] valuesA = {0, 1, 31};

        for (int i = 0; i < valuesA.length; i++) {
            setA.add(valuesA[i]);
        }

        BitIntSet setB = new BitIntSet(32);
        int[] valuesB = {0, 5, 6, 8, 31};

        for (int i = 0; i < valuesB.length; i++) {
            setB.add(valuesB[i]);
        }

        setA.merge(setB);

        for (int i = 0; i < valuesA.length; i++) {
            assertTrue(setA.has(valuesA[i]));
        }

        for (int i = 0; i < valuesB.length; i++) {
            assertTrue(setA.has(valuesB[i]));
        }
    }