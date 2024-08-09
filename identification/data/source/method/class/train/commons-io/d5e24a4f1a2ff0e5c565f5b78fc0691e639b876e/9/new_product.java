public static void copy(
            byte[] input,
            OutputStream output,
            int bufferSize)
                throws IOException {
        // TODO Is bufferSize param needed?
        output.write(input);
    }