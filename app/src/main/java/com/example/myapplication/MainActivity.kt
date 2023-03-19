package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class MainActivity : AppCompatActivity() {
    @SuppressLint("WrongViewCast", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val searchButton = findViewById<Button>(R.id.search_button)
        val usernameEditText = findViewById<EditText>(R.id.username_edit_text)
        usernameEditText.setText("")

        searchButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            GlobalScope.launch(Dispatchers.IO) {
                val userData = fetchUserData(username)
                withContext(Dispatchers.Main) {
                    if (userData != null) {
                        val intent = Intent(this@MainActivity, UserInfoActivity::class.java)
                        intent.putExtra(UserInfoActivity.EXTRA_USER_DATA, userData.toString())
                        startActivity(intent)
                    }
                }
            }
        }
    }

    private fun fetchUserData(username: String): JSONObject? {
        val url = URL("https://api.github.com/users/$username")
        val connection = url.openConnection() as HttpsURLConnection
        connection.requestMethod = "GET"
        connection.setRequestProperty("Accept", "application/json")

        return try {
            val inputStream = connection.inputStream
            val jsonString = inputStream.bufferedReader().use { it.readText() }
            JSONObject(jsonString)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        } finally {
            connection.disconnect()
        }
    }
}