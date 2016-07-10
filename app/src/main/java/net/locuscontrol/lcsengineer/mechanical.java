package net.locuscontrol.lcsengineer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class mechanical extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mechanical);
    }

    public void mBtn11Clk(View view){
        Intent i = new Intent(this, m_layout11.class);
        startActivity(i);
    }







}


