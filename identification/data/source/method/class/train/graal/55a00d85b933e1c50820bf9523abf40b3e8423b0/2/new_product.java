@Override
    public void deleteComponent(String id) throws IOException {
        Path compFile = registryPath.resolve(SystemUtils.fileName(id + COMPONENT_FILE_SUFFIX));
        Path listFile = registryPath.resolve(SystemUtils.fileName(id + LIST_FILE_SUFFIX));
        Files.deleteIfExists(compFile);
        Files.deleteIfExists(listFile);
    }