package br.com.siecola.androidproject02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import br.com.siecola.androidproject02.product.ProductListViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val applicationContext = MainApplication.getApplicationContext()
        Log.i("ProductListViewModel", "MainActivity:onCreate")
        val viewModel = ViewModelProviders.of(this).get(ProductListViewModel::class.java)
    }
}