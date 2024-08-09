public void prepare(ApplicationId application, ClusterSpec cluster, NodeSpec requestedNodes) {
        if (requestedNodes.type() != NodeType.tenant) return; // Nothing to provision for this node type
        if (cluster.type() != ClusterSpec.Type.container) return; // Nothing to provision for this cluster type
        provision(application, cluster.id(), false);
    }