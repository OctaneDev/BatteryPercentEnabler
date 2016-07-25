package com.bigtooth.apps.perenable;

import android.app.*;
import android.os.*;
import android.view.*;
import java.io.*;

import java.lang.Process;

public class MainActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
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
