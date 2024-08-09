public SearchQuery build(final String query, final boolean envCondition) {
        String q;
        if (envCondition && additionalQuery != null
                && StringUtil.isNotBlank(query)) {
            q = query + " " + additionalQuery;
        } else {
            q = query;
        }

        final SearchQuery searchQuery = buildQuery(q);
        if (!searchQuery.queryExists()) {
            return searchQuery.query(StringUtil.EMPTY);
        }

        if (roleQueryHelper == null || !envCondition) {
            return searchQuery;
        }

        StringBuilder queryBuf = new StringBuilder(255);
        queryBuf.append(searchQuery.getQuery());

        if (roleQueryHelper != null) {
            final Set<String> roleSet = roleQueryHelper.build();
            if (roleSet.size() > maxFilterQueriesForRole) {
                // add query
                final String sq = queryBuf.toString();
                queryBuf = new StringBuilder(255);
                final boolean hasQueries = sq.contains(_AND_)
                        || sq.contains(_OR_);
                if (hasQueries) {
                    queryBuf.append('(');
                }
                queryBuf.append(sq);
                if (hasQueries) {
                    queryBuf.append(')');
                }
                queryBuf.append(_AND_);
                if (roleSet.size() > 1) {
                    queryBuf.append('(');
                }
                queryBuf.append(getRoleQuery(roleSet));
                if (roleSet.size() > 1) {
                    queryBuf.append(')');
                }
            } else if (!roleSet.isEmpty()) {
                // add filter query
                searchQuery.addFilterQuery(getRoleQuery(roleSet));
            }
        }

        return searchQuery.query(queryBuf.toString());
    }