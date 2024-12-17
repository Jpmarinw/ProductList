package edu.ifam.productlist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

import edu.ifam.productlist.entity.Fornecedor;
import edu.ifam.productlist.repository.FornecedorDAO;

public class FornecedorDetails extends AppCompatActivity {

    private EditText etFornecedorCodigo;
    private EditText etFornecedorNome;
    private EditText etFornecedorTelefone;
    private EditText etFornecedorEmail;
    private EditText etFornecedorEndereco;
    private ImageButton ibSaveFornecedor;
    private ImageButton ibDeleteFornecedor;
    private ImageButton ibClearFornecedor;
    private FornecedorDAO fornecedorDAO;
    private long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_fornecedor_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        fornecedorDAO = new FornecedorDAO(this);

        etFornecedorCodigo = findViewById(R.id.editTextCodigo);
        etFornecedorNome = findViewById(R.id.editTextNome);
        etFornecedorTelefone = findViewById(R.id.editTextTelefone);
        etFornecedorEmail = findViewById(R.id.editTextEmail);
        etFornecedorEndereco = findViewById(R.id.editTextEndereco);
        ibSaveFornecedor = findViewById(R.id.ibSaveFornecedor);
        ibDeleteFornecedor = findViewById(R.id.ibDeleteFornecedor);
        ibClearFornecedor = findViewById(R.id.ibClearFornecedor);

        // Insert
        ibSaveFornecedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fornecedorDAO.insert(getFornecedor());
                Intent intent = new Intent(FornecedorDetails.this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Fornecedor Incluído", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        ibDeleteFornecedor.setVisibility(View.INVISIBLE);

        // Update and Delete
        Intent intent = getIntent();
        if (intent.hasExtra("id")){
            ibDeleteFornecedor.setVisibility(View.VISIBLE);
            id = intent.getLongExtra("id", 0);
            setFornecedor(fornecedorDAO.getFornecedor(id));
            ibSaveFornecedor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    fornecedorDAO.update(id, getFornecedor());
                    Toast.makeText(getApplicationContext(), "Fornecedor Atual", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
        }
    }

    private Fornecedor getFornecedor() {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setCodigo(Integer.parseInt(etFornecedorCodigo.getText().toString()));
        fornecedor.setNome(etFornecedorNome.getText().toString());
        fornecedor.setTelefone(etFornecedorTelefone.getText().toString());
        fornecedor.setEmail(etFornecedorEmail.getText().toString());
        fornecedor.setEndereco(etFornecedorEndereco.getText().toString());
        return fornecedor;
    }

    private void setFornecedor(Fornecedor fornecedor){
        String codigo = String.format(Locale.getDefault(), "%d", fornecedor.getCodigo());
        etFornecedorNome.setText(fornecedor.getNome());
        etFornecedorTelefone.setText(fornecedor.getTelefone());
        etFornecedorEmail.setText(fornecedor.getEmail());
        etFornecedorEndereco.setText(fornecedor.getEndereco());
    }

    //Deletar um fornecedor
    public void ibDeleteFornecedorOnClick(View view){
        fornecedorDAO.delete(id);
        Toast.makeText(this, "Fornecedor Excluído", Toast.LENGTH_SHORT).show();
        finish();
    }

    //Clean Detalhes do fornecedor
    public void ibClearFornecedorOnClick(View view){
        setFornecedor(new Fornecedor());
        etFornecedorCodigo.setText("");
    }
}
