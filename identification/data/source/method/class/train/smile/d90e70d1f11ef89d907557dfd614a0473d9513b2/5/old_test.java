@Test
    public void testLogp_intArr2() {
        System.out.println("logp");
        String[] symbols = {"0", "1"};
        HMM<String> hmm = new HMM<>(pi, a, b, symbols);

        String[] o = {"0", "0", "1", "1", "0", "1", "1", "0"};
        double expResult = -5.609373;
        double result = hmm.logp(o);
        assertEquals(expResult, result, 1E-6);
    }