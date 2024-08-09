public static String getHostname() {
        play.Configuration config = play.Configuration.root();

        if (config != null) {
            String hostname = play.Configuration.root().getString("application.hostname");
            if (hostname != null && !hostname.isEmpty()) {
                return hostname;
            }
        }

        try {
            return getDefaultAddress().getHostName();
        } catch (Exception e) {
            play.Logger.warn("Failed to get the hostname", e);
            return "localhost";
        }
    }