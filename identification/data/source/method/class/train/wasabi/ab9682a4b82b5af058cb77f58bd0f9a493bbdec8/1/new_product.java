@Override
    public List<Map> getActionsRows(Experiment.ID experimentID, Parameters parameters)
            throws RepositoryException {

        Transaction transaction = transactionFactory.newTransaction();
        try {
            //build and execute SQL queries for counts
            Date from_ts = parameters.getFromTime();
            Date to_ts = parameters.getToTime();
            String sqlBase = "bucket_label as bid, count(user_id) as c, count(distinct user_id) as cu";
            StringBuilder sqlParams = new StringBuilder(" where experiment_id = ? and context = ?");
            List params = new ArrayList();
            params.add(experimentID);
            params.add(parameters.getContext().getContext());


            if (from_ts != null) {
                params.add(from_ts);
                sqlParams.append(" and timestamp >= ?");
            }

            if (to_ts != null) {
                params.add(to_ts);
                sqlParams.append(" and timestamp <= ?");
            }

            addActionsToSql(parameters, sqlParams, params);

            Object[] bucketSqlData = new Object[params.size()];
            params.toArray(bucketSqlData);

            String sqlActions = "select action, " + sqlBase + " from event_action" +
                    sqlParams.toString() + " group by bucket_label, action";
            List<Map> actionsRows = transaction.select(sqlActions, bucketSqlData);


            return actionsRows;

        } catch (Exception e) {
            throw new RepositoryException("error reading actions rows from MySQL", e);
        }
    }