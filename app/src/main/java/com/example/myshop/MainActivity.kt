package com.example.myshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myshop.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Response

import retrofit2.Callback

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        getProducts()
    }

    fun getProducts() {
        val apiclient = ApiClient.buildClient(ApiInterface::class.java)
        val request = apiclient.getProducts()
        request.enqueue(object : Callback<ProductResponse> {
            override fun onResponse(
                call: Call<ProductResponse>,
                response: Response<ProductResponse>
            ) {
                if (response.isSuccessful) {

                    var products = response.body()?.product
                    var adapterHolder = ProductAdapter(products ?: emptyList())
                    binding.rvRecycler.layoutManager = LinearLayoutManager(this@MainActivity)
                    binding.rvRecycler.adapter = adapterHolder
                    Toast.makeText(
                        baseContext,
                        "fetched ${products?.size} products",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    Toast.makeText(baseContext, response.errorBody()?.string(), Toast.LENGTH_LONG)
                        .show()
                }
            }

            override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()
            }
        })
    }
}
