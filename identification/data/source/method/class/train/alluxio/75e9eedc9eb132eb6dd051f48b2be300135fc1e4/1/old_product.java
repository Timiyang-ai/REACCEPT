@Override
  protected void prepareConfiguration(String path,
      org.apache.hadoop.conf.Configuration hadoopConf) {
    if (path.startsWith(SCHEME)) {
      // Configure for Gluster FS
      hadoopConf.set("fs.glusterfs.impl", Configuration.get(PropertyKey.UNDERFS_GLUSTERFS_IMPL));
      hadoopConf.set("mapred.system.dir", Configuration.get(PropertyKey.UNDERFS_GLUSTERFS_MR_DIR));
      hadoopConf
          .set("fs.glusterfs.volumes", Configuration.get(PropertyKey.UNDERFS_GLUSTERFS_VOLUMES));
      hadoopConf.set(
          "fs.glusterfs.volume.fuse." + Configuration.get(PropertyKey.UNDERFS_GLUSTERFS_VOLUMES),
          Configuration.get(PropertyKey.UNDERFS_GLUSTERFS_MOUNTS));
    } else {
      // If not Gluster FS fall back to default HDFS behavior
      // This should only happen if someone creates an instance of this directly rather than via the
      // registry and factory which enforces the GlusterFS prefix being present.
      super.prepareConfiguration(path, hadoopConf);
    }
  }