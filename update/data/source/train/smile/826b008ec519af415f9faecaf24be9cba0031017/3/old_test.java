@Test
    public void testNewInstance() {
        System.out.println("newInstance");
        int[] father = {1,1,1,0,1,0,0,1,0,0,0};
        BitString instance = new BitString(father, null, BitString.Crossover.SINGLE_POINT, 1.0, 0.0);
        BitString result = instance.newInstance();
        assertEquals(father.length, result.length);
        assertEquals(father.length, result.bits().length);
        boolean same = true;
        for (int i = 0; i < father.length; i++) {
            if (father[i] != result.bits()[i]) {
                same = false;
            }
        }
        assertFalse(same);
    }