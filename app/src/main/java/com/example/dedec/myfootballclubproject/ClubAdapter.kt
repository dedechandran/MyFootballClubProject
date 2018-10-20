package com.example.dedec.myfootballclubproject

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import kotlinx.android.extensions.LayoutContainer
import org.jetbrains.anko.*


class ClubAdapter(
    private val context: Context,
    private val listItem: ArrayList<Club>,
    private val listener: (Club) -> Unit
) :
    RecyclerView.Adapter<ClubAdapter.ClubViewHolder>() {

    override fun onCreateViewHolder(viewgroup: ViewGroup, position: Int) =
        ClubViewHolder(ItemListUI().createView(AnkoContext.create(context, viewgroup)))

    override fun getItemCount() = listItem.size

    override fun onBindViewHolder(holder: ClubViewHolder, position: Int) {
        holder.bindItem(listItem[position], listener)
    }

    class ItemListUI : AnkoComponent<ViewGroup> {
        override fun createView(ui: AnkoContext<ViewGroup>) = with(ui) {
            linearLayout {
                orientation = LinearLayout.HORIZONTAL
                padding = dip(8)
                lparams(width = matchParent) {
                    bottomMargin = dip(8)
                }
                imageView {
                    id = R.id.img_club
                }.lparams(width = dip(50), height = dip(50)) {
                    rightMargin = dip(8)
                }
                textView {
                    id = R.id.name_club
                }.lparams(width = matchParent) {
                    gravity = Gravity.CENTER_VERTICAL
                }
            }
        }

    }

    class ClubViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        private val clubName = itemView.find<TextView>(R.id.name_club)
        private val clubImage = itemView.find<ImageView>(R.id.img_club)
        fun bindItem(items: Club, listener: (Club) -> Unit) {
            clubName.text = items.name
            Glide.with(containerView).load(items.image).into(clubImage)
            containerView.setOnClickListener { listener(items) }
        }
    }
}