public QueryContext build(final String query, final Consumer<QueryContext> context) {
        String q;
        if (additionalQuery != null && StringUtil.isNotBlank(query)) {
            q = query + " " + additionalQuery;
        } else {
            q = query;
        }

        final QueryContext queryContext = buildBaseQuery(q, context);

        if (keyMatchHelper != null) {
            final List<String> docIdQueryList = keyMatchHelper.getDocIdQueryList();
            if (docIdQueryList != null && !docIdQueryList.isEmpty()) {
                queryContext.addQuery(boolQuery -> {
                    for (final String docIdQuery : docIdQueryList) {
                        // TODO id query?
                        boolQuery.should(QueryBuilders.queryStringQuery(docIdQuery));
                    }
                });
            }
        }

        if (roleQueryHelper != null) {
            final Set<String> roleSet = roleQueryHelper.build();
            if (!roleSet.isEmpty()) {
                final FilterBuilder filterBuilder =
                        FilterBuilders.orFilter(roleSet.stream().map(name -> FilterBuilders.termFilter(fieldHelper.roleField, name))
                                .toArray(n -> new FilterBuilder[n]));
                queryContext.addFilter(filterBuilder);
            }
        }

        if (!queryContext.hasSorts() && defaultSortBuilders != null) {
            queryContext.addSorts(defaultSortBuilders);
        }
        return queryContext;
    }