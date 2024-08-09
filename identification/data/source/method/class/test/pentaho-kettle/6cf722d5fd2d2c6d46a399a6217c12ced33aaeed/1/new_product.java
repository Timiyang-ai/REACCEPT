public void setSelectedDatabaseConnection(DatabaseItem selectedDatabaseConnection) {
    DatabaseItem old = this.selectedDatabaseConnection;
    this.selectedDatabaseConnection = selectedDatabaseConnection;
    DatabaseMeta databaseMeta = this.selectedDatabaseConnection == null ? null : jobMeta.findDatabase(this.selectedDatabaseConnection.getName());
    boolean validDatabaseSelected = databaseMeta != null;
    setDatabaseInteractionButtonsDisabled(!validDatabaseSelected);
    updateDatabaseItemsList();
    // If the selected database changes update the config
    if (!suppressEventHandling && (old == null && this.selectedDatabaseConnection != null || !old.equals(this.selectedDatabaseConnection))) {
      if (validDatabaseSelected) {
        try {
          getConfig().setConnectionInfo(databaseMeta.getName(), databaseMeta.getURL(), databaseMeta.getUsername(), databaseMeta.getPassword());
        } catch (KettleDatabaseException ex) {
          sqoopJobEntry.logError(BaseMessages.getString(AbstractSqoopJobEntry.class, "ErrorConfiguringDatabaseConnection"), ex);
        }
      } else {
        getConfig().copyConnectionInfoFromAdvanced();
      }
    }
    firePropertyChange("selectedDatabaseConnection", old, this.selectedDatabaseConnection);
  }