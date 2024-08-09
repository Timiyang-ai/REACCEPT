public void delete(final Id.Artifact artifactId) throws IOException {

    // delete everything in a transaction
    try {
      transactional.execute(new TxRunnable() {
        @Override
        public void run(DatasetContext context) throws Exception {
          // first look up details to get plugins and apps in the artifact
          ArtifactCell artifactCell = new ArtifactCell(artifactId);
          Table metaTable = getMetaTable(context);
          byte[] detailBytes = metaTable.get(artifactCell.rowkey, artifactCell.column);
          if (detailBytes == null) {
            // ok there is nothing to delete, we're done
            return;
          }
          deleteMeta(metaTable, artifactId, detailBytes);
        }
      });
    } catch (TransactionFailureException e) {
      throw Transactions.propagate(e, IOException.class);
    }
  }