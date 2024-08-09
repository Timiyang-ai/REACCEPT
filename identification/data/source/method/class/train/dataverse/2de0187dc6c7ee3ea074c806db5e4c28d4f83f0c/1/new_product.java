public static List<ExternalToolHandler> findExternalToolHandlersByFile(List<ExternalTool> externalTools, DataFile file, ApiToken apiToken) {
        List<ExternalToolHandler> externalToolHandlers = new ArrayList<>();
        externalTools.forEach((externalTool) -> {
            ExternalToolHandler externalToolHandler = new ExternalToolHandler(externalTool, file, apiToken);
            externalToolHandlers.add(externalToolHandler);
        });
        return externalToolHandlers;
    }