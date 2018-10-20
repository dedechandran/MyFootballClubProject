package com.example.dedec.myfootballclubproject

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private var clubItems: ArrayList<Club> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainActivityUI().setContentView(this)
        initData()
        recyclerView = find(R.id.rv_club)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ClubAdapter(this, clubItems) {
            startActivity(
                intentFor<DetailActivity>(
                    DetailActivity.EXTRA_CLUB to it
                )
            )
        }

    }

    class MainActivityUI : AnkoComponent<MainActivity> {
        override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
            verticalLayout {
                recyclerView {
                    id = R.id.rv_club
                }.lparams(width = matchParent, height = matchParent)
            }
        }
    }

    private fun initData() {
        val clubName = resources.getStringArray(R.array.club_name)
        val clubImage = resources.obtainTypedArray(R.array.club_image)
        val clubDesc = resources.getStringArray(R.array.club_desc)

        for (i in clubName.indices) {
            clubItems.add(Club(clubName[i], clubImage.getResourceId(i, 0), clubDesc[i]))
        }

        clubImage.recycle()

    }
}
