public void deleteLsp(IsisMessage lspMessage) {
        LsPdu lsp = (LsPdu) lspMessage;
        String lspKey = lsp.lspId();
        LspWrapper lspWrapper = findLsp(lspMessage.isisPduType(), lspKey);
        switch (lsp.isisPduType()) {
            case L1LSPDU:
                isisL1Db.remove(lspKey);
                break;
            case L2LSPDU:
                isisL2Db.remove(lspKey);
                break;
            default:
                log.debug("Unknown LSP type to remove..!!!");
                break;
        }

        try {
            lspWrapper.setLspProcessing(IsisConstants.LSPREMOVED);
            lspForProviderQueue.put(lspWrapper);
        } catch (Exception e) {
            log.debug("Added LSp In Blocking queue: {}", lspWrapper);
        }
    }