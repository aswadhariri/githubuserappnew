package com.example.githubuserapps
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.githubuserapp.User

class DetailGithubUser : AppCompatActivity(), View.OnClickListener {
    companion object {
        const val EXTRA_USER = "extra_user"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_github_user)

        val btnBack: Button = findViewById(R.id.btn_back)
        btnBack.setOnClickListener(this)

        val tvDetailName: TextView = findViewById(R.id.tv_detail_name)
        val tvDetailUsername: TextView = findViewById(R.id.tv_detail_username)
        val tvDetailLocation: TextView = findViewById(R.id.tv_detail_location)
        val tvDetailRepository: TextView = findViewById(R.id.tv_detail_repository)
        val tvDetailCompany: TextView = findViewById(R.id.tv_detail_company)
        val tvDetailFollowers: TextView = findViewById(R.id.tv_detail_followers)
        val tvDetailFollowing: TextView = findViewById(R.id.tv_detail_following)
        val imgPhotoUser: ImageView = findViewById(R.id.img_photo)

        val user = intent.getParcelableExtra<User>(EXTRA_USER) as User
        tvDetailUsername.text= user.username
        tvDetailName.text = user.name
        tvDetailLocation.text = user.location
        tvDetailRepository.text = user.repository
        tvDetailCompany.text = user.company
        tvDetailFollowers.text = user.item_follower
        tvDetailFollowing.text =user.item_following
        user.avatar?.let { imgPhotoUser.setImageResource(it) }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_back -> {
                val moveIntent = Intent(this@DetailGithubUser, MainActivity::class.java)
                startActivity(moveIntent)
            }
        }
    }
}