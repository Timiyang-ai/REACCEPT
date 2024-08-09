@Override
    public void applyDelegate(Config config) {
        if (port != null) {
            config.setPort(port);
        }
        config.setConnectionEndType(ConnectionEndType.SERVER);
    }