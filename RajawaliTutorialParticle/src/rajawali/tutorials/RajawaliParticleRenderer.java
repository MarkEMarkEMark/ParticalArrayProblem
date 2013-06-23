package rajawali.tutorials;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import rajawali.animation.Animation3D;
import rajawali.materials.ParticleMaterial;
import rajawali.materials.textures.ATexture.TextureException;
import rajawali.materials.textures.Texture;
import rajawali.math.Vector3;
import rajawali.renderer.RajawaliRenderer;
import rajawali.animation.TranslateAnimation3D;
import rajawali.animation.Animation3D.RepeatMode;
import android.content.Context;
import android.opengl.GLES20;

public class RajawaliParticleRenderer extends RajawaliRenderer {
	private final int MAX_FRAMES = 500;
	private final int PTCL_COUNT = 1;
	private int mFrameCount;
	private ExampleParticleSystem[] mParticleSystem = new ExampleParticleSystem[PTCL_COUNT];
	private ExampleParticleSystem mParticle = new ExampleParticleSystem();
	private int ptcl;

	// MEO: Animation
	Animation3D anim = null;

	public RajawaliParticleRenderer(Context context) {
		super(context);
		setFrameRate(60);
	}

	protected void initScene() {
		getCurrentCamera().setPosition(0, 0, 10);

		ParticleMaterial material = new ParticleMaterial();
		try {
			material.addTexture(new Texture(R.drawable.starburst_b_128));
		} catch (TextureException e) {
			e.printStackTrace();
		}

		if (false) { //set to true to test array version
			for (ptcl = 0; ptcl < PTCL_COUNT; ptcl++) {
				mParticleSystem[ptcl].setMaterial(material);
				mParticleSystem[ptcl].setPointSize(1000);

				mParticleSystem[ptcl].setTransparent(true);
				mParticleSystem[ptcl].setBlendingEnabled(true);
				mParticleSystem[ptcl].setBlendFunc(GLES20.GL_SRC_ALPHA,
						GLES20.GL_ONE_MINUS_SRC_ALPHA);

				addChild(mParticleSystem[ptcl]);
				anim = new TranslateAnimation3D(new Vector3(1, 1, 0),
						new Vector3(-1, -1, 0));
				anim.setDuration(1000);
				anim.setRepeatMode(RepeatMode.REVERSE_INFINITE);
				anim.setTransformable3D(mParticleSystem[ptcl]);
				registerAnimation(anim);
				anim.play();
			}
		} else {
			mParticle.setMaterial(material);
			mParticle.setPointSize(1000);

			mParticle.setTransparent(true);
			mParticle.setBlendingEnabled(true);
			mParticle.setBlendFunc(GLES20.GL_SRC_ALPHA,
					GLES20.GL_ONE_MINUS_SRC_ALPHA);

			addChild(mParticle);
			anim = new TranslateAnimation3D(new Vector3(1, 1, 0), new Vector3(
					-1, -1, 0));
			anim.setDuration(1000);
			anim.setRepeatMode(RepeatMode.REVERSE_INFINITE);
			anim.setTransformable3D(mParticle);
			registerAnimation(anim);
			anim.play();
		}

	}

	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		super.onSurfaceCreated(gl, config);
	}

	public void onDrawFrame(GL10 glUnused) {
		super.onDrawFrame(glUnused);
	}
}
