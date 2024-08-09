public static void copy(
            final Reader input,
            final OutputStream output)
                throws IOException {
        final OutputStreamWriter out = new OutputStreamWriter(output);
        copy(input, out);
        // XXX Unless anyone is planning on rewriting OutputStreamWriter, we
        // have to flush here.
        out.flush();
    }