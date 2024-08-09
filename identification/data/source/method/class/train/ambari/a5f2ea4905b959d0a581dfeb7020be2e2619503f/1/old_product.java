public UpgradeSummary getUpgradeSummary() {
    UpgradeSummary summary = new UpgradeSummary();
    summary.direction = m_direction;
    summary.type = m_type;
    summary.orchestration = m_orchestration;
    summary.isRevert = m_isRevert;

    summary.services = new HashMap<>();

    for (String serviceName : m_services) {
      RepositoryVersionEntity sourceRepositoryVersion = m_sourceRepositoryMap.get(serviceName);
      RepositoryVersionEntity targetRepositoryVersion = m_targetRepositoryMap.get(serviceName);
      if (null == sourceRepositoryVersion || null == targetRepositoryVersion) {
        LOG.warn("Unable to get the source/target repositories for {} for the upgrade summary",
            serviceName);
        continue;
      }

      UpgradeServiceSummary serviceSummary = new UpgradeServiceSummary();
      serviceSummary.sourceRepositoryId = sourceRepositoryVersion.getId();
      serviceSummary.sourceStackId = sourceRepositoryVersion.getStackId().getStackId();
      serviceSummary.sourceVersion = sourceRepositoryVersion.getVersion();

      serviceSummary.targetRepositoryId = targetRepositoryVersion.getId();
      serviceSummary.targetStackId = targetRepositoryVersion.getStackId().getStackId();
      serviceSummary.targetVersion = targetRepositoryVersion.getVersion();

      summary.services.put(serviceName, serviceSummary);
    }

    return summary;
  }