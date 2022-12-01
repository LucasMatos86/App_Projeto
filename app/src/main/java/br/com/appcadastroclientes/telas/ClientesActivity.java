package br.com.appcadastroclientes.telas;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import br.com.appcadastroclientes.R;
import br.com.appcadastroclientes.dao.DaoCliente;
import br.com.appcadastroclientes.model.ModelCliente;

public class ClientesActivity extends AppCompatActivity {

    private EditText campoNome, campoCelular, campoEmail, campoEndereco, campoObservacao;
    private String idCodigo;
    private Button botaoAdicionarCliente, botaoAlterarCliente;
    ModelCliente clienteAtual;

    public void inicializarComponentes(){
        campoNome = (EditText)findViewById(R.id.idNome);
        campoCelular = (EditText)findViewById(R.id.idCelular);
        campoEmail = (EditText)findViewById(R.id.idEmail);
        campoEndereco = (EditText)findViewById(R.id.idEndereco);
        campoObservacao = (EditText)findViewById(R.id.idObservacao);
        botaoAdicionarCliente = (Button)findViewById(R.id.idBtnCadastro);
        botaoAlterarCliente =(Button)findViewById(R.id.idBtnAlterar);
    }

    public void limparCampos(){
        campoNome.setText("");
        campoCelular.setText("");
        campoEmail.setText("");
        campoEndereco.setText("");
        campoObservacao.setText("");
    }

    public void cadastroCliente(View view){

        String txtNome, txtCelular, txtEmail, txtEndereco, txtObservacao;
        txtNome = campoNome.getText().toString();
        txtCelular = campoCelular.getText().toString();
        txtEmail = campoEmail.getText().toString();
        txtEndereco = campoEndereco.getText().toString();
        txtObservacao = campoObservacao.getText().toString();

        if (!txtNome.isEmpty()){
            if (!txtCelular.isEmpty()){
                if (!txtEmail.isEmpty()){
                    if (!txtEndereco.isEmpty()){

                        DaoCliente escreverCliente = new DaoCliente(getBaseContext());

                        ModelCliente setCliente = new ModelCliente();

                        setCliente.setCliNome(txtNome);
                        setCliente.setCliCelular(txtCelular);
                        setCliente.setCliEmail(txtEmail);
                        setCliente.setCliEndereco(txtEndereco);
                        setCliente.setCliObservacao(txtObservacao);

                        boolean resultado;

                        resultado = escreverCliente.cadastroCliente(setCliente);

                        if (resultado==true){

                            Toast toast = Toast.makeText(ClientesActivity.this,"Cliente: "+txtNome+
                                            "\n"+"Adicionado com sucesso",
                                    Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.CENTER,0, 0);
                            LinearLayout toastContentView = (LinearLayout) toast.getView ();
                            ImageView imageView = new ImageView(getApplicationContext());
                            imageView.setImageResource(R.drawable.ic_positivo_24dp);
                            toastContentView.addView(imageView, 0);
                            toast.show();

                            limparCampos();
                        }else {

                            Toast.makeText(ClientesActivity.this,"Erro ao Adicionar cliente: "+txtNome,
                                    Toast.LENGTH_LONG).show();

                        }

                    }else {
                        Toast.makeText(ClientesActivity.this,"Preencha o campo Endereço!",
                                Toast.LENGTH_LONG).show();
                    }

                }else {
                    Toast.makeText(ClientesActivity.this,"Preencha o campo Email!",
                            Toast.LENGTH_LONG).show();
                }

            }else {
                Toast.makeText(ClientesActivity.this,"Preencha o campo Celular!",
                        Toast.LENGTH_LONG).show();
            }

        }else {
            Toast.makeText(ClientesActivity.this,"Preencha o campo nome!",
                    Toast.LENGTH_LONG).show();
        }
    }

    public void recebendoDadosAlterar(){
        clienteAtual =(ModelCliente) getIntent().getSerializableExtra("idCliente");

        if (clienteAtual!=null){
            campoNome.setText(clienteAtual.getCliNome());
            campoCelular.setText(clienteAtual.getCliCelular());
            campoEmail.setText(clienteAtual.getCliEmail());
            campoEndereco.setText(clienteAtual.getCliEndereco());
            campoObservacao.setText(clienteAtual.getCliObservacao());
            botaoAdicionarCliente.setVisibility(View.GONE);
            botaoAlterarCliente.setVisibility(View.VISIBLE);
        }

    }

    public void alterarCliente(View view){
        String setNovoNome, setNovoCelular, setNovoEmail, setNovoEndereco, setNovoObservacao;
        setNovoNome = campoNome.getText().toString();
        setNovoCelular = campoCelular.getText().toString();
        setNovoEmail = campoEmail.getText().toString();
        setNovoEndereco = campoEndereco.getText().toString();
        setNovoObservacao = campoObservacao.getText().toString();
        if(!setNovoNome.isEmpty()){
            if(!setNovoCelular.isEmpty()){
                if(!setNovoEmail.isEmpty()){
                    if(!setNovoEndereco.isEmpty()){
                        DaoCliente rescreverCliente = new DaoCliente(getBaseContext());
                        ModelCliente setAlteracao = new ModelCliente();
                        setAlteracao.setCliNome(setNovoNome);
                        setAlteracao.setCliCelular(setNovoCelular);
                        setAlteracao.setCliEmail(setNovoEmail);
                        setAlteracao.setCliEndereco(setNovoEndereco);
                        setAlteracao.setCliObservacao(setNovoObservacao);
                        setAlteracao.setCliCodigo(clienteAtual.getCliCodigo());
                        Boolean resultado = rescreverCliente.alterarCliente(setAlteracao);
                        if (resultado==true){
                            Toast.makeText(ClientesActivity.this, "Dados atualizado com sucesso!", Toast.LENGTH_LONG).show();
                            limparCampos();
                            botaoAdicionarCliente.setVisibility(View.VISIBLE);
                            botaoAlterarCliente.setVisibility(View.GONE);
                        }

                    }else {
                        Toast.makeText(ClientesActivity.this, "Preencha o campo endereço", Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(ClientesActivity.this, "Preencha o campo Email", Toast.LENGTH_LONG).show();
                }
            }else {
                Toast.makeText(ClientesActivity.this, "Preencha o campo Celular", Toast.LENGTH_LONG).show();
            }
        }else {
            Toast.makeText(ClientesActivity.this, "Preencha o campo", Toast.LENGTH_LONG).show();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        inicializarComponentes();
        recebendoDadosAlterar();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cliente,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.idMenuLista:
                startActivity(new Intent(ClientesActivity.this,
                        ListarClienteActivity.class));
                break;
            case R.id.idMenuAlterar:
                startActivity(new Intent(ClientesActivity.this,
                        ListarClienteActivity.class));
                break;
            case R.id.idMenuExcluir:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}