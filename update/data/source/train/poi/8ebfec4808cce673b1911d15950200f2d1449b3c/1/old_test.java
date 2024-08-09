@Test
    public void testWrite1() throws IOException {
        ByteArrayOutputStream stream  = new ByteArrayOutputStream();
        DocumentOutputStream  dstream = new DocumentOutputStream(stream, 25);

        for (int j = 0; j < 25; j++)
        {
            dstream.write(j);
        }
        try
        {
            dstream.write(0);
            fail("Should have caught IOException");
        }
        catch (IOException ignored)
        {
        }
        byte[] output = stream.toByteArray();

        assertEquals(25, output.length);
        for (int j = 0; j < 25; j++)
        {
            assertEquals(( byte ) j, output[ j ]);
        }
        dstream.close();
        stream.close();
    }