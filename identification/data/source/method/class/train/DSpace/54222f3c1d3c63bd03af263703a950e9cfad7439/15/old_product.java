public void register()
    {
        // Create the database entry
        Timestamp now = new Timestamp(started.getTime());
        try {
            Context context = new Context();
            row = DatabaseManager.create(context, "Webapp");
            row.setColumn("AppName", kind);
            row.setColumn("URL", url);
            row.setColumn("Started", now);
            row.setColumn("isUI", isUI() ? 1 : 0); // update won't widen boolean to integer
            DatabaseManager.update(context, row);
            context.complete();
        } catch (SQLException e) {
            log.error("Failed to record startup in Webapp table.", e);
        }
    }