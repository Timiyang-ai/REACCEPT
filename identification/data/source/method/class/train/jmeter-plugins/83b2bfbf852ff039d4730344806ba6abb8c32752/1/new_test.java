@Test
    public void testMain() {
        System.out.println("main");
        String[] args = "--help".split(" ");
        NewDriver.main(args);
    }