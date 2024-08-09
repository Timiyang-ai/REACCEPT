public void predict(Dataset newData) { 
        
        if(GeneralConfiguration.DEBUG) {
            System.out.println("predict()");
        }
        
        knowledgeBase.load();
        
        predictDataset(newData);

    }