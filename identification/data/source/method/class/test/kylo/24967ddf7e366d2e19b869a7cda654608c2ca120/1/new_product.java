@Override
    public Page<? extends BatchJobExecution> findAll(String filter, Pageable pageable) {
        QJpaBatchJobExecution jobExecution = QJpaBatchJobExecution.jpaBatchJobExecution;
        //if the filter contains a filter on the feed then delegate to the findAllForFeed method to include any check data jobs
        List<SearchCriteria> searchCriterias = GenericQueryDslFilter.parseFilterString(filter);
        SearchCriteria feedFilter = searchCriterias.stream().map(searchCriteria -> searchCriteria.withKey(CommonFilterTranslations.resolvedFilter(jobExecution, searchCriteria.getKey()))).filter(
            sc -> sc.getKey().equalsIgnoreCase(CommonFilterTranslations.jobExecutionFeedNameFilterKey)).findFirst().orElse(null);
        if (feedFilter != null && feedFilter.getPreviousSearchCriteria() != null && !feedFilter.isValueCollection()) {
            //remove the feed filter from the list and filter by this feed
            searchCriterias.remove(feedFilter.getPreviousSearchCriteria());
            String feedValue = feedFilter.getValue().toString();
            //remove any quotes around the feedValue
            feedValue = feedValue.replaceAll("^\"|\"$", "");
            return findAllForFeed(feedValue, searchCriterias, pageable);
        } else {
            pageable = CommonFilterTranslations.resolveSortFilters(jobExecution, pageable);
            QJpaBatchJobInstance jobInstancePath = new QJpaBatchJobInstance("jobInstance");
            QJpaOpsManagerFeed feedPath = new QJpaOpsManagerFeed("feed");

            return findAllWithFetch(jobExecution,
                                    GenericQueryDslFilter.buildFilter(jobExecution, filter).and(augment(feedPath.id)),
                                    pageable,
                                    QueryDslFetchJoin.innerJoin(jobExecution.nifiEventJobExecution),
                                    QueryDslFetchJoin.innerJoin(jobExecution.jobInstance, jobInstancePath),
                                    QueryDslFetchJoin.innerJoin(jobInstancePath.feed, feedPath)
            );
        }

    }