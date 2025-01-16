package edu.ifam.productlist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import edu.ifam.productlist.dto.FornecedorDTO;
import edu.ifam.productlist.entity.Fornecedor;
import edu.ifam.productlist.interfaces.FornecedorAPI;
import edu.ifam.productlist.recycler.FornecedorAdapter;
import edu.ifam.productlist.repository.FornecedorDAO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FornecedorAdapter fornecedorAdapter;
    private List<Fornecedor> fornecedorList;
    private FornecedorDAO fornecedorDAO;
    private ProgressBar pbMainActivity;
    private FornecedorAPI fornecedorAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.recyclerViewFornecedores);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        fornecedorDAO = new FornecedorDAO(this);
        pbMainActivity = findViewById(R.id.pbMain);
        pbMainActivity.setVisibility(View.INVISIBLE);

        acessarAPI();
    }

    @Override
    protected void onStart() {
        super.onStart();
        getFornecedores();
    }

    public void addFornecedorOnClick(View view) {
        Intent intent = new Intent(this, FornecedorDetails.class);
        startActivity(intent);
    }

    private void acessarAPI(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://10.100.50.166:8080").addConverterFactory(GsonConverterFactory.create()).build();
        fornecedorAPI = retrofit.create(FornecedorAPI.class);
    }

    private void getFornecedores(){
        Call<List<FornecedorDTO>> call = fornecedorAPI.getFornecedor();
        pbMainActivity.setVisibility(View.VISIBLE);
        call.enqueue(new Callback<List<FornecedorDTO>>() {
            @Override
            public void onResponse(Call<List<FornecedorDTO>> call, Response<List<FornecedorDTO>> response) {
                List<Fornecedor> fornecedores = new ArrayList<>();
                if (response.isSuccessful() && response.body() != null) {
                    List<FornecedorDTO> fornecedoresDTO = response.body();
                    for (FornecedorDTO fornecedorDTO : fornecedoresDTO)
                        fornecedores.add(fornecedorDTO.getFornecedor());
                } else {
                    String codigoErro = "Erro: " + response.code();
                    Toast.makeText(getApplicationContext(), codigoErro, Toast.LENGTH_SHORT).show();
                }
                fornecedorAdapter = new FornecedorAdapter(getApplicationContext(), fornecedores);
                recyclerView.setAdapter(fornecedorAdapter);
                pbMainActivity.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<List<FornecedorDTO>> call, Throwable t) {
                String failureMessage = "Falha de Acesso: " + t.getMessage();
                Toast.makeText(getApplicationContext(), failureMessage, Toast.LENGTH_LONG).show();
            }
        });
    }
}
