package edu.ifam.productlist.recycler;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import edu.ifam.productlist.FornecedorDetails;
import edu.ifam.productlist.R;
import edu.ifam.productlist.entity.Fornecedor;
import java.util.List;

public class FornecedorAdapter extends RecyclerView.Adapter<FornecedorAdapter.FornecedorViewHolder> {

    private Context context;
    private List<Fornecedor> fornecedores;

    public FornecedorAdapter(Context context, List<Fornecedor> fornecedores) {
        this.context = context;
        this.fornecedores = fornecedores;
    }

    @NonNull
    @Override
    public FornecedorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_fornecedor, parent, false);
        return new FornecedorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FornecedorViewHolder holder, int position) {
        Fornecedor fornecedor = fornecedores.get(position);
        holder.codigoTextView.setText(String.valueOf(fornecedor.getCodigo()));
        holder.nomeTextView.setText(fornecedor.getNome());
        holder.enderecoTextView.setText(fornecedor.getEndereco());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, FornecedorDetails.class);
            intent.putExtra("id", fornecedor.getId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return fornecedores.size();
    }

    public static class FornecedorViewHolder extends RecyclerView.ViewHolder {
        TextView codigoTextView;
        TextView nomeTextView;
        TextView enderecoTextView;

        public FornecedorViewHolder(View itemView) {
            super(itemView);
            codigoTextView = itemView.findViewById(R.id.textViewTelefone);
            nomeTextView = itemView.findViewById(R.id.textViewNome);
            enderecoTextView = itemView.findViewById(R.id.textViewEndereco);
        }
    }
}
