package com.danielme.android.dagger2.view;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.danielme.android.dagger2.DaggerDemoApplication;
import com.danielme.android.dagger2.R;
import com.danielme.android.dagger2.model.Artist;
import com.danielme.android.dagger2.service.SingletonService;

import java.io.IOException;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

  @Inject
  SingletonService singletonService;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ((DaggerDemoApplication) getApplication()).getAppComponent().inject(this);

    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    ((TextView) findViewById(R.id.textView1)).setText(singletonService.getPath());
    (new ArtistTask()).execute();
  }

  private class ArtistTask extends AsyncTask<Void, Void, Artist> {

    @Override
    protected Artist doInBackground(Void... params) {
      try {
        return singletonService.getArtist("bbd80354-597e-4d53-94e4-92b3a7cb8f2c");
      } catch (IOException e) {
        return null;
      }
    }

    @Override
    protected void onPostExecute(Artist result) {
      TextView textView = (TextView) findViewById(R.id.textView2);
      if (result == null) {
        textView.setText(getString(R.string.error));
      } else {
        textView.setText(result.getName());
      }
    }

  }

}