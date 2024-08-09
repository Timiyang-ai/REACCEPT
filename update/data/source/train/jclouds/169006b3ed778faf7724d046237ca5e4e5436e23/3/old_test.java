@Test(description = "GET /vApp/{id}/screen", dependsOnMethods = { "testInstallVMwareTools" })
   public void testGetScreenImage() {
      // Power on Vm
      vm = powerOnVm(vm.getHref());

      // The method under test
      byte[] image = vmApi.getScreenImage(vm.getHref());

      // Check returned bytes against PNG header magic number
      byte[] pngHeaderBytes = new byte[] { (byte) 0x89, 0x50, 0x4E, 0x47, 0x0D, 0x0A, 0x1A, 0x0A };
      assertNotNull(image);
      assertTrue(image.length > pngHeaderBytes.length);
      for (int i = 0; i < pngHeaderBytes.length; i++) {
         assertEquals(image[i], pngHeaderBytes[i],
                  String.format("Image differs from PNG format at byte %d of header", i));
      }
   }