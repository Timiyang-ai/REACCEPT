@Override
    public void reloadConfig(ApplicationSet applicationSet) {
        ApplicationId id = applicationSet.getId();
        try (Lock lock = applications.lock(id)) {
            if ( ! applications.exists(id))
                return; // Application was deleted before activation.
            if (applicationSet.getApplicationGeneration() != applications.requireActiveSessionOf(id))
                return; // Application activated a new session before we got here.

            setLiveApp(applicationSet);
            notifyReloadListeners(applicationSet);
        }
    }