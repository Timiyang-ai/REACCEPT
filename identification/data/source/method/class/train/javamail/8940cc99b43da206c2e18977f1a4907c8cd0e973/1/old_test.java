    private static void close(Socket s) {
        try {
            s.close();
        } catch (IOException ignore) {
        }
    }