package br.com.siecola.androidproject02.product

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.siecola.androidproject02.network.Product
import br.com.siecola.androidproject02.network.SalesApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

private const val TAG = "ProductListViewModel"

class ProductListViewModel : ViewModel() {
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>>
        get() = _products

    init {
        getProducts()
    }

    fun refreshProducts() {
        _products.value = null
        getProducts()
    }

    private fun getProducts() {
        Log.i(TAG, "Preparing to request products list")

        coroutineScope.launch {
            var getProductsDeferred = SalesApi.retrofitService.getProducts()
            try {
                Log.i(TAG, "Loading products")
                var productsList = getProductsDeferred.await()
                _products.value = productsList;

                Log.i(TAG, "Number of products ${productsList.size}")
            } catch (e: Exception) {
                Log.i(TAG, "Error: ${e.message}")
            }
        }

        Log.i(TAG, "Products list requested")
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}