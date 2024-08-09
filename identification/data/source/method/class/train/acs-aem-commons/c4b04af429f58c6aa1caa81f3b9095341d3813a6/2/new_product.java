public void createAssetFolders(ActionManager manager) {
        assetFolderDefinitions.values().stream().forEach(assetFolderDefinition -> {
            try {
                manager.withResolver(rr -> {
                    ReportRowSatus status;

                    try {
                        Resource folder = rr.getResource(assetFolderDefinition.getPath());
                        if (folder == null) {
                            final Map<String, Object> folderProperties = new HashMap<>();
                            folderProperties.put(JcrConstants.JCR_PRIMARYTYPE, assetFolderDefinition.getNodeType());
                            folder = rr.create(rr.getResource(assetFolderDefinition.getParentPath()),
                                    assetFolderDefinition.getName(),
                                    folderProperties);

                            status = ReportRowSatus.CREATED;
                        } else {
                            status = ReportRowSatus.UPDATED_FOLDER_TITLES;
                        }

                        Resource jcrContent = folder.getChild(JcrConstants.JCR_CONTENT);
                        if (jcrContent == null) {
                            final Map<String, Object> jcrContentProperties = new HashMap<>();
                            jcrContentProperties.put(JcrConstants.JCR_PRIMARYTYPE, JcrConstants.NT_UNSTRUCTURED);
                            rr.create(folder, JcrConstants.JCR_CONTENT, jcrContentProperties);
                        }

                        setTitles(folder, assetFolderDefinition);
                        record(status, assetFolderDefinition.getPath(), assetFolderDefinition.getTitle());
                        log.debug("Created Asset Folder [ {} -> {} ]", assetFolderDefinition.getPath(), assetFolderDefinition.getTitle());
                    } catch (Exception e) {
                        record(ReportRowSatus.FAILED_TO_CREATE, assetFolderDefinition.getPath(), assetFolderDefinition.getTitle());
                        log.error("Unable to create Asset Folder [ {} -> {} ]", assetFolderDefinition.getPath(), assetFolderDefinition.getTitle());
                    }
                });
            } catch (Exception e) {
                log.error("Unable to import asset folders via ACS Commons MCP - Asset Folder Creator", e);
            }
        });
    }