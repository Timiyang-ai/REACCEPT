public void open() throws IOException, DatabaseException {
        if (!isOpen()) {
            cve = new CveDB();
            cve.open();
            cpe = CpeMemoryIndex.getInstance();
            try {
                final long creationStart = System.currentTimeMillis();
                cpe.open(cve);
                final long creationSeconds = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - creationStart);
                LOGGER.info("Created CPE Index ({} seconds)", creationSeconds);
            } catch (IndexException ex) {
                LOGGER.debug("IndexException", ex);
                throw new DatabaseException(ex);
            }
        }
    }