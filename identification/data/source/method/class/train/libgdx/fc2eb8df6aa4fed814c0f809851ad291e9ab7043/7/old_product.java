public void dispose( )
	{
		GL20 gl = graphics.getGL20();
		
		ByteBuffer tmp = ByteBuffer.allocateDirect(4);
		tmp.order(ByteOrder.nativeOrder());
		IntBuffer handle = tmp.asIntBuffer();
		
		colorTexture.dispose();
		handle.put( depthbufferHandle );
		handle.flip();
		gl.glDeleteRenderbuffers( 1, handle );
		
		handle.put( framebufferHandle );
		handle.flip();
		gl.glDeleteFramebuffers( 1, handle );
		
		buffers.remove( this );
	}