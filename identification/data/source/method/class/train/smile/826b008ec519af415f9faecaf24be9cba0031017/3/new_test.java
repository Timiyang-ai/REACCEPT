@Test
    public void testMutate() {
        System.out.println("mutate");

        MathEx.setSeed(19650218); // to get repeatable results.

        byte[] father = {1,1,1,0,1,0,0,1,0,0,0};
        BitString instance = new BitString(father.clone(), null, Crossover.SINGLE_POINT, 1.0, 0.1);
        instance.mutate();

        byte[] mutant = {1,1,1,1,1,0,0,1,0,1,0};
        for (int i = 0; i < father.length; i++) {
            assertEquals(mutant[i], instance.bits()[i]);
        }
    }