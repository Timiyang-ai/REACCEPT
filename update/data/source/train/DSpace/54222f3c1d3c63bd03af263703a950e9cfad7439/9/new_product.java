public void register()
    {
        // Create the database entry
        Timestamp now = new Timestamp(started.getTime());
        try {
            Context context = new Context();
            webApp = webAppService.create(context, kind, url, now, isUI() ? 1 : 0);
            context.complete();
        } catch (SQLException e) {
            log.error("Failed to record startup in Webapp table.", e);
        }
    }