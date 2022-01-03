package com.example.practice

import Model.StockDTO
import android.content.ContentValues
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private lateinit var db:SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var stockListView = findViewById<ListView>(R.id.StockListView)
        val item = arrayListOf<String>("123","456")
        var arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,)
        stockListView.adapter = arrayAdapter
        db = DBHelper(this).writableDatabase
        db.delete("myTable",null,null)
        var idButton = findViewById<Button>(R.id.Login)
        httpSync()
        idButton.setOnClickListener {
            var intent = Intent(this, HomeView::class.java)
        startActivity(intent)
        }
      //  db.delete("myTable",null,null)

       // vc.put("StockNumber")
        //db.insert("myTable",)
    }


    fun httpSync() {
        val req = Request.Builder().url("https://www.twse.com.tw/exchangeReport/STOCK_DAY_ALL?response=open_dat").build()
        OkHttpClient().newCall(req).enqueue(object :Callback {
            override fun onResponse(call: Call, response: Response) {
                var json = response.body?.string()
                var a = response.body.toString()
                println(a)
              //  println(json)
                var abc = Gson().fromJson(json, StockDTO::class.java)
                println(abc.data)
                var vc = ContentValues()
                for (i in 0 until abc.data.size) {
                   // for (j in 0 until abc.data[i].size) {
                        vc.put("StockNumber", abc.data[i][0])
                        vc.put("StockName", abc.data[i][1])
                        vc.put("TradeVolume", abc.data[i][2])
                        vc.put("StockTotalPrice", abc.data[i][3])
                    vc.put("OpeningPrice", abc.data[i][4])
                    vc.put("HighestPrice", abc.data[i][5])
                    vc.put("LowestPrice", abc.data[i][6])
                    vc.put("ClosingPrice", abc.data[i][7])
                    vc.put("ChangePrice", abc.data[i][8])
                    vc.put("TradeCount",   abc.data[i][9])
                    //}
                    db.insert("myTable", null, vc)

                }
           //     db.insert("myTable", null, vc)
                //var a = Stock()

               // println(json)
            }

            override fun onFailure(call: Call, e: IOException) {
                println("fail")
            }
        }


        )
    }
}