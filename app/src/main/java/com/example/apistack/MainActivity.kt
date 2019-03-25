package com.example.apistack

import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpRecyclerView()



        btnConsultar.setOnClickListener {
            if(inputPergunta.text.toString().isBlank()){
                inputPergunta.error = "Favor informar qual a pergunta"
            }
            getStackResponse(inputPergunta.text.toString())
        }



    }
    private fun setUpRecyclerView(){
        recyclerView.adapter = StackAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(MarginItemDecoration(8))

    }

    class MarginItemDecoration(private val spaceHeight: Int) : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(outRect: Rect, view: View,
                                    parent: RecyclerView, state: RecyclerView.State) {
            with(outRect) {
                if (parent.getChildAdapterPosition(view) == 0) {
                    top = spaceHeight
                }
                left =  spaceHeight
                right = spaceHeight
                bottom = spaceHeight
            }
        }
    }
    private fun getStackResponse(pergunta:String){
        val countriesAPI = RetrofitProvider.stackApi

        val call = countriesAPI.getQuestions("desc","activity",pergunta,"stackoverflow")
        Log.d("Http Retroift",call.request().toString())
        call.enqueue(object : Callback<StackQuestion> {
            override fun onFailure(call: Call<StackQuestion>, t: Throwable) {
                Toast.makeText(this@MainActivity,
                    "FALHOU A REQUISICAO", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<StackQuestion>,
                                    response: Response<StackQuestion>
            ) {
                if (response.code() == 200){
                   var stack = response.body()!!
                    val adapter = recyclerView.adapter
                    if (adapter is StackAdapter) {
                        stack.items.let {
                            if (it != null) {
                                adapter.setNewList(it)
                            }
                        }
                    }

                }
            }

        })


    }
}
