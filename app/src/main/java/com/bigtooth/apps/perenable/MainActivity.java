package com.bigtooth.apps.perenable;

import android.os.*;
import android.support.v7.app.AppCompatActivity;
import android.view.*;
import android.widget.Button;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.io.*;

import java.lang.Process;

public class MainActivity extends AppCompatActivity {

	InterstitialAd mInterstitialAd;

    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

		MobileAds.initialize(getApplicationContext(), "ca-app-pub-5164171001589422/9857178194");

		AdView mAdView = (AdView) findViewById(R.id.adView);
		AdRequest adRequest = new AdRequest.Builder().build();
		mAdView.loadAd(adRequest);

		mInterstitialAd = new InterstitialAd(this);
		mInterstitialAd.setAdUnitId("ca-app-pub-5164171001589422/3810644591");

		mInterstitialAd.setAdListener(new AdListener() {
			@Override
			public void onAdClosed() {
				requestNewInterstitial();
			}
		});

		requestNewInterstitial();

		final Button enable = (Button)findViewById(R.id.enable);
		final Button reboot = (Button)findViewById(R.id.reboot);

		enable.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (mInterstitialAd.isLoaded()) {
					mInterstitialAd.show();
				} else {
					try {
						enable(null);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});

		reboot.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (mInterstitialAd.isLoaded()) {
					mInterstitialAd.show();
				} else {
					try {
						reboot(null);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
    }

	private void requestNewInterstitial() {
		AdRequest adRequest = new AdRequest.Builder()
				.addTestDevice("5C6759521D465119182182A234FF5CE3")
				.build();

		mInterstitialAd.loadAd(adRequest);
	}


	public void enable(View view) throws Exception {
		Process process = Runtime.getRuntime().exec("content insert --uri content://settings/system --bind name:s:status_bar_show_battery_percent --bind value:i:1");
		BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
	}

	public void reboot(View view) throws Exception {
		Process process = Runtime.getRuntime().exec("su");
		BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));

		Process screenrecord = Runtime.getRuntime().exec("reboot");
		BufferedReader out = new BufferedReader(new InputStreamReader(process.getInputStream()));
	}

}
