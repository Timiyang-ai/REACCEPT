public SearchQuery build(final String query, final boolean envCondition) {
        String q;
        if (envCondition && additionalQuery != null && StringUtil.isNotBlank(query)) {
            q = query + " " + additionalQuery;
        } else {
            q = query;
        }

        final SearchQuery searchQuery = buildQuery(q);
        if (!searchQuery.queryExists()) {
            searchQuery.query(StringUtil.EMPTY);
        }

        if (!envCondition) {
            return searchQuery;
        }

        if (keyMatchHelper != null) {
            final List<String> docIdQueryList = keyMatchHelper.getDocIdQueryList();
            if (docIdQueryList != null && searchQuery.queryExists()) {
                final String originalQuery = searchQuery.getQuery();
                final StringBuilder queryBuf = new StringBuilder(originalQuery.length() + 100);
                queryBuf.append(originalQuery);
                for (final String docIdQuery : docIdQueryList) {
                    queryBuf.append(_OR_);
                    queryBuf.append(docIdQuery);
                }
                searchQuery.setQuery(queryBuf.toString());
            }
        }

        if (roleQueryHelper != null) {
            final Set<String> roleSet = roleQueryHelper.build();
            if (roleSet.size() > maxFilterQueriesForRole) {
                // add query
                final String sq = searchQuery.getQuery();
                final StringBuilder queryBuf = new StringBuilder(255);
                final boolean hasQueries = sq.contains(_AND_) || sq.contains(_OR_);
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
                searchQuery.query(queryBuf.toString());
            } else if (!roleSet.isEmpty()) {
                // add filter query
                searchQuery.addFilterQuery(getRoleQuery(roleSet));
            }
        }
        return searchQuery;
    }