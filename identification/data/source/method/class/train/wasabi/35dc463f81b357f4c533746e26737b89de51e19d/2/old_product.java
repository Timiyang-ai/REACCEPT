@Override
    public List<Map> getRollupRows(Experiment.ID experimentId, String rollupDate, Parameters parameters)
            throws RepositoryException {

        // TODO enable direct mapping of DateMidnight
        List rollupRows;
        try {
            //build and execute SQL queries for counts from rollups
            String sqlQuery = "select bucket_label as bid, action, impression_count as ic, impression_user_count as iuc, " +
                    "action_count as ac, action_user_count as auc from experiment_rollup " +
                    "where experiment_id = ? and cumulative = ? and day = ? and context = ?";

            rollupRows = transaction.select(sqlQuery, experimentId, true,
                    rollupDate, parameters.getContext().getContext());
            return rollupRows;

        } catch (Exception e) {
            throw new RepositoryException("error reading rollup rows from MySQL", e);
        }

    }