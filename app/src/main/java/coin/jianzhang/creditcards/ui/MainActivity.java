package coin.jianzhang.creditcards.ui;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import coin.jianzhang.creditcards.R;

public class MainActivity extends AppCompatActivity {

    final private int REQUEST_CODE_ASK_WRITE_PERMISSIONS = 111;
    final private int REQUEST_CODE_ASK_READ_PERMISSIONS = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setLogo(R.mipmap.ic_launcher);
        //has to call the request permission for the API 23
        requestReadExternalStoragePermission();
        requestWriteExternalStoragePermission();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        switch (requestCode) {
            case REQUEST_CODE_ASK_READ_PERMISSIONS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission Granted
                } else {
                    // Permission Denied
                    Toast.makeText(MainActivity.this, getString(R.string.allow_read),
                            Toast.LENGTH_SHORT).show();
                    requestReadExternalStoragePermission();
                }
                break;
            case REQUEST_CODE_ASK_WRITE_PERMISSIONS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                    Toast.makeText(MainActivity.this, getString(R.string.allow_write),
                            Toast.LENGTH_SHORT).show();
                    requestWriteExternalStoragePermission();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void requestReadExternalStoragePermission() {

        if (VERSION.SDK_INT >= 23) {
            int hasWriteContactsPermission = checkSelfPermission(Manifest.
                    permission.READ_EXTERNAL_STORAGE);
            if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_ASK_READ_PERMISSIONS);
            }
        }
    }

    private void requestWriteExternalStoragePermission() {

        if (VERSION.SDK_INT >= 23) {
            int hasWriteContactsPermission = checkSelfPermission(Manifest.
                    permission.WRITE_EXTERNAL_STORAGE);
            if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQUEST_CODE_ASK_WRITE_PERMISSIONS);
            }
        }
    }
}
