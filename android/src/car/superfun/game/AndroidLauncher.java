package car.superfun.game;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.backends.android.AndroidFragmentApplication;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.Games;

import car.superfun.game.CarSuperFun;

import static android.content.ContentValues.TAG;

public class AndroidLauncher extends FragmentActivity implements AndroidFragmentApplication.Callbacks {

	GoogleApiClient client;

	@Override
	public void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		initializeClient();

		//In order to run fragment in full screen.
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

		GameFragment fragment = new GameFragment();
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		transaction.replace(android.R.id.content, fragment);
		transaction.commit();
		//initialize(new CarSuperFun(), config);
	}

	private void initializeClient() {
		client = new GoogleApiClient.Builder(this)
				.addApi(Games.API)
				.addScope(Games.SCOPE_GAMES)
				.enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
					@Override
					public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
						Log.e(TAG, "Could not connect to Play games services");
						finish();
					}
				}).build();
	}

	@Override
	public void exit() {

	}
}
