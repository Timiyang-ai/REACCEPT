public void predict(Dataset newData) { 
        logger.info("predict()");
        
        knowledgeBase.load();
        
        predictDataset(newData);

    }