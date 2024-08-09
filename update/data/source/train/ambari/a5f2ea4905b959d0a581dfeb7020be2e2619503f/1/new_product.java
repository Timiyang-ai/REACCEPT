public UpgradeSummary getUpgradeSummary() {
    UpgradeSummary summary = new UpgradeSummary();
    summary.direction = m_direction;
    summary.isRevert = m_isRevert;
    summary.serviceGroups = new HashMap<>();

    for (ServiceGroup serviceGroup : m_serviceGroups.keySet()) {
      MpackChangeSummary changeSummary = m_serviceGroups.get(serviceGroup);

      UpgradeServiceGroupSummary serviceGroupSummary = new UpgradeServiceGroupSummary();
      serviceGroupSummary.type = m_type;
      serviceGroupSummary.sourceMpackId = changeSummary.getSource().getResourceId();
      serviceGroupSummary.sourceStack = changeSummary.getSource().getStackId().getStackId();
      serviceGroupSummary.targetMpackId = changeSummary.getTarget().getRegistryId();
      serviceGroupSummary.targetStack = changeSummary.getTarget().getStackId().getStackId();
      serviceGroupSummary.services = new LinkedHashMap<>();

      summary.serviceGroups.put(serviceGroup.getServiceGroupName(), serviceGroupSummary);

      for( ModuleVersionChange moduleVersionChange : changeSummary.getModuleVersionChanges() ) {
        UpgradeServiceSummary upgradeServiceSummary = new UpgradeServiceSummary();
        upgradeServiceSummary.sourceVersion = moduleVersionChange.getSource().getVersion();
        upgradeServiceSummary.targetVersion = moduleVersionChange.getTarget().getVersion();
        upgradeServiceSummary.components = new LinkedHashMap<>();
        serviceGroupSummary.services.put(moduleVersionChange.getSource().getName(), upgradeServiceSummary);

        for( ModuleComponentVersionChange componentVersionChange : moduleVersionChange.getComponentChanges() ) {
          UpgradeComponentSummary componentSummary = new UpgradeComponentSummary();
          componentSummary.sourceVersion = componentVersionChange.getSource().getVersion();
          componentSummary.targetVersion = componentVersionChange.getTarget().getVersion();

          upgradeServiceSummary.components.put(componentVersionChange.getSource().getName(), componentSummary);
        }
      }
    }

    return summary;
  }