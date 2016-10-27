package com.example.krzysztof.alotto2;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    private String[] string = {"SELECT GAME", "Lotto", "Euro Millions", "Daily Million"};
    private Spinner spinner;
    //private ArrayAdapter<String> adapter;
    private TextView numberView, bonusView;
    private Button save, load, generate, clear;
    static final int BLOCK_SIZE = 50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_action_baricon2);
        //add reference
        spinner = (Spinner) findViewById(R.id.mySpinner);
        numberView = (TextView) findViewById(R.id.numView);
        bonusView = (TextView) findViewById(R.id.bonView);
        save = (Button)findViewById(R.id.saveBtn);
        load = (Button)findViewById(R.id.loadBtn);
        generate = (Button)findViewById(R.id.genBtn);
        clear = (Button)findViewById(R.id.clearBtn);
        generate.setEnabled(false);
        clear.setEnabled(false);
        save.setEnabled(false);

        //new adapter object
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getBaseContext(), android.R.
                layout.simple_spinner_item, string);
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);//set adapter for a spinner
        //set listener for the spinner
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView)parent.getChildAt(0)).setTextColor(Color.WHITE);
                ((TextView)parent.getChildAt(0)).setTextSize(20);
                //create string  and assign selected item to it

                String game = spinner.getSelectedItem().toString();
                if (game.equals("Lotto")) {
                    numberView.setText(" ");
                    bonusView.setText(" ");
                    generate.setEnabled(true);
                    clear.setEnabled(true);
                    save.setEnabled(true);
                    getLotto();

                } else if (game.equals("Euro Millions")) {
                    numberView.setText(" ");
                    bonusView.setText(" ");
                    generate.setEnabled(true);
                    clear.setEnabled(true);
                    save.setEnabled(true);
                    getEuroMillions();
                    getEuroMillionBonus();

                } else if (game.equals("Daily Million")) {
                    numberView.setText(" ");
                    bonusView.setText(" ");
                    generate.setEnabled(true);
                    clear.setEnabled(true);
                    save.setEnabled(true);
                    getDailyMillion();
                }
                else {
                    numberView.setText("");
                    bonusView.setText("");
                    spinner.setSelection(0);
                    generate.setEnabled(false);
                    clear.setEnabled(false);
                    save.setEnabled(false);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        //Anonymous inner listener class for 'save' btn
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numbers = "No data to be saved";
                //Display message if EditText is empty
                if (numberView.getText().toString().isEmpty()) {
                    Toast.makeText(getBaseContext(), numbers,
                            Toast.LENGTH_SHORT).show();
                } else {
                    //Call the method and clear EditText
                    writeNumbers();
                    writeBonus();
                }
            }
        });
        //Anonymous inner listener class for 'Read' btn
        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Call the method
                clear.setEnabled(true);
                readNumbers();
                readBonus();
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberView.setText("");
                bonusView.setText("");
                spinner.setSelection(0);
                generate.setEnabled(false);
                clear.setEnabled(false);
                save.setEnabled(false);
            }
        });
        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String game = spinner.getSelectedItem().toString();
                if (game.equals("Lotto")) {
                    getLotto();

                } else if (game.equals("Euro Millions")) {
                    getEuroMillions();
                    getEuroMillionBonus();

                } else if (game.equals("Daily Million")) {
                    getDailyMillion();
                }
                else {
                    Toast.makeText(getBaseContext(), "Select game type",
                            Toast.LENGTH_LONG).show();
                    numberView.setText(" ");
                    bonusView.setText(" ");
                }
            }
        });
    }

    public void getLotto(){
        int[] nums = new int[48];
        int a,b,t;
        int size = 7;
        String str = "";
        for (int i = 1;i<48;i++){
            nums[i] = i;
        }
        for (int i=1;i<48;i++){
            int r = (int)(47*Math.random())+1;
            int temp = nums[i];
            nums[i] = nums[r];
            nums[r] = temp;
        }
        for(a=1;a < size;a++)
            for(b=size-1;b>=a;b--){
                if(nums[b-1]>nums[b])
                {
                    t =nums[b-1];
                    nums[b-1]= nums[b];
                    nums[b] = t;
                }
            }
        for (int i=1;i<size;i++){
            str += " " + Integer.toString(nums[i])+ " ";
        }
        numberView.setText(str);
    }
    public void getDailyMillion(){
        int[] nums = new int[40];
        int a,b,t;
        int size = 7;
        String str = "";
        for (int i = 1;i<40;i++){
            nums[i] = i;
        }
        for (int i=1;i<40;i++){
            int r = (int)(39*Math.random())+1;
            int temp = nums[i];
            nums[i] = nums[r];
            nums[r] = temp;
        }
        for(a=1;a < size;a++)
            for(b=size-1;b>=a;b--){
                if(nums[b-1]>nums[b])
                {
                    t =nums[b-1];
                    nums[b-1]= nums[b];
                    nums[b] = t;
                }
            }
        for (int i=1;i<size;i++){
            str += " " + Integer.toString(nums[i])+ " ";
        }
        numberView.setText(str);
    }
    public void getEuroMillions(){
        int[] nums = new int[51];
        int a,b,t;
        int size = 6;
        String str = "";
        for (int i = 1;i<51;i++){
            nums[i] = i;
        }
        for (int i=1;i<51;i++){
            int r = (int)(50*Math.random())+1;
            int temp = nums[i];
            nums[i] = nums[r];
            nums[r] = temp;
        }
        for(a=1;a < size;a++)
            for(b=size-1;b>=a;b--){
                if(nums[b-1]>nums[b])
                {
                    t =nums[b-1];
                    nums[b-1]= nums[b];
                    nums[b] = t;
                }
            }
        for (int i=1;i<size;i++){
            str += " " + Integer.toString(nums[i])+ " ";
        }
        numberView.setText(str);
    }
    public void getEuroMillionBonus(){
        int[] nums = new int[12];
        int a,b,t;
        int size =3;
        String string = "";
        for (int i = 1;i<12;i++){
            nums[i] = i;
        }
        for (int i=1;i<12;i++){
            int r = (int)(11*Math.random())+1;
            int temp = nums[i];
            nums[i] = nums[r];
            nums[r] = temp;
        }
        for(a=1;a < size;a++)
            for(b=size-1;b>=a;b--){
                if(nums[b-1]>nums[b])
                {
                    t =nums[b-1];
                    nums[b-1]= nums[b];
                    nums[b] = t;
                }
            }
        for (int i=1;i<size;i++) {
            string += " " + Integer.toString(nums[i]) + " ";
        }
        bonusView.setText(string);
    }
    //Write name to the file
    public void writeNumbers(){
        try {
            // Write text into file using write method of OutputStreamWriter Class
            FileOutputStream fos = openFileOutput("Numbers.txt", MODE_PRIVATE);
            OutputStreamWriter outputWriter = new OutputStreamWriter(fos);
            outputWriter.write(numberView.getText().toString());
            outputWriter.close();//Close
            String msg = "Data saved";
            //display file saved message
            Toast.makeText(getBaseContext(), msg,
                    Toast.LENGTH_SHORT).show();
            //Catch exception
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //Read numbers
    public void readNumbers(){
        try {
            //Read the name from NameFile.txt using read method
            //Open file for reading.
            FileInputStream fis = openFileInput("Numbers.txt");
            InputStreamReader InputRead= new InputStreamReader(fis);

            char[] inputBuffer= new char[BLOCK_SIZE];
            String numbers="";
            int charRead;

            while ((charRead=InputRead.read(inputBuffer))>0) {
                //Convert char to string
                String read=String.copyValueOf(inputBuffer,0,charRead);
                numbers +=read;
            }
            InputRead.close();
            //Display numbers
            numberView.setText(numbers);
            //Toast.makeText(getBaseContext(), numbers,Toast.LENGTH_SHORT).show();
            //Catch exception
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //Write name to the file
    public void writeBonus(){
        try {
            // Write text into file using write method of OutputStreamWriter Class
            FileOutputStream fos = openFileOutput("Bonus.txt", MODE_PRIVATE);
            OutputStreamWriter outputWriter = new OutputStreamWriter(fos);
            outputWriter.write(bonusView.getText().toString());
            outputWriter.close();//Close
            //Catch exception
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //Read numbers
    public void readBonus(){
        try {
            //Read the name from NameFile.txt using read method
            //Open file for reading.
            FileInputStream fis = openFileInput("Bonus.txt");
            InputStreamReader InputRead= new InputStreamReader(fis);

            char[] inputBuffer= new char[BLOCK_SIZE];
            String bonus="";
            int charRead;

            while ((charRead=InputRead.read(inputBuffer))>0) {
                //Convert char to string
                String read=String.copyValueOf(inputBuffer,0,charRead);
                bonus +=read;
            }
            InputRead.close();
            //Display numbers
            bonusView.setText(bonus);

            //Catch exception
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
