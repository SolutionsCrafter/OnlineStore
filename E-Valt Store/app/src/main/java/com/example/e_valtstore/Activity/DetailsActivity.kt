package com.example.e_valtstore.Activity

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.e_valtstore.Adapter.DetaliAdapter
import com.example.e_valtstore.DataClass.Items
import com.example.e_valtstore.R
import me.relex.circleindicator.CircleIndicator2

class DetailsActivity : AppCompatActivity() {

    private val itemData: Items = Items()
    private lateinit var rvDetailBanner: RecyclerView
    private lateinit var tvDescription: TextView
    private lateinit var indicator: CircleIndicator2
    private lateinit var DetailBannerProgressIndicator: ProgressBar

    private lateinit var img1 : ImageView
    private lateinit var img2: ImageView
    private lateinit var img3: ImageView
    private lateinit var img4: ImageView
    private lateinit var img5: ImageView

    private lateinit var tv1:TextView
    private lateinit var tv2:TextView
    private lateinit var tv3:TextView
    private lateinit var tv4:TextView
    private lateinit var tv5:TextView
    private lateinit var tv6:TextView

    private lateinit var tvDetailTitle: TextView
    private lateinit var tvDetailPrice: TextView
    private lateinit var imgDetailRating: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_details)

        tvDescription = findViewById(R.id.tvDiscription)
        rvDetailBanner = findViewById(R.id.rvDetailBanner)
        indicator = findViewById(R.id.DetailBannerIndicator)
        DetailBannerProgressIndicator = findViewById(R.id.DetailBannerProgressIndicator)

        // Set up back button
        findViewById<ImageView>(R.id.btnBack).setOnClickListener {
            finish()
        }

        // Set up favorite button
        findViewById<ImageView>(R.id.btnFav).setOnClickListener {
            // Handle favorite button click
        }

        // Set up buy button
        findViewById<Button>(R.id.btnBuy).setOnClickListener {
            // Handle buy button click
        }

        // Set up small images
        img1 = findViewById(R.id.img1)
        img2 = findViewById(R.id.img2)
        img3 = findViewById(R.id.img3)
        img4 = findViewById(R.id.img4)
        img5 = findViewById(R.id.img5)

        tv1 = findViewById(R.id.tv1)
        tv2 = findViewById(R.id.tv2)
        tv3 = findViewById(R.id.tv3)
        tv4 = findViewById(R.id.tv4)
        tv5 = findViewById(R.id.tv5)
        tv6 = findViewById(R.id.tv6)

        tvDetailTitle = findViewById(R.id.tvDetailTitle)
        tvDetailPrice = findViewById(R.id.tvDetailPrice)
        imgDetailRating = findViewById(R.id.imgDetailRating)

        // Set up layout manager
        rvDetailBanner.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        // Get item from intent
        val item = intent.getSerializableExtra("item") as? Items
        item?.let {
            // Set adapter first
            val adapter = DetaliAdapter(it.picUrl.drop(1))
            rvDetailBanner.adapter = adapter
            DetailBannerProgressIndicator.visibility = View.GONE

            // Attach indicator after setting adapter
            indicator.attachToRecyclerView(rvDetailBanner, LinearSnapHelper())
            adapter.registerAdapterDataObserver(indicator.adapterDataObserver)

            // Set description
            tvDescription.text = it.description

            // Set small images
            Glide.with(this)
                .load(it.picUrl[1])
                .error(R.drawable.default_shoe)
                .into(img1)

            Glide.with(this)
                .load(it.picUrl[2])
                .into(img2)

            Glide.with(this)
                .load(it.picUrl[3])
                .into(img3)

            Glide.with(this)
                .load(it.picUrl[4])
                .into(img4)

            Glide.with(this)
                .load(it.picUrl[5])
                .into(img5)

            tv1.text = it.size[0]
            tv2.text = it.size[1]
            tv3.text = it.size[2]
            tv4.text = it.size[3]
            tv5.text = it.size[4]
            tv6.text = it.size[5]

            tvDetailTitle.text = it.title

            tvDetailPrice.text = "$${it.price}"

            imgDetailRating.text = it.rating.toString()

        }

        // Apply window insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

}