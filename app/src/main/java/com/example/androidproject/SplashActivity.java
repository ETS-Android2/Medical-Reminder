package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.androidproject.home.view.Home;
import com.example.androidproject.login.loginView.LoginScreen;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.FileAsyncHttpResponseHandler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

    public static ArrayList<String> medicines = new ArrayList<>();
    public static ArrayList<String> diseases = new ArrayList<>();

    private String url = "https://github.com/MarwanAdel1/Data-set/blob/origin/Medication%20Guides.xls?raw=true";

    private AsyncHttpClient asyncHttpClient;
    private WorkbookSettings workbookSettings;
    private Workbook workbook;
    boolean loggedIn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        FileInputStream fis;

        try {

            fis = openFileInput("medicines");
            ObjectInputStream ois = new ObjectInputStream(fis);
             medicines = (ArrayList<String>) ois.readObject();
            ois.close();

            fis = openFileInput("diseases");
            ois = new ObjectInputStream(fis);
            diseases = (ArrayList<String>) ois.readObject();
            ois.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        SharedPreferences data = getSharedPreferences("LoginStatus", MODE_PRIVATE);
        loggedIn = data.getBoolean("LoggedIn", false);

        if (medicines.size()>1&&diseases.size()>1){

            if (loggedIn) {
                Intent intent = new Intent(SplashActivity.this, Home.class);
                startActivity(intent);
            } else {
                Intent intent = new Intent(SplashActivity.this, LoginScreen.class);
                startActivity(intent);
            }
        }else {
            Toast.makeText(this, "Downloading", Toast.LENGTH_SHORT).show();
            getData();
        }




    }

    void getData(){

        asyncHttpClient = new AsyncHttpClient();

        asyncHttpClient.get(url, new FileAsyncHttpResponseHandler(this) {
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, File file) {
                Log.e(Home.class.getSimpleName(), "Failed : " + statusCode);
                Log.e(Home.class.getSimpleName(), "Failed : " + file.getName());
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

                        try {
                            FileOutputStream fos = openFileOutput("medicines",MODE_PRIVATE);
                            ObjectOutputStream oos = new ObjectOutputStream(fos);
                            oos.writeObject(medicines); // write medicines to ObjectOutputStream
                            oos.close();

                            fos = openFileOutput("diseases",MODE_PRIVATE);
                            oos = new ObjectOutputStream(fos);
                            oos.writeObject(diseases); // write diseases to ObjectOutputStream
                            oos.close();

                            Toast.makeText(SplashActivity.this, "Saved", Toast.LENGTH_SHORT).show();

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                        if (loggedIn) {
                            Intent intent = new Intent(SplashActivity.this, Home.class);
                            startActivity(intent);
                        } else {
                            Intent intent = new Intent(SplashActivity.this, LoginScreen.class);
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