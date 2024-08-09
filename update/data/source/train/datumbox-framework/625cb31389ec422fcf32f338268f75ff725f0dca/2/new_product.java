public void predict(Dataset newData) { 
        logger.debug("predict()");
        
        knowledgeBase.load();
        
        predictDataset(newData);

    }