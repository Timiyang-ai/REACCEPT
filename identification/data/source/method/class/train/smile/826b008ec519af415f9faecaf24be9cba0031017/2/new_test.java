@Test
    public void testNewInstance() {
        System.out.println("newInstance");

        byte[] father = {1,1,1,0,1,0,0,1,0,0,0};
        int length = father.length;
        BitString instance = new BitString(father, null, Crossover.SINGLE_POINT, 1.0, 0.0);
        BitString result = instance.newInstance();

        assertEquals(length, result.length());

        boolean same = true;
        for (int i = 0; i < length; i++) {
            if (father[i] != result.bits()[i]) {
                same = false;
            }
        }
        assertFalse(same);
    }