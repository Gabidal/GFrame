package fi.quanfoxes;

import android.app.*;
import android.os.*;
import android.util.*;
import android.widget.*;
import android.content.*;
import java.net.*;

public class MainActivity extends Activity 
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		final fetcher a = new fetcher();
		final TextView b = (TextView)findViewById(R.id.output);
		Thread t = new Thread(new Runnable(){
			@Override public void run(){
				
				SharedPreferences reader = getSharedPreferences("save", MODE_PRIVATE);
				double x = reader.getFloat("ID", 0);
				double y = a.getJson("x86");
				if (x < y)
				{
					SharedPreferences.Editor editor = reader.edit();
					editor.putFloat("ID", (float)y);
					editor.apply();
					
				}
				
				
				
				runOnUiThread(new Runnable(){
					@Override public void run(){
						b.setText(a.responce);
					}
				});
			}
		});
		t.start();
    }
}
