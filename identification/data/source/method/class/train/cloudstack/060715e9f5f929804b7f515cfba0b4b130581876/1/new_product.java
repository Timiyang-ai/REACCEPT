protected SR createFileSR(Connection conn, String path) {
        String srPath = StringUtils.trim(path);
        synchronized (srPath) {
            SR sr = retrieveAlreadyConfiguredSrWithoutException(conn, srPath);
            if (sr == null) {
                sr = createNewFileSr(conn, srPath);
            }
            if (sr == null) {
                String hostUuid = this.hypervisorResource._host.getUuid();
                throw new CloudRuntimeException(String.format("Could not retrieve an already used file SR for path [%s] or create a new file SR on host [%s]", srPath, hostUuid));
            }
            return sr;
        }
    }