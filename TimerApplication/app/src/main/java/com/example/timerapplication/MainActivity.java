package com.example.timerapplication;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.StrictMode;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.view.View;

import org.w3c.dom.Text;


public class MainActivity extends AppCompatActivity {

    //defining variables and count
    TextView timeleft;

    Button startstopbutton;//this is used for the start button initial implementation was to have buttons combined
    //stopbutton used to stop process
    Button StopButton;
    //timerbar variable is the progress bar itself
    ProgressBar timerbar;
    //duration input is used to read what is placed for the workout segment
    EditText durationinput;
    //rest input used to read what is used for the rest input
    EditText restinput;
    //timer used to start and stop the process as the task sheet states this is used as a CountDownTimer
    CountDownTimer timer = null;

    //progress track is used to count and fill progress bar
    int progresstrack = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initialising variables and text bar
        timeleft = (TextView) findViewById(R.id.timeleft);
        startstopbutton = (Button) findViewById(R.id.startstopbutton);
        timerbar = (ProgressBar) findViewById(R.id.timerbar);
        restinput = (EditText)findViewById(R.id.restinput);
        durationinput = (EditText)findViewById(R.id.durationinput);
        timerbar.setProgress(progresstrack);

    }
    public void startstopclick(View view) {
            //converting both the workout and rest inputs to strings so they can be changed to longs
            String workingoutinput = durationinput.getText().toString();
            String restinginput = restinput.getText().toString();
            //toast is used to check if anything is placed in the fields and throws an android message if left empty
            if (workingoutinput.matches("") & restinginput.matches("")) {
                Toast.makeText(MainActivity.this,"PLEASE ENTER TIME ON BOTH FIELDS",
                        Toast.LENGTH_SHORT).show();

            }
            else {

                //workout input changed to long
                long workoutlonginput = Long.parseLong(workingoutinput)*1000;


                    //countdown timer initializing
                    timer = new CountDownTimer(workoutlonginput,1000){
                        public void onTick(long millisUntilFinished){
                            //conversion for formatting to minutes and seconds
                            long total = millisUntilFinished/1000;
                            long minute = (total %3600) / 60;
                            long sec = total % 60;
                            String puttimetogether = String.format("%02d:%02d",minute,sec);
                            timeleft.setText("WORKOUT TIME: " +puttimetogether);

                            int intuntilfins = (int) workoutlonginput;
                            progresstrack++;
                            timerbar.setProgress((progresstrack*100/(intuntilfins/1000)));

                        }

                        @Override
                        public void onFinish() {
                            //resets the progress bar for next iteration
                            progresstrack =0;
                            timerbar.setProgress(0);
                            //calls function for second timer to begin once timer is done
                            secondtimer();
                        }


                    }.start();// starts process

        }

    }
// second function for rest timer is created which is essentially a repeat of the code
    public void secondtimer(){

            String workingoutinput = durationinput.getText().toString();
            String restinginput = restinput.getText().toString();
            if (workingoutinput.matches("") & restinginput.matches("")) {
                Toast.makeText(MainActivity.this,"PLEASE ENTER TIME ON BOTH FIELDS",
                        Toast.LENGTH_SHORT).show();

            }
            else {

                long restinglonginput = Long.parseLong(restinginput)*1000;

                    timer = new CountDownTimer(restinglonginput,1000){
                        public void onTick(long millisUntilFinished){
                            long total = millisUntilFinished/1000;
                            long minute = (total %3600) / 60;
                            long sec = total % 60;
                            String puttimetogether = String.format("%02d:%02d",minute,sec);
                            timeleft.setText("RESTING TIME: "+puttimetogether);

                            int intuntilfin =(int) restinglonginput;
                            progresstrack++;
                            timerbar.setProgress((progresstrack*100/(intuntilfin/1000)));
                        }

                        @Override
                        public void onFinish() {
                            //resets the progress bar for next iteration
                            progresstrack =0;
                            timerbar.setProgress(0);
                            //cancels process
                            timer.cancel();
                        }

                    }.start();

            }
        }
        //stop button
      public void StopClick(View view){
        // uses cancel android function to stop process
        timer.cancel();
      }
    }



