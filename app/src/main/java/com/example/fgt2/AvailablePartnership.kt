package com.example.fgt2

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_data.*

class AvailablePartnership : AppCompatActivity() {

    internal var dbHelper = DatabaseHelper(this)
//   create a tables inside the database

    @SuppressLint("WrongConstant", "Recycle")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_available_partnership)

//        create an array that will inflate the layout
        val users: ArrayList<UserModel> = ArrayList()
//        val cursor = db.rawQuery("SELECT * FROM users", null)
        val cursor = dbHelper.allData
//            check if there are any records from the database
        if (cursor.count == 0) {
            show_message("No records ", "Sorry no records were found !!", this)
        } else {
            refresh()
        }
    }

    fun refresh(){
        val users: ArrayList<UserModel> = ArrayList()
//        val cursor = db.rawQuery("SELECT * FROM users", null)
        val cursor = dbHelper.allData
//            check if there are any records from the database
        if (cursor.count == 0) {
            show_message("No records ", "Sorry no records were found !!", this)
        } else {
            while (cursor.moveToNext()) {
                users.add(
                    UserModel(
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4)
                    )
                )

            }
            userlist.adapter = CustomAdapter(this, users)
        }
    }

}
