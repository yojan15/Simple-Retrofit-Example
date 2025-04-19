package com.example.jsonexample.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.jsonexample.repository.Repository
import com.google.gson.JsonElement
import kotlinx.coroutines.launch

class PostViewModel(application: Application) : AndroidViewModel(application) {

    var obsResponse : MutableLiveData<List<JsonElement>> = MutableLiveData()
    private val repository = Repository()

    var id : Int = -1
        set(value) {
            field = value
        }

    var title : String = ""
        set(value) {
            field = value
        }

    var body : String = ""
        set(value) {
            field = value
        }

    fun fetchData() {
        viewModelScope.launch {
            try {
                val result = repository.getPosts()
                result.onSuccess { jsonResponse ->
                    obsResponse.value = jsonResponse

                    jsonResponse.firstOrNull()?.asJsonObject?.apply {
                        id = this["id"]?.asInt?: -1
                        title = this["title"]?.asString?: ""
                        body = this["body"]?.asString?: ""
                    }
                }
            } catch (exception : Exception) {
                Log.e("ViewModel","${exception.message}")
            }
        }
    }
}