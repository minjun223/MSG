package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import org.json.JSONObject

class UserInfoActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_USER_DATA = "user_data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)

        val userDataJsonString = intent.getStringExtra(EXTRA_USER_DATA)
        val userData = JSONObject(userDataJsonString)

        val avatarUrl = userData.getString("avatar_url")
        val name = userData.getString("name")
        val username = userData.getString("login")
        val bio = userData.getString("bio")
        val followersCount = userData.getInt("followers")
        val followingCount = userData.getInt("following")

        val avatarImageView = findViewById<ImageView>(R.id.avatar_image_view)
        val nameTextView = findViewById<TextView>(R.id.name_text_view)
        val usernameTextView = findViewById<TextView>(R.id.username_text_view)
        val bioTextView = findViewById<TextView>(R.id.bio_text_view)
        val followersTextView = findViewById<TextView>(R.id.followers_text_view)
        val followingTextView = findViewById<TextView>(R.id.following_text_view)

        Glide.with(this).load(avatarUrl).into(avatarImageView)
        nameTextView.text = name
        usernameTextView.text = username
        bioTextView.text = bio
        followersTextView.text = followersCount.toString()
        followingTextView.text = followingCount.toString()
    }
}