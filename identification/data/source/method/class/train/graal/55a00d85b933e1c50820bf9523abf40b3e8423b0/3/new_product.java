@Override
    public ComponentInfo loadComponentFiles(ComponentInfo ci) throws IOException {
        String tag = ci.getId();
        Path listFile = registryPath.resolve(SystemUtils.fileName(tag + LIST_FILE_SUFFIX));
        if (!Files.exists(listFile)) {
            return ci;
        }
        List<String> s = Files.readAllLines(listFile);
        // throw away duplicities, sort.
        Set<String> result = new HashSet<>(s.size());
        for (String e : s) {
            String trimmed = e.trim();
            if (!trimmed.isEmpty()) {
                SystemUtils.checkCommonRelative(null, trimmed);
                result.add(trimmed);
            }
        }
        s = new ArrayList<>(result);
        Collections.sort(s);
        ci.addPaths(s);
        return ci;
    }