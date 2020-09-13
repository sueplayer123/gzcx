package com.example.guzhang;

import android.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.*;

public class MainActivity extends Activity {
    private TextView textView2;
    private EditText editText1;
    private static SQLiteDatabase database;
    private static String stString1;
    private static String stString2;
    private static String stString3;
    public static final String DATABASE_FILENAME = "testsu.db";                // 这个是DB文件名字
    public static final String PACKAGE_NAME = "com.example.guzhang";    // 这个是自己项目包路径

    public static final String DATABASE_PATH = android.os.Environment.getExternalStorageDirectory().getAbsolutePath() + "/"
            + PACKAGE_NAME;
    static String databaseFilename = DATABASE_PATH + "/" + DATABASE_FILENAME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView2 = (TextView) findViewById(R.id.textView2);
        Button button1 = (Button) this.findViewById(R.id.button1);
        Button button2 = (Button) this.findViewById(R.id.button2);
        Button button3 = (Button) this.findViewById(R.id.button3);
        Button button4 = (Button) this.findViewById(R.id.button4);
        editText1 = (EditText) this.findViewById(R.id.editText1);
        database = openDatabase(this);
        button1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                InputStream inputStream = getResources().openRawResource(R.raw.sue);
                String string = getString(inputStream);
                textView2.setText(string);
                textView2.setMovementMethod(new ScrollingMovementMethod());

            }
        });
        button2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                String sql = "select * from liucheng where liucheng like ?";

                Cursor cursor = database.rawQuery(sql, new String[]{"%" + editText1.getText().toString() + "%"});
                while (cursor.moveToNext()) {

                    stString1 = cursor.getString(cursor.getColumnIndex("liucheng"));
                }
                cursor.close();
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, LiuchengActivity.class);
                intent.putExtra("data1", stString1);
                startActivity(intent);

            }
        });
        button3.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                String sql = "select * from guzhang where name like ?";

                Cursor cursor = database.rawQuery(sql, new String[]{"%" + editText1.getText().toString() + "%"});
                while (cursor.moveToNext()) {

                    stString2 = cursor.getString(cursor.getColumnIndex("gzcl"));
                }
                cursor.close();
                Intent intent2 = new Intent();
                intent2.setClass(MainActivity.this, DemoActivity.class);
                intent2.putExtra("data2", stString2);
                startActivity(intent2);

            }
        });
        button4.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                String sql = "select * from casetest where case like ?";
                Cursor cursor = database.rawQuery(sql, new String[]{"%" + editText1.getText().toString() + "%"});

                while (cursor.moveToNext()) {

                    stString3 = cursor.getString(cursor.getColumnIndex("content"));
                }
                cursor.close();
                Intent intent3 = new Intent();
                intent3.setClass(MainActivity.this, CaseActivity.class);
                intent3.putExtra("data3", stString3);
                startActivity(intent3);

            }
        });
    }

    protected void onDestroy() {

        super.onDestroy();
        if (!(database == null)) {
            database.close();
        }
    }

    public static String getString(InputStream inputStream) {
        InputStreamReader inputStreamReader = null;
        try {
            inputStreamReader = new InputStreamReader(inputStream, "gbk");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        BufferedReader reader = new BufferedReader(inputStreamReader);
        StringBuffer sb = new StringBuffer("");
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static SQLiteDatabase openDatabase(Context context) {
        try {

            File dir = new File(DATABASE_PATH);
            if (!dir.exists()) {
                dir.mkdir();//新建文件
            }
            if (!(new File(databaseFilename)).exists()) {
                InputStream is = context.getResources().openRawResource(
                        R.raw.testsu);
                FileOutputStream fos = new FileOutputStream(databaseFilename);
                byte[] buffer = new byte[8192];
                int count = 0;
                while ((count = is.read(buffer)) > 0) {
                    fos.write(buffer, 0, count);
                }

                fos.close();
                is.close();
            }
            database = SQLiteDatabase.openOrCreateDatabase(databaseFilename,
                    null);
            return database;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
