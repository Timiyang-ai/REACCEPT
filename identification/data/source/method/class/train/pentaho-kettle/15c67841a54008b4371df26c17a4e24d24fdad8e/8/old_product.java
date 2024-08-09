public TreeSelection[] getTreeObjects( final Tree tree, Tree selectionTree, Tree coreObjectsTree ) {
    List<TreeSelection> objects = new ArrayList<TreeSelection>();

    if ( selectionTree != null && !selectionTree.isDisposed() && tree.equals( selectionTree ) ) {
      TreeItem[] selection = selectionTree.getSelection();
      for ( int s = 0; s < selection.length; s++ ) {
        TreeItem treeItem = selection[s];
        String[] path = ConstUI.getTreeStrings( treeItem );

        TreeSelection object = null;

        switch ( path.length ) {
          case 0:
            break;
          case 1: // ------complete-----
            if ( path[0].equals( Spoon.STRING_TRANSFORMATIONS ) ) { // the top level Transformations entry

              object = new TreeSelection( path[0], TransMeta.class );
            }
            if ( path[0].equals( Spoon.STRING_JOBS ) ) { // the top level Jobs entry

              object = new TreeSelection( path[0], JobMeta.class );
            }
            break;

          case 2: // ------complete-----
            if ( path[0].equals( Spoon.STRING_BUILDING_BLOCKS ) ) { // the top level Transformations entry

              if ( path[1].equals( Spoon.STRING_TRANS_BASE ) ) {
                object = new TreeSelection( path[1], PluginInterface.class );
              }
            }
            if ( path[0].equals( Spoon.STRING_TRANSFORMATIONS ) ) { // Transformation title

              object = new TreeSelection( path[1], spoon.delegates.trans.getTransformation( path[1] ) );
            }
            if ( path[0].equals( Spoon.STRING_JOBS ) ) { // Jobs title

              object = new TreeSelection( path[1], spoon.delegates.jobs.getJob( path[1] ) );
            }
            break;

          case 3: // ------complete-----
            if ( path[0].equals( Spoon.STRING_TRANSFORMATIONS ) ) { // Transformations title

              TransMeta transMeta = spoon.delegates.trans.getTransformation( path[1] );
              if ( path[2].equals( Spoon.STRING_CONNECTIONS ) ) {
                object = new TreeSelection( path[2], DatabaseMeta.class, transMeta );
              }
              if ( path[2].equals( Spoon.STRING_STEPS ) ) {
                object = new TreeSelection( path[2], StepMeta.class, transMeta );
              }
              if ( path[2].equals( Spoon.STRING_HOPS ) ) {
                object = new TreeSelection( path[2], TransHopMeta.class, transMeta );
              }
              if ( path[2].equals( Spoon.STRING_PARTITIONS ) ) {
                object = new TreeSelection( path[2], PartitionSchema.class, transMeta );
              }
              if ( path[2].equals( Spoon.STRING_SLAVES ) ) {
                object = new TreeSelection( path[2], SlaveServer.class, transMeta );
              }
              if ( path[2].equals( Spoon.STRING_CLUSTERS ) ) {
                object = new TreeSelection( path[2], ClusterSchema.class, transMeta );
              }
              executeExtensionPoint( new SpoonTreeDelegateExtension( transMeta, path, 3, objects ) );
            }
            if ( path[0].equals( Spoon.STRING_JOBS ) ) { // Jobs title

              JobMeta jobMeta = spoon.delegates.jobs.getJob( path[1] );
              if ( path[2].equals( Spoon.STRING_CONNECTIONS ) ) {
                object = new TreeSelection( path[2], DatabaseMeta.class, jobMeta );
              }
              if ( path[2].equals( Spoon.STRING_JOB_ENTRIES ) ) {
                object = new TreeSelection( path[2], JobEntryCopy.class, jobMeta );
              }
              if ( path[2].equals( Spoon.STRING_SLAVES ) ) {
                object = new TreeSelection( path[2], SlaveServer.class, jobMeta );
              }
              executeExtensionPoint( new SpoonTreeDelegateExtension( jobMeta, path, 3, objects ) );
            }
            break;

          case 4: // ------complete-----
            if ( path[0].equals( Spoon.STRING_TRANSFORMATIONS ) ) { // The name of a transformation
              final TransMeta transMeta = spoon.delegates.trans.getTransformation( path[1] );

              if ( transMeta != null ) {
                if ( path[2].equals( Spoon.STRING_CONNECTIONS ) ) {
                  String dbName = path[3];
                  DatabaseMeta databaseMeta = transMeta.findDatabase( dbName );
                  if ( databaseMeta != null ) {
                    dbName = databaseMeta.getName();
                  }

                  object = new TreeSelection( dbName, databaseMeta, transMeta );
                }
                if ( path[2].equals( Spoon.STRING_STEPS ) ) {
                  object = new TreeSelection( path[3], transMeta.findStep( path[3] ), transMeta );
                }
                if ( path[2].equals( Spoon.STRING_HOPS ) ) {
                  object = new TreeSelection( path[3], transMeta.findTransHop( path[3] ), transMeta );
                }
                if ( path[2].equals( Spoon.STRING_PARTITIONS ) ) {
                  object = new TreeSelection( path[3], transMeta.findPartitionSchema( path[3] ), transMeta );
                }
                if ( path[2].equals( Spoon.STRING_SLAVES ) ) {
                  object = new TreeSelection( path[3], transMeta.findSlaveServer( path[3] ), transMeta );
                }
                if ( path[2].equals( Spoon.STRING_CLUSTERS ) ) {
                  object = new TreeSelection( path[3], transMeta.findClusterSchema( path[3] ), transMeta );
                }
                executeExtensionPoint( new SpoonTreeDelegateExtension( transMeta, path, 4, objects ) );
              }
            }
            if ( path[0].equals( Spoon.STRING_JOBS ) ) { // The name of a job
              JobMeta jobMeta = spoon.delegates.jobs.getJob( path[1] );
              if ( jobMeta != null && path[2].equals( Spoon.STRING_CONNECTIONS ) ) {
                String dbName = path[3];
                DatabaseMeta databaseMeta = jobMeta.findDatabase( dbName );
                if ( databaseMeta != null ) {
                  dbName = databaseMeta.getName();
                }

                object = new TreeSelection( dbName, databaseMeta, jobMeta );
              }
              if ( jobMeta != null && path[2].equals( Spoon.STRING_JOB_ENTRIES ) ) {
                object = new TreeSelection( path[3], jobMeta.findJobEntry( path[3] ), jobMeta );
              }
              if ( jobMeta != null && path[2].equals( Spoon.STRING_SLAVES ) ) {
                object = new TreeSelection( path[3], jobMeta.findSlaveServer( path[3] ), jobMeta );
              }
              executeExtensionPoint( new SpoonTreeDelegateExtension( jobMeta, path, 4, objects ) );
            }
            break;

          case 5:
            if ( path[0].equals( Spoon.STRING_TRANSFORMATIONS ) ) { // The name of a transformation

              TransMeta transMeta = spoon.delegates.trans.getTransformation( path[1] );
              if ( transMeta != null && path[2].equals( Spoon.STRING_CLUSTERS ) ) {
                ClusterSchema clusterSchema = transMeta.findClusterSchema( path[3] );
                object =
                  new TreeSelection( path[4], clusterSchema.findSlaveServer( path[4] ), clusterSchema, transMeta );
              }
            }
            break;
          default:
            break;
        }

        if ( object != null ) {
          objects.add( object );
        }
      }
    }
    if ( tree != null && coreObjectsTree != null && tree.equals( coreObjectsTree ) ) {
      TreeItem[] selection = coreObjectsTree.getSelection();
      for ( int s = 0; s < selection.length; s++ ) {
        TreeItem treeItem = selection[s];
        String[] path = ConstUI.getTreeStrings( treeItem );

        TreeSelection object = null;

        switch ( path.length ) {
          case 0:
            break;
          case 2: // Job entries
            if ( spoon.showJob ) {
              PluginRegistry registry = PluginRegistry.getInstance();
              Class<? extends PluginTypeInterface> pluginType = JobEntryPluginType.class;
              PluginInterface plugin = registry.findPluginWithName( pluginType, path[1] );

              // Retry for Start
              //
              if ( plugin == null ) {
                if ( path[1].equals( JobMeta.STRING_SPECIAL_START ) ) {
                  plugin = registry.findPluginWithId( pluginType, JobMeta.STRING_SPECIAL );
                }
              }
              // Retry for Dummy
              //
              if ( plugin == null ) {
                if ( path[1].equals( JobMeta.STRING_SPECIAL_DUMMY ) ) {
                  plugin = registry.findPluginWithId( pluginType, JobMeta.STRING_SPECIAL );
                }
              }

              if ( plugin != null ) {
                object = new TreeSelection( path[1], plugin );
              }
            }

            if ( spoon.showTrans ) {
              // Steps
              object =
                new TreeSelection( path[1], PluginRegistry.getInstance().findPluginWithName(
                  StepPluginType.class, path[1] ) );
            }
            break;
          default:
            break;
        }

        if ( object != null ) {
          objects.add( object );
        }
      }
    }

    return objects.toArray( new TreeSelection[objects.size()] );
  }