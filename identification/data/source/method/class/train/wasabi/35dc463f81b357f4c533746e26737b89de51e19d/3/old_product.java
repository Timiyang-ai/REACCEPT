@Override
    public List<Map> getCountsFromRollups(Experiment.ID experimentID, Parameters parameters)
            throws RepositoryException {

        try {

            //build and execute SQL queries for counts from rollups
            String sqlQuery = "select day, bucket_label as bid, cumulative as c, action, impression_count as ic, " +
                    "impression_user_count as iuc, action_count as ac, action_user_count as auc " +
                    "from experiment_rollup where experiment_id = ? and context = ? order by day asc";

            return transaction.select(sqlQuery, experimentID, parameters.getContext().getContext());

        } catch (Exception e) {
            throw new RepositoryException("error reading counts from MySQL rollups", e);
        }
    }