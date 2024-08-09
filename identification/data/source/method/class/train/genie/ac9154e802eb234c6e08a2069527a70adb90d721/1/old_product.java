@SuppressWarnings("unchecked")
    public T[] query(QueryBuilder builder) {
        LOG.debug("called");
        EntityManager em = createEntityManager();
        try {
            String strQuery;
            String table = builder.getTable();
            String criteria = builder.getClause();
            strQuery = ((criteria == null) || criteria.isEmpty()) ? String
                    .format("select %s from %s %s", ENTITY_ALIAS, table,
                            ENTITY_ALIAS) : String.format(
                            "select %s from %s %s where %s", ENTITY_ALIAS, table,
                            ENTITY_ALIAS, criteria);

            // order by update time, if need be
            boolean orderByUpdateTime = builder.isOrderByUpdateTime();
            if (orderByUpdateTime) {
                boolean desc = builder.isDesc();
                if (desc) {
                    strQuery += String.format(" order by %s.updateTime desc",
                            ENTITY_ALIAS);
                } else {
                    strQuery += String.format(" order by %s.updateTime asc",
                            ENTITY_ALIAS);
                }
            }

            Query q = em.createQuery(strQuery);
            LOG.debug("Query string: " + strQuery);

            // set max results to default, or requested limit (capped by MAX)
            // for paginated results, limit is per page
            Integer limit = builder.getLimit();
            if ((limit == null) || (limit > MAX_PAGE_SIZE)) {
                limit = MAX_PAGE_SIZE;
            }
            boolean paginate = builder.isPaginate();
            if (paginate) {
                q.setMaxResults(limit);
            }

            // enable pagination, if requested
            Integer page = builder.getPage();
            if (paginate && (page != null)) {
                int startPos = getStartPosition(page, limit);
                q.setFirstResult(startPos);
            }

            List<T> records = (List<T>) q.getResultList();
            T[] results = (T[]) new Object[records.size()];
            results = (T[]) records.toArray(results);
            return results;
        } finally {
            em.close();
        }
    }