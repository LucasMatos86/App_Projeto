package br.com.appcadastroclientes.telas;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import br.com.appcadastroclientes.R;


public class SobreActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    public void sendMessage(View view)
    {
        setContentView(R.layout.activity_biografia_lucas);

    }


}