    @Test
    public void position() throws Exception {
        {
            int pos0 = whole.position();
            whole.skip(1);
            int pos1 = whole.position();
            whole.skip(6);
            int pos2 = whole.position();
            assertEquals(0, pos0);
            assertEquals(1, pos1);
            assertEquals(6, pos2);
        }
        {
            int pos0 = middle.position();
            middle.skip(1);
            int pos1 = middle.position();
            middle.skip(6);
            int pos2 = middle.position();
            assertEquals(0, pos0);
            assertEquals(1, pos1);
            assertEquals(4, pos2);
        }
    }