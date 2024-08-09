@Test
    public void testMutate() {
        System.out.println("mutate");
        int[] father = {1,1,1,0,1,0,0,1,0,0,0};
        BitString instance = new BitString(father.clone(), null, BitString.Crossover.SINGLE_POINT, 1.0, 1.0);
        instance.mutate();
        assertEquals(father.length, instance.length);
        assertEquals(father.length, instance.bits().length);
        boolean same = true;
        for (int i = 0; i < father.length; i++) {
            if (father[i] != instance.bits()[i]) {
                same = false;
            }
        }
        assertFalse(same);
    }