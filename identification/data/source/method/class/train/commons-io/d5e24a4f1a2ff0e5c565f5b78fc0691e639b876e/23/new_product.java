public static void copy(
            Reader input,
            OutputStream output,
            int bufferSize)
                throws IOException {
        OutputStreamWriter out = new OutputStreamWriter(output);
        copy(input, out, bufferSize);
        // XXX Unless anyone is planning on rewriting OutputStreamWriter, we have to flush here.
        out.flush();
    }