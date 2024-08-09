public void reloadConfig(ApplicationSet applicationSet) {
        setLiveApp(applicationSet);
        notifyReloadListeners(applicationSet);
    }