public int rebalanceNode(RebalancePartitionsInfo stealInfo) {
        VAdminProto.InitiateRebalanceNodeRequest rebalanceNodeRequest = VAdminProto.InitiateRebalanceNodeRequest.newBuilder()
                                                                                                                .setAttempt(stealInfo.getAttempt())
                                                                                                                .setDonorId(stealInfo.getDonorId())
                                                                                                                .setStealerId(stealInfo.getStealerId())
                                                                                                                .addAllPartitions(stealInfo.getPartitionList())
                                                                                                                .addAllUnbalancedStore(stealInfo.getUnbalancedStoreList())
                                                                                                                .addAllDeletePartitions(stealInfo.getDeletePartitionsList())
                                                                                                                .addAllStealMasterPartitions(stealInfo.getStealMasterPartitions())
                                                                                                                .addAllStealerRoStoreToDir(decodeROStoreVersionDirMap(stealInfo.getStealerNodeROStoreToDir()))
                                                                                                                .addAllDonorRoStoreToDir(decodeROStoreVersionDirMap(stealInfo.getDonorNodeROStoreToDir()))
                                                                                                                .build();
        VAdminProto.VoldemortAdminRequest adminRequest = VAdminProto.VoldemortAdminRequest.newBuilder()
                                                                                          .setType(VAdminProto.AdminRequestType.INITIATE_REBALANCE_NODE)
                                                                                          .setInitiateRebalanceNode(rebalanceNodeRequest)
                                                                                          .build();
        VAdminProto.AsyncOperationStatusResponse.Builder response = sendAndReceive(stealInfo.getStealerId(),
                                                                                   adminRequest,
                                                                                   VAdminProto.AsyncOperationStatusResponse.newBuilder());

        if(response.hasError())
            throwException(response.getError());

        return response.getRequestId();
    }