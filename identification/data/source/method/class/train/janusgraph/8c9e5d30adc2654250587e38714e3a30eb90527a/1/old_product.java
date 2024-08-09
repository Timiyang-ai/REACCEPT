public void clearStorage() throws BackendException {
        openStores.clear();
        final String lp = "ClearStorage: "; // "log prefix"
        /*
         * log4j is capable of automatically writing the name of a method that
         * generated a log message, but the docs warn that "generating caller
         * location information is extremely slow and should be avoided unless
         * execution speed is not an issue."
         */

        CTConnection conn = null;
        try {
            conn = pool.borrowObject(SYSTEM_KS);
            Cassandra.Client client = conn.getClient();

            KsDef ksDef;
            try {
                client.set_keyspace(keySpaceName);
                ksDef = client.describe_keyspace(keySpaceName);
            } catch (NotFoundException e) {
                log.debug(lp + "Keyspace {} does not exist, not attempting to truncate.", keySpaceName);
                return;
            } catch (InvalidRequestException e) {
                log.debug(lp + "InvalidRequestException when attempting to describe keyspace {}, not attempting to truncate.", keySpaceName);
                return;
            }


            if (null == ksDef) {
                log.debug(lp + "Received null KsDef for keyspace {}; not truncating its CFs", keySpaceName);
                return;
            }

            if (this.storageConfig.get(GraphDatabaseConfiguration.DROP_ON_CLEAR)) {
                client.system_drop_keyspace(keySpaceName);
                pool.clear();
            } else {
                final List<CfDef> cfDefs = ksDef.getCf_defs();

                if (null == cfDefs) {
                    log.debug(lp + "Received empty CfDef list for keyspace {}; not truncating CFs", keySpaceName);
                    return;
                }

                for (final CfDef cfDef : ksDef.getCf_defs()) {
                    client.truncate(cfDef.name);
                    log.info(lp + "Truncated CF {} in keyspace {}", cfDef.name, keySpaceName);
                }

                /*
                 * Clearing the CTConnectionPool is unnecessary. This method
                 * removes no keyspaces. All open Cassandra connections will
                 * remain valid.
                 */
            }
        } catch (Exception e) {
            throw new TemporaryBackendException(e);
        } finally {
            if (conn != null && conn.getClient() != null) {
                try {
                    conn.getClient().set_keyspace(SYSTEM_KS);
                } catch (InvalidRequestException e) {
                    log.warn("Failed to reset keyspace", e);
                } catch (TException e) {
                    log.warn("Failed to reset keyspace", e);
                }
            }
            pool.returnObjectUnsafe(SYSTEM_KS, conn);
        }
    }