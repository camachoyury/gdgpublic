package com.gdgcochabamba.ubicate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;

import com.gdgcochabamba.ubicate.controller.PlaceController;
import com.gdgcochabamba.ubicate.controller.UserController;
import com.gdgcochabamba.ubicate.model.Place;
import com.gdgcochabamba.ubicate.model.User;
import com.google.gson.Gson;

import roboguice.inject.ContentView;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;

//@ContentView(R.layout.activity_main)
@TargetApi(Build.VERSION_CODES.GINGERBREAD)
@SuppressLint("NewApi")
public class MainActivity extends Activity implements OnClickListener {

	private EditText emailText;
	private EditText password;
	Button enter;
	public static MainActivity instance;
	public final static String PREF = "preference";
	
	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		instance = this;
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		emailText = (EditText) findViewById(R.id.et_email);
		password = (EditText) findViewById(R.id.et_password);
		enter = (Button) findViewById(R.id.enter);
		enter.setOnClickListener(this);


	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		if (v == enter) {
			if (emailText.getText().length() > 0
					&& password.getText().length() > 0) {
				Intent intent = new Intent(getApplicationContext(),
						MapActivity.class);
				startActivity(intent);
				User user = new User();
				user.setEmail(String.valueOf(emailText.getText().toString()));
				user.setPassword(String.valueOf(password.getText().toString()));
				UserController controller = new UserController();
				try {
					user = controller.put(user);
					saveStringPreferences("USER_ID",String.valueOf(user.getId()));
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Log.d("USER JSON", user.toString());
				
			}else {
				Toast.makeText(getBaseContext(), "Email and password can't be null",
						Toast.LENGTH_SHORT).show();
			}

		}

	}
	
	
	
	public static void saveStringPreferences(String key, String value)
			throws NullPointerException {
		try {
			SharedPreferences sharedPreferences = instance
					.getSharedPreferences(PREF, 0);
			SharedPreferences.Editor editor = sharedPreferences.edit();
			editor.putString(key, value);
			editor.commit();
		} catch (NullPointerException e) {
			throw new NullPointerException(e.getMessage());
		} catch (Exception e) {
			throw new NullPointerException(e.getMessage());
		}
	}

	public static String loadStringPreferences(String key) {

		SharedPreferences sharedPreferences = instance.getSharedPreferences(
				PREF, 0);
		String res = sharedPreferences.getString(key, "");
		return res;

	}


}
