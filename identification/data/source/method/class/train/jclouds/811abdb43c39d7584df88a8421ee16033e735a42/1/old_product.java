public NovaTemplateOptions autoAssignFloatingIp(boolean enable) {
      checkState(floatingIps.isEmpty(), "Cannot auto assign when a floating ip is configured");
      this.autoAssignFloatingIp = enable;
      return this;
   }