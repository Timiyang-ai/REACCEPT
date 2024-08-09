    @Test(expected = NullPointerException.class)
    public void add_whenNull() throws InterruptedException {
        Pipelining<String> pipelining = new Pipelining<String>(1);
        pipelining.add(null);
    }