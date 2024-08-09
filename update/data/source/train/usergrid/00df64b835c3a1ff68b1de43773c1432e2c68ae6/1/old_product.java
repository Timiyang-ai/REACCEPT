@Override
    public List<ChangeLogEntry> getChangeLog( List<MvccEntity> mvccEntities, UUID minVersion ) {

        Map<String, ChangeLogEntry> writeMap = new HashMap<String, ChangeLogEntry>();

        List<ChangeLogEntry> changeLog = new ArrayList<ChangeLogEntry>();

        for (MvccEntity mvccEntity : mvccEntities) {

            Entity entity = mvccEntity.getEntity().get();
            int compare = mvccEntity.getVersion().compareTo(minVersion);

            if (compare == -1) { // less than minVersion

                for (Field field : entity.getFields()) {
                    String key = field.getName();
                    ChangeLogEntry cle = new ChangeLogEntry( entity.getId(), null, 
                                                 ChangeLogEntry.ChangeType.PROPERTY_DELETE, field );
                    changeLog.add( 0, cle );
                }

            } else { // greater than or equal to minVersion

                for (Field field : entity.getFields()) {

                    String key = field.getName() + field.getValue(); 
                    ChangeLogEntry cle = writeMap.get( key );
                    if ( cle == null ) {
                        cle = new ChangeLogEntry( entity.getId(), mvccEntity.getVersion(), 
                                      ChangeLogEntry.ChangeType.PROPERTY_WRITE, field );
                        writeMap.put( key, cle );
                        changeLog.add( 0, cle );
                    } else {
                        cle.addVersion( mvccEntity.getVersion() );
                    } 
                } 
            }
        }
        return changeLog;
    }