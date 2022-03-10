package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.FileAsyncHttpResponseHandler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;

public class SplashActivity extends AppCompatActivity {
    public static final String MEDICINES = "medicine";
    public static final String DISEASES = "diseases";

    private static ArrayList<String> medicines = new ArrayList<>();
    private static ArrayList<String> diseases = new ArrayList<>();

    private String url = "https://github.com/MarwanAdel1/Data-set/blob/origin/Medication%20Guides.xls?raw=true";

    private AsyncHttpClient asyncHttpClient;
    private WorkbookSettings workbookSettings;
    private Workbook workbook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        asyncHttpClient = new AsyncHttpClient();

        asyncHttpClient.get(url, new FileAsyncHttpResponseHandler(this) {
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, File file) {
                Log.e(MainActivity.class.getSimpleName(), "Failed : " + statusCode);
                Log.e(MainActivity.class.getSimpleName(), "Failed : " + file.getName());
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, File file) {
                workbookSettings = new WorkbookSettings();
                workbookSettings.setGCDisabled(true);

                if (file != null) {
                    try {
                        workbook = workbook.getWorkbook(file);
                        Sheet sheet = workbook.getSheet(0);

                        for (int i = 0; i < sheet.getRows(); i++) {
                            Cell[] row = sheet.getRow(i);

                            if (!row[0].getContents().isEmpty()) {
                                medicines.add(row[0].getContents());
                            }

                            if (!row[1].getContents().isEmpty()) {
                                diseases.add(row[1].getContents());
                            }
                        }

                        SharedPreferences data = getSharedPreferences("LoginStatus", MODE_PRIVATE);
                        boolean loggedIn = data.getBoolean("LoggedIn", false);

                        if (loggedIn) {
                            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                            intent.putStringArrayListExtra(MEDICINES, medicines);
                            intent.putStringArrayListExtra(DISEASES, diseases);
                            startActivity(intent);
                        }else {
                            Intent intent = new Intent(SplashActivity.this, LoginScreen.class);
                            intent.putStringArrayListExtra(MEDICINES, medicines);
                            intent.putStringArrayListExtra(DISEASES, diseases);
                            startActivity(intent);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (BiffException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}