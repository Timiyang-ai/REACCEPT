@Override
        public void destroyObject(MessageConsumerResources model) throws Exception {
            if (model != null) {
                // First clean up our message consumer
                if (model.getMessageConsumer() != null) {
                    model.getMessageConsumer().close();
                }
                
                // If the resource has a 
                if (model.getSession() != null) {
                    if (model.getSession().getTransacted()) {
                        try {
                            model.getSession().rollback();
                        } catch (Exception e) {
                            // Do nothing. Just make sure we are cleaned up
                        }
                    }
                    model.getSession().close();
                }
            }
        }