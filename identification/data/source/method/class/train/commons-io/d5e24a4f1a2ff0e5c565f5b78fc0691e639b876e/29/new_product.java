@Deprecated
    public static void copy(
            final Reader input,
            final OutputStream output)
                throws IOException {
        // make explicit the dependency on the default encoding
        final OutputStreamWriter out = new OutputStreamWriter(output, Charset.defaultCharset());
        copy(input, out);
        // XXX Unless anyone is planning on rewriting OutputStreamWriter, we
        // have to flush here.
        out.flush();
    }