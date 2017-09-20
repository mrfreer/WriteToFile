package com.example.android.writetofile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText)findViewById(R.id.editText);
        textView = (TextView)findViewById(R.id.textView);
    }

    public void read(View view){
        try {

            FileInputStream fileInputStream = openFileInput("myText.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();
            String line;
            while((line = bufferedReader.readLine()) != null){
                stringBuffer.append(line + "\n");
            }
            textView.setText(stringBuffer.toString());
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }

    public void write(View view){
        String myTextMessage = editText.getText().toString();
        try {
            FileOutputStream fileOutputStream = openFileOutput("myText.txt", MODE_PRIVATE);
            fileOutputStream.write(myTextMessage.getBytes());
            fileOutputStream.close();
            Toast.makeText(getApplicationContext(), "Text saved" , Toast.LENGTH_LONG).show();
            editText.setText("");
        }
        catch (FileNotFoundException f){
            f.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }


    }
}
