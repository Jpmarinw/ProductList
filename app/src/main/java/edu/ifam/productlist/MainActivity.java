package edu.ifam.productlist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import edu.ifam.productlist.entity.Fornecedor;
import edu.ifam.productlist.recycler.FornecedorAdapter;
import edu.ifam.productlist.repository.FornecedorDAO;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FornecedorAdapter fornecedorAdapter;
    private List<Fornecedor> fornecedorList;
    private FornecedorDAO fornecedorDAO;

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
    }

    @Override
    protected void onStart() {
        super.onStart();
        fornecedorAdapter = new FornecedorAdapter(this, fornecedorDAO.getFornecedor());
        recyclerView.setAdapter(fornecedorAdapter);
    }

    public void addFornecedorOnClick(View view) {
        Intent intent = new Intent(this, FornecedorDetails.class);
        startActivity(intent);
    }
}
