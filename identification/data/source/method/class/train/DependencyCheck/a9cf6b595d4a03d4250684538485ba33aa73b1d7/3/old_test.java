@Test
    public void testOpen() {
        System.out.println("open");
        Index instance = new Index();
        try {
            instance.open();
        } catch (IOException ex) {
            fail(ex.getMessage());
        }
        try {
            instance.close();
        } catch (CorruptIndexException ex) {
            fail(ex.getMessage());
        } catch (IOException ex) {
            fail(ex.getMessage());
        }
    }