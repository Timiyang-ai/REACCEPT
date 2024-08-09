    @Ignore
    @Test
    public void printWallets() throws Exception {

        System.out.println("\n\n");
        callMain("-f", "/home/flint/w/uniclient-test", "--verbose");
        System.out.println(output);
        System.out.println("\n\n");
    }