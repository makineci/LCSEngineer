package net.locuscontrol.lcsengineer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;

public class electrical extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electrical);
    }

    public void eBtn11Clk(View view){
        Intent i = new Intent(this, eLayout11.class);
        startActivity(i);
    }

    public void eBtn12Clk(View view){

    }


    public void eBtn13Clk(View view){

    }


    public void eBtn21Clk(View view){

    }


    public void eBtn22Clk(View view){

    }


    public void eBtn23Clk(View view){

    }
}
