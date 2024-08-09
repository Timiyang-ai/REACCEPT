@Override
    public void deleteComponent(String id) throws IOException {
        Path compFile = registryPath.resolve(Paths.get(id + COMPONENT_FILE_SUFFIX));
        Path listFile = registryPath.resolve(Paths.get(id + LIST_FILE_SUFFIX));
        Files.deleteIfExists(compFile);
        Files.deleteIfExists(listFile);
    }