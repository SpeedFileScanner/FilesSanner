package io.haydar.filescanner.app;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.storage.StorageVolume;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.lang.reflect.Method;
import java.util.ArrayList;

import io.haydar.filescanner.FileInfo;
import io.haydar.filescanner.FileScanner;


public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        View root = findViewById(R.id.root);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fileScanner();
            }
        });

        // Example of a call to a native method
        TextView tv = (TextView) findViewById(R.id.sample_text);
        //tv.setText(stringFromJNI());
        System.out.println(Environment.getExternalStorageDirectory().getAbsolutePath());

        android.os.storage.StorageManager storageManager = (android.os.storage.StorageManager) getSystemService(Context.STORAGE_SERVICE);
        try {
            Method method = storageManager.getClass().getMethod("getVolumeList", new Class[0]);
            Method method2 = storageManager.getClass().getMethod("getVolumeState", new Class[]{String.class});
            if (method != null && method2 != null) {
                StorageVolume[] objArr = (StorageVolume[]) method.invoke(storageManager, new Object[0]);
                Log.d("WW", "getVolumeList=" + objArr.length);
                for (StorageVolume o : objArr) {
                    Log.d("WW", "getVolumeList item=" + o.toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }



        if (PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {

        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                Snackbar.make(root, " will need to read external storage to display songs on your device.",
                        Snackbar.LENGTH_INDEFINITE)
                        .setAction(android.R.string.ok, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
                            }
                        }).show();
            } else {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
            }
        }
    }

    private void fileScanner() {
        //FileScanner.getInstance(this).clear();
        FileScanner.getInstance(this).setType(".mp3").start(new FileScanner.ScannerListener() {
            @Override
            public void onScanBegin() {
                Log.d(TAG, "onScanBegin: ");
            }

            @Override
            public void onScanEnd() {
                Log.d(TAG, "onScanEnd: ");
                ArrayList<FileInfo> fileInfoArrayList = FileScanner.getInstance(MainActivity.this).getAllFiles();
                for (FileInfo fileInfo : fileInfoArrayList) {
                    Log.d(TAG, "fileScanner: " + fileInfo.getFilePath());
                }
            }

            @Override
            public void onScanning(String paramString, int progress) {
                Log.d(TAG, "onScanning: " + progress);
            }

            @Override
            public void onScanningFiles(FileInfo info, int type) {
                Log.d(TAG, "onScanningFiles: info=" + info.toString());
                Log.d(TAG, "onScanningFiles: type=" + type);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
