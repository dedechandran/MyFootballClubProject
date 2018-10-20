package com.example.dedec.myfootballclubproject

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import org.jetbrains.anko.*

class DetailActivity : AppCompatActivity() {
    private lateinit var imgClub: ImageView
    private lateinit var descClub: TextView

    companion object {
        const val EXTRA_CLUB = "EXTRA_CLUB"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DetailActivityUI().setContentView(this)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val clubIntent = intent
        val club = clubIntent?.getParcelableExtra<Club>(EXTRA_CLUB)


        imgClub = find(R.id.img_club_detail)
        descClub = find(R.id.desc_club)

        supportActionBar?.title = club?.name
        Glide.with(this).load(club?.image).into(imgClub)
        descClub.text = club?.description
    }

    class DetailActivityUI : AnkoComponent<DetailActivity> {
        override fun createView(ui: AnkoContext<DetailActivity>) = with(ui) {
            verticalLayout {
                padding = dip(16)
                imageView {
                    id = R.id.img_club_detail
                }.lparams(width = dip(80), height = dip(80)) {
                    bottomMargin = dip(8)
                    gravity = Gravity.CENTER
                }
                textView {
                    id = R.id.desc_club
                    textAlignment = View.TEXT_ALIGNMENT_CENTER
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
