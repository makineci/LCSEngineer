package net.locuscontrol.lcsengineer;

import android.support.v7.app.ActionBarActivity;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;


public class MainActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void electricalBtn(View view){
        Intent i = new Intent(this, electrical.class);
        startActivity(i);
    }


    public void mechanicalBtn(View view){
        Intent i = new Intent(this, mechanical.class);
        startActivity(i);
    }
}
