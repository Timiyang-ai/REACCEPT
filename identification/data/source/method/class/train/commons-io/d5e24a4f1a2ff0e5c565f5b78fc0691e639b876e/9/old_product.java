public static void copy(
            final byte[] input,
            final OutputStream output,
            int bufferSize)
                throws IOException {
        // TODO Is bufferSize param needed?
        output.write(input);
    }