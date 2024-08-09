    @Test
    public void resetAndTruncateTest()
    {
        File tempFile = new File(Files.createTempDir(), "reset.txt");
        final int bufferSize = 48;
        final int writeSize = 64;
        byte[] toWrite = new byte[writeSize];
        SequentialWriterOption option = SequentialWriterOption.newBuilder().bufferSize(bufferSize).build();
        try (SequentialWriter writer = new SequentialWriter(tempFile, option))
        {
            // write bytes greather than buffer
            writer.write(toWrite);
            assertEquals(bufferSize, writer.getLastFlushOffset());
            assertEquals(writeSize, writer.position());
            // mark thi position
            DataPosition pos = writer.mark();
            // write another
            writer.write(toWrite);
            // another buffer should be flushed
            assertEquals(bufferSize * 2, writer.getLastFlushOffset());
            assertEquals(writeSize * 2, writer.position());
            // reset writer
            writer.resetAndTruncate(pos);
            // current position and flushed size should be changed
            assertEquals(writeSize, writer.position());
            assertEquals(writeSize, writer.getLastFlushOffset());
            // write another byte less than buffer
            writer.write(new byte[]{0});
            assertEquals(writeSize + 1, writer.position());
            // flush off set should not be increase
            assertEquals(writeSize, writer.getLastFlushOffset());
            writer.finish();
        }
        catch (IOException e)
        {
            Assert.fail();
        }
        // final file size check
        assertEquals(writeSize + 1, tempFile.length());
    }