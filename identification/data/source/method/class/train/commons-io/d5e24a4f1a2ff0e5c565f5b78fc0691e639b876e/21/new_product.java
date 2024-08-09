public static int copy(
            InputStream input,
            OutputStream output,
            int bufferSize)
                throws IOException {
        byte[] buffer = new byte[bufferSize];
        int count = 0;
        int n = 0;
        while (-1 != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
            count += n;
        }
        return count;
    }