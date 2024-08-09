@Test
    public void testGetNextColor() {
        System.out.println("getNextColor");
        ColorsDispatcher instance = new ColorsDispatcher();
        for (int n = 0; n < 2000; n++) {
            Color c = instance.getNextColor();
            System.out.println(c);
            assertNotNull(c);
        }
    }