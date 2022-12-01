package br.com.appcadastroclientes.telas;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.appcadastroclientes.R;
import br.com.appcadastroclientes.adapter.AdapterCliente;
import br.com.appcadastroclientes.dao.DaoCliente;
import br.com.appcadastroclientes.model.ModelCliente;

public class ListarClienteActivity extends AppCompatActivity {

    private RecyclerView recyclerViewCliente;
    private AdapterCliente adapterCliente;
    private List<ModelCliente> listCliente = new ArrayList<>();
    private TextView txtDelete,txtAlterar, txtDetalhe;

    public void inicializarComponentes() {
        recyclerViewCliente = (RecyclerView) findViewById(R.id.idRecyclerViewListarCliente);
        txtDelete = (TextView) findViewById(R.id.idTxtExcluirCliente);
        txtAlterar = (TextView) findViewById(R.id.idTxtAlterarCliente);
        txtDetalhe = (TextView) findViewById(R.id.idTxtDetalheCliente);
    }

    public void carregarListacliente() {

        DaoCliente daoCliente = new DaoCliente(getBaseContext());

        listCliente = daoCliente.listarCliente();

        adapterCliente = new AdapterCliente(listCliente, getApplicationContext());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

        recyclerViewCliente.setLayoutManager(layoutManager);
        recyclerViewCliente.setHasFixedSize(true);
        recyclerViewCliente.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        recyclerViewCliente.setAdapter(adapterCliente);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_cliente);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        inicializarComponentes();
        carregarListacliente();
    }
    @Override
    protected void onStart() {
        carregarListacliente();
        super.onStart();
    }
}