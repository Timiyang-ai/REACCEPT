@Test
    public void testOpen() {
        System.out.println("open");
        Index instance = new Index();
        try {
            instance.open();
        } catch (IOException ex) {
            fail(ex.getMessage());
        }
        instance.close();
    }