public void createAssetFolders(ActionManager manager) {
        assetFolderDefinitions.values().stream().forEach(assetFolderDefinition -> {
            try {
                manager.withResolver(rr -> {
                    try {
                        createAssetFolder(assetFolderDefinition, rr);
                    } catch (Exception e) {
                        record(ReportRowStatus.FAILED_TO_CREATE, assetFolderDefinition.getPath(), assetFolderDefinition.getTitle());
                        log.error("Unable to create Asset Folder [ {} -> {} ]", assetFolderDefinition.getPath(), assetFolderDefinition.getTitle());
                    }
                });
            } catch (Exception e) {
                log.error("Unable to import asset folders via ACS Commons MCP - Asset Folder Creator", e);
            }
        });
    }