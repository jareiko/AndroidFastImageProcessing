package project.android.imageprocessing.output;

import project.android.imageprocessing.FastImageProcessingPipeline;
import project.android.imageprocessing.GLRenderer;
import project.android.imageprocessing.input.GLTextureOutputRenderer;

/**
 * A screen renderer extension of GLRenderer. 
 * This class accepts a texture as input and renders it to the screen.
 * @author Chris Batt
 */
public class ScreenEndpoint extends GLRenderer implements GLTextureInputRenderer {
	private FastImageProcessingPipeline rendererContext;
	
	/**
	 * Creates a GLTextureToScreenRenderer. 
	 * If it is not set to full screen mode, the reference to the render context is allowed to be null.
	 * @param rendererContext
	 * A reference to the GLSurfaceView.Renderer that contains the OpenGL context.
	 * @param fullScreenTexture
	 * Whether or not to use the input filter size as the render size or to render full screen.
	 */
	public ScreenEndpoint(FastImageProcessingPipeline rendererContext) {
		super();
		this.rendererContext = rendererContext;
	}
	
	@Override
	protected void initWithGLContext() {
		setRenderSize(rendererContext.getWidth(), rendererContext.getHeight());
		super.initWithGLContext();
	}
	
	/* (non-Javadoc)
	 * @see project.android.imageprocessing.output.GLTextureInputRenderer#newTextureReady(int, project.android.imageprocessing.input.GLTextureOutputRenderer)
	 */
	@Override
	public void newTextureReady(int texture, GLTextureOutputRenderer source) {
		texture_in = texture;
		setWidth(source.getWidth());
		setHeight(source.getHeight());
		onDrawFrame();
	}
}
