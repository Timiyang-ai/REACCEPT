public void init(final JSONObject requestJSONObject) throws ServiceException {
        if (SoloServletListener.isInited()) {
            return;
        }

        final RuntimeEnv runtimeEnv = Latkes.getRuntimeEnv();

        if (RuntimeEnv.LOCAL == runtimeEnv || RuntimeEnv.BAE == runtimeEnv) {
            LOGGER.log(Level.INFO, "B3log Solo is running on [" + runtimeEnv + "] environment, database [{0}], creates all tables",
                Latkes.getRuntimeDatabase());
            final List<CreateTableResult> createTableResults = JdbcRepositories.initAllTables();

            for (final CreateTableResult createTableResult : createTableResults) {
                LOGGER.log(Level.INFO, "Create table result[tableName={0}, isSuccess={1}]",
                    new Object[] {createTableResult.getName(), createTableResult.isSuccess()});
            }
        }

        int retries = MAX_RETRIES_CNT;

        while (true) {
            final Transaction transaction = userRepository.beginTransaction();

            try {
                final JSONObject statistic = statisticRepository.get(Statistic.STATISTIC);

                if (null == statistic) {
                    initStatistic();
                    initPreference(requestJSONObject);
                    initReplyNotificationTemplate();
                    initAdmin(requestJSONObject);
                }

                transaction.commit();
                break;
            } catch (final Exception e) {
                if (0 == retries) {
                    LOGGER.log(Level.SEVERE, "Initialize B3log Solo error", e);
                    throw new ServiceException("Initailize B3log Solo error: " + e.getMessage());
                }

                // Allow retry to occur
                --retries;
                LOGGER.log(Level.WARNING, "Retrying to init B3log Solo[retries={0}]", retries);
            } finally {
                if (transaction.isActive()) {
                    transaction.rollback();
                }
            }
        }

        final Transaction transaction = userRepository.beginTransaction();

        try {
            helloWorld();
            transaction.commit();
        } catch (final Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }

            LOGGER.log(Level.SEVERE, "Hello World error?!", e);
        }
    }