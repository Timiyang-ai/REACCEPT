    private void writeTestPayload(final FileWriter fw1, final FileWriterWithEncoding fw2) throws IOException {
        assertTrue(file1.exists());
        assertTrue(file2.exists());

        fw1.write(textContent);
        fw2.write(textContent);
        fw1.write(65);
        fw2.write(65);
        fw1.write(anotherTestContent);
        fw2.write(anotherTestContent);
        fw1.write(anotherTestContent, 1, 2);
        fw2.write(anotherTestContent, 1, 2);
        fw1.write("CAFE", 1, 2);
        fw2.write("CAFE", 1, 2);

        fw1.flush();
        fw2.flush();
    }