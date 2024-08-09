public void register(DiagnosticsPlugin plugin) {
        checkNotNull(plugin, "plugin can't be null");

        if (!enabled) {
            return;
        }

        long periodMillis = plugin.getPeriodMillis();
        if (periodMillis < -1) {
            throw new IllegalArgumentException(plugin + " can't return a periodMillis smaller than -1");
        }

        logger.finest(plugin.getClass().toString() + " is " + (periodMillis == DISABLED ? "disabled" : "enabled"));

        if (periodMillis == DISABLED) {
            return;
        }

        plugin.onStart();

        if (periodMillis > 0) {
            // it is a periodic task
            scheduler.scheduleAtFixedRate(new WritePluginTask(plugin), 0, periodMillis, MILLISECONDS);
        } else {
            addStaticPlugin(plugin);
        }
    }