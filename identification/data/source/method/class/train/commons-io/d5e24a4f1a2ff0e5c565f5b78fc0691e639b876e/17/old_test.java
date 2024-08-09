    @Test
    public void copy_byteArrayToOutputStream() throws Exception {
        final ByteArrayOutputStream baout = new ByteArrayOutputStream();
        final OutputStream out = new YellOnFlushAndCloseOutputStream(baout, false, true);

        CopyUtils.copy(inData, out);

        assertEquals(inData.length, baout.size(), "Sizes differ");
        assertTrue(Arrays.equals(inData, baout.toByteArray()), "Content differs");
    }