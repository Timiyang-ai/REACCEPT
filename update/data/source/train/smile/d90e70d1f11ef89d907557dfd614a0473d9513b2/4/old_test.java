@Test
    public void testPredict() {
        System.out.println("predict");
        HMM hmm = new HMM(pi, a, b);
        int[] o = {0, 0, 1, 1, 0, 1, 1, 0};
        int[] s = {0, 0, 0, 0, 0, 0, 0, 0};
        int[] result = hmm.predict(o);
        assertEquals(o.length, result.length);
        for (int i = 0; i < s.length; i++) {
            assertEquals(s[i], result[i]);
        }
    }