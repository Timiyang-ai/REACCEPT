@Override
    public Result saveBlockHeader(BlockHeaderPo po) {
        if (null == po) {
            return Result.getFailed(KernelErrorCode.NULL_PARAMETER);
        }
        byte[] hashBytes = new byte[0];
        try {
            hashBytes = po.getHash().serialize();
        } catch (IOException e) {
            Log.error(e);
            return Result.getFailed(e.getMessage());
        }
        Result result = null;
        try {
            result = dbService.put(ProtocolStorageConstant.DB_NAME_BLOCK_HEADER, hashBytes, po.serialize());
        } catch (IOException e) {
            Log.error(e);
            return Result.getFailed(e.getMessage());
        }
        if (result.isFailed()) {
            return result;
        }
        result = dbService.put(ProtocolStorageConstant.DB_NAME_BLOCK_HEADER_INDEX, new VarInt(po.getHeight()).encode(), hashBytes);
        if (result.isFailed()) {
            this.removeBlockHerader(hashBytes);
            return result;
        }
        dbService.put(ProtocolStorageConstant.DB_NAME_BLOCK_HEADER_INDEX, new VarInt(ProtocolStorageConstant.BEST_BLOCK_HASH_INDEX).encode(), hashBytes);
        return Result.getSuccess();
    }