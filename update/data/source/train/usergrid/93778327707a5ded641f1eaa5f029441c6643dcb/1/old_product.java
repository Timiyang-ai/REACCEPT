@Override
    public List<ChangeLogEntry> getChangeLog( List<MvccEntity> mvccEntities, UUID minVersion ) {

        Map<String, ChangeLogEntry> deleteMap = new HashMap<String, ChangeLogEntry>();
        Map<byte[], ChangeLogEntry> writeMap = new HashMap<byte[], ChangeLogEntry>();

        List<ChangeLogEntry> changeLog = new ArrayList<ChangeLogEntry>();

        for (MvccEntity mvccEntity : mvccEntities) {

            System.out.println("-------------------------------------");
            System.out.println("Version " + mvccEntity.getVersion());

            Entity entity = mvccEntity.getEntity().get();

            int compare = mvccEntity.getVersion().compareTo(minVersion);

            if (compare == -1) { // less than minVersion

                for (Field field : entity.getFields()) {

                    String key = field.getName();

                    ChangeLogEntry cle = deleteMap.get( key );
                    if ( cle == null ) {
                        cle = new ChangeLogEntry( entity.getId(), null, 
                                ChangeLogEntry.ChangeType.PROPERTY_DELETE, field );
                        deleteMap.put( key, cle );
                        changeLog.add( 0, cle );
                    } 
                }

            } else { // greater than or equal to minVersion

                for (Field field : entity.getFields()) {

                    byte[] hash;
                    String key = field.getName() + field.getValue(); 
                    try {
                        MessageDigest instance = MessageDigest.getInstance( "MD5" );
                        hash = instance.digest( key.getBytes("UTF-8") );
                    } catch ( Exception ex ) {
                        throw new RuntimeException("MD5 not supported", ex);
                    }

                    ChangeLogEntry cle = writeMap.get( hash );
                    if ( cle == null ) {
                        cle = new ChangeLogEntry( entity.getId(), mvccEntity.getVersion(), 
                                ChangeLogEntry.ChangeType.PROPERTY_WRITE, field );
                        writeMap.put( hash, cle );
                        changeLog.add( 0, cle );
                    } else {
                        cle.addVersion( mvccEntity.getVersion() );
                    } 
                } 
            }
        }
        return changeLog;
    }