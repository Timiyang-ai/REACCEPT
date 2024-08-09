public static int copy(
            final Reader input,
            final Writer output,
            final int bufferSize)
                throws IOException {
        final char[] buffer = new char[bufferSize];
        int count = 0;
        int n = 0;
        while (-1 != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
            count += n;
        }
        return count;
    }