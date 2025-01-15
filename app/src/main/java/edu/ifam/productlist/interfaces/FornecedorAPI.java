package edu.ifam.productlist.interfaces;

import java.util.List;

import edu.ifam.productlist.dto.FornecedorDTO;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface FornecedorAPI {
    @GET("/fornecedor")
    Call<List<FornecedorDTO>> getFornecedor();

    @GET("/fornecedor/{id}")
    Call<FornecedorDTO> getFornecedor(@Path("id") Long id);

    @POST("/fornecedor")
    Call<FornecedorDTO> setFornecedor(@Body FornecedorDTO fornecedorDTO);

    @PUT("/fornecedor/{id}")
    Call<FornecedorDTO> updateFornecedor(@Path("id") Long id, @Body FornecedorDTO fornecedorDTO);

    @DELETE("/fornecedor/{id}")
    Call<FornecedorDTO> deleteFornecedor(@Path("id") Long id);
}
