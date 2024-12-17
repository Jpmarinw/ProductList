package edu.ifam.productlist.repository;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import edu.ifam.productlist.entity.Fornecedor;
import java.util.ArrayList;
import java.util.List;

public class FornecedorDAO {
    private SQLiteDatabase sqLiteDatabase;

    public FornecedorDAO(Context context){
        BDProdutos bdProdutos = new BDProdutos(context, "produto", null, 1);
        sqLiteDatabase = bdProdutos.getWritableDatabase();
    }

    @SuppressLint("Range")
    public List<Fornecedor> getFornecedor(){
        List<Fornecedor> fornecedores = new ArrayList<>();
        String sql = "SELECT * FROM fornecedor";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            do{
                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setId(cursor.getLong(cursor.getColumnIndex("id")));
                fornecedor.setCodigo(cursor.getInt(cursor.getColumnIndex("codigo")));
                fornecedor.setNome(cursor.getString(cursor.getColumnIndex("nome")));
                fornecedor.setTelefone(cursor.getString(cursor.getColumnIndex("telefone")));
                fornecedor.setEmail(cursor.getString(cursor.getColumnIndex("email")));
                fornecedor.setEndereco(cursor.getString(cursor.getColumnIndex("endereco")));
                fornecedores.add(fornecedor);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return fornecedores;
    }

    @SuppressLint("Range")
    public Fornecedor getFornecedor(long id){
        String sql = " SELECT * FROM fornecedor " + "WHERE id = ?";
        String[] selectionArgs = {Long.toString(id)};
        @SuppressLint("Recycle") Cursor cursor = sqLiteDatabase.rawQuery(sql, selectionArgs);
        Fornecedor fornecedor = new Fornecedor();
        if (cursor.moveToFirst()){
            do{
                fornecedor.setId(cursor.getLong(cursor.getColumnIndex("id")));
                fornecedor.setCodigo(cursor.getInt(cursor.getColumnIndex("codigo")));
                fornecedor.setNome(cursor.getString(cursor.getColumnIndex("nome")));
                fornecedor.setTelefone(cursor.getString(cursor.getColumnIndex("telefone")));
                fornecedor.setEmail(cursor.getString(cursor.getColumnIndex("email")));
                fornecedor.setEndereco(cursor.getString(cursor.getColumnIndex("endereco")));
            }while(cursor.moveToNext());
        }
        return fornecedor;
    }

    public void insert(Fornecedor fornecedor){
        ContentValues cv = new ContentValues();
        cv.put("codigo", fornecedor.getCodigo());
        cv.put("nome", fornecedor.getNome());
        cv.put("telefone", fornecedor.getTelefone());
        cv.put("email", fornecedor.getEmail());
        cv.put("endereco", fornecedor.getEndereco());
        sqLiteDatabase.insert("fornecedor", null, cv);
    }

    public void update(long id, Fornecedor fornecedor){
        ContentValues cv = new ContentValues();
        cv.put("codigo", fornecedor.getCodigo());
        cv.put("nome", fornecedor.getNome());
        cv.put("telefone", fornecedor.getTelefone());
        cv.put("email", fornecedor.getEmail());
        cv.put("endereco", fornecedor.getEndereco());
        sqLiteDatabase.update("fornecedor", cv, "id = ?", new String[]{String.valueOf(id)});
    }

    public void delete(long id){
        String sql = " DELETE FROM fornecedor " + "WHERE id = ?";
        String[] selectionArgs = {Long.toString(id)};
        sqLiteDatabase.execSQL(sql, selectionArgs);
    }
}
