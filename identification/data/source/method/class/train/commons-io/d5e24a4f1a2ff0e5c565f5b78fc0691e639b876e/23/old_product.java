public static void copy(
            final Reader input,
            final OutputStream output,
            final int bufferSize)
                throws IOException {
        final OutputStreamWriter out = new OutputStreamWriter(output);
        copy(input, out, bufferSize);
        // XXX Unless anyone is planning on rewriting OutputStreamWriter, we have to flush here.
        out.flush();
    }