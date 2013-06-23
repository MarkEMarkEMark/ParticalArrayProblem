package rajawali.tutorials;

import rajawali.RajawaliActivity;
import android.os.Bundle;
import android.view.Menu;

public class RajawaliParticleActivity extends RajawaliActivity {
	private RajawaliParticleRenderer mRenderer; 
	
	@Override public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mRenderer = new RajawaliParticleRenderer(this);
		mRenderer.setSurfaceView(mSurfaceView);
		super.setRenderer(mRenderer);
	}
}
