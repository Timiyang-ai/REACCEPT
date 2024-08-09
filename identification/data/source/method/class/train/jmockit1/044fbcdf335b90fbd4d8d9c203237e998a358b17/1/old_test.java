   @Test
   public void doBusinessOperationXyz() throws Exception {
      EntityX existingItem = new EntityX(1, "AX5", "abc@xpta.net");
      persist(existingItem);

      businessService.doBusinessOperationXyz();

      assertNotEquals(0, data.getId()); // implies "data" was persisted
      new Verifications() {{ anyEmail.send(); times = 1; }};
   }