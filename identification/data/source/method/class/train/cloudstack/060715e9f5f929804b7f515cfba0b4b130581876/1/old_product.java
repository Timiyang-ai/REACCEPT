protected SR createFileSR(final Connection conn, final String path) {
        SR sr = null;
        PBD pbd = null;

        try {
            final String srname = path.trim();
            synchronized (srname.intern()) {
                final Set<SR> srs = SR.getByNameLabel(conn, srname);
                if (srs != null && !srs.isEmpty()) {
                    return srs.iterator().next();
                }
                final Map<String, String> smConfig = new HashMap<String, String>();
                final Host host = Host.getByUuid(conn, hypervisorResource.getHost().getUuid());
                final String uuid = UUID.randomUUID().toString();
                sr = SR.introduce(conn, uuid, srname, srname, "file", "file", false, smConfig);
                final PBD.Record record = new PBD.Record();
                record.host = host;
                record.SR = sr;
                smConfig.put("location", path);
                record.deviceConfig = smConfig;
                pbd = PBD.create(conn, record);
                pbd.plug(conn);
                sr.scan(conn);
            }
            return sr;
        } catch (final Exception ex) {
            try {
                if (pbd != null) {
                    pbd.destroy(conn);
                }
            } catch (final Exception e1) {
                s_logger.debug("Failed to destroy PBD", ex);
            }

            try {
                if (sr != null) {
                    sr.forget(conn);
                }
            } catch (final Exception e2) {
                s_logger.error("Failed to forget SR", ex);
            }

            final String msg = "createFileSR failed! due to the following: " + ex.toString();

            s_logger.warn(msg, ex);

            throw new CloudRuntimeException(msg, ex);
        }
    }