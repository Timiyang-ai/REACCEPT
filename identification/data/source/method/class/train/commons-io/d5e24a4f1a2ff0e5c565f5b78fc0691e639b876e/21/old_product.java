public static int copy(
            final InputStream input,
            final OutputStream output,
            final int bufferSize)
                throws IOException {
        final byte[] buffer = new byte[bufferSize];
        int count = 0;
        int n = 0;
        while (-1 != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
            count += n;
        }
        return count;
    }