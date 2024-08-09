public static String create(List<String> pathSegments) {
        try {
            return create(pathSegments, java.net.InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException e) {
            return create(pathSegments, "localhost");
        }
    }