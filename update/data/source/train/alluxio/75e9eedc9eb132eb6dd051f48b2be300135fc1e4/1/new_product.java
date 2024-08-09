public static Configuration prepareConfiguration(String path, Map<String, String> ufsConf) {
    if (path.startsWith(SCHEME)) {
      Configuration hadoopConf = new Configuration();
      // Configure for Gluster FS
      hadoopConf.set("fs.glusterfs.impl",
          UnderFileSystemUtils.getValue(PropertyKey.UNDERFS_GLUSTERFS_IMPL, ufsConf));
      hadoopConf.set("mapred.system.dir",
          UnderFileSystemUtils.getValue(PropertyKey.UNDERFS_GLUSTERFS_MR_DIR, ufsConf));
      hadoopConf.set("fs.glusterfs.volumes",
          UnderFileSystemUtils.getValue(PropertyKey.UNDERFS_GLUSTERFS_VOLUMES, ufsConf));
      hadoopConf.set("fs.glusterfs.volume.fuse."
          + UnderFileSystemUtils.getValue(PropertyKey.UNDERFS_GLUSTERFS_VOLUMES, ufsConf),
          UnderFileSystemUtils.getValue(PropertyKey.UNDERFS_GLUSTERFS_MOUNTS, ufsConf));
      return hadoopConf;
    } else {
      // If not Gluster FS fall back to default HDFS behavior
      // This should only happen if someone creates an instance of this directly rather than via the
      // registry and factory which enforces the GlusterFS prefix being present.
      return HdfsUnderFileSystem.createConfiguration(ufsConf);
    }
  }