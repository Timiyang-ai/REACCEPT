@Override
  public void run() {
    Set<String> tldsOfInterest = getTlds();

    List<TaskHandle> tasks = dnsQueue.leaseTasks(writeLockTimeout);
    if (tasks.isEmpty()) {
      return;
    }
    logger.infofmt("leased %d tasks", tasks.size());
    // Normally, all tasks will be deleted from the pull queue. But some might have to remain if
    // we are not interested in the associated TLD, or if the TLD is paused. Remember which these
    // are.
    Set<TaskHandle> tasksToKeep = new HashSet<>();
    // The paused TLDs for which we found at least one refresh request.
    Set<String> pausedTlds = new HashSet<>();
    // Create a sorted multimap into which we will insert the refresh items, so that the items for
    // each TLD will be grouped together, and domains and hosts will be grouped within a TLD. The
    // grouping and ordering of domains and hosts is not technically necessary, but a predictable
    // ordering makes it possible to write detailed tests.
    SortedSetMultimap<String, RefreshItem> refreshItemMultimap = TreeMultimap.create();
    // Read all tasks on the DNS pull queue and load them into the refresh item multimap.
    for (TaskHandle task : tasks) {
      try {
        Map<String, String> params = ImmutableMap.copyOf(task.extractParams());
        String tld = params.get(RequestParameters.PARAM_TLD);
        if (tld == null) {
          logger.severe("discarding invalid DNS refresh request; no TLD specified");
        } else if (!tldsOfInterest.contains(tld)) {
          tasksToKeep.add(task);
        } else if (Registry.get(tld).getDnsPaused()) {
          tasksToKeep.add(task);
          pausedTlds.add(tld);
        } else {
          String typeString = params.get(DNS_TARGET_TYPE_PARAM);
          String name = params.get(DNS_TARGET_NAME_PARAM);
          TargetType type = TargetType.valueOf(typeString);
          switch (type) {
            case DOMAIN:
            case HOST:
              refreshItemMultimap.put(tld, RefreshItem.create(type, name));
              break;
            default:
              logger.severefmt("discarding DNS refresh request of type %s", typeString);
              break;
          }
        }
      } catch (RuntimeException | UnsupportedEncodingException e) {
        logger.severefmt(e, "discarding invalid DNS refresh request (task %s)", task);
      }
    }
    if (!pausedTlds.isEmpty()) {
      logger.infofmt("the dns-pull queue is paused for tlds: %s", pausedTlds);
    }
    // Loop through the multimap by TLD and generate refresh tasks for the hosts and domains.
    for (Map.Entry<String, Collection<RefreshItem>> tldRefreshItemsEntry
        : refreshItemMultimap.asMap().entrySet()) {
      for (List<RefreshItem> chunk : Iterables.partition(
          tldRefreshItemsEntry.getValue(), tldUpdateBatchSize)) {
        TaskOptions options = withUrl(PublishDnsUpdatesAction.PATH)
            .countdownMillis(jitterSeconds.isPresent()
                ? random.nextInt((int) SECONDS.toMillis(jitterSeconds.get()))
                : 0)
            .param(RequestParameters.PARAM_TLD, tldRefreshItemsEntry.getKey());
        for (RefreshItem refreshItem : chunk) {
          options.param(
              (refreshItem.type() == TargetType.HOST)
                  ? PublishDnsUpdatesAction.HOSTS_PARAM
                  : PublishDnsUpdatesAction.DOMAINS_PARAM,
              refreshItem.name());
        }
        taskEnqueuer.enqueue(dnsPublishPushQueue, options);
      }
    }
    Set<TaskHandle> tasksToDelete = difference(ImmutableSet.copyOf(tasks), tasksToKeep);
    // In keepTasks mode, never delete any tasks.
    if (keepTasks) {
      logger.infofmt("would have deleted %d tasks", tasksToDelete.size());
      for (TaskHandle task : tasks) {
        dnsQueue.dropTaskLease(task);
      }
    // Otherwise, either delete or drop the lease of each task.
    } else {
      logger.infofmt("deleting %d tasks", tasksToDelete.size());
      dnsQueue.deleteTasks(ImmutableList.copyOf(tasksToDelete));
      logger.infofmt("dropping %d tasks", tasksToKeep.size());
      for (TaskHandle task : tasksToKeep) {
        dnsQueue.dropTaskLease(task);
      }
      logger.infofmt("done");
    }
  }