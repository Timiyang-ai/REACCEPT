protected void setClusters(final Set<Cluster> clusters) {
        this.clusters.clear();
        if (clusters != null) {
            this.clusters.addAll(clusters);
        }
    }