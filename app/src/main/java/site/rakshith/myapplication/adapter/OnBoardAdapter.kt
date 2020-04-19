package site.rakshith.myapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import site.rakshith.myapplication.R
import site.rakshith.myapplication.pojo.OnboardItem

class OnBoardAdapter(
    private val context :Context,
    private val items: ArrayList<OnboardItem>
)
    : RecyclerView.Adapter<OnBoardAdapter.OnBoardViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardViewHolder {
        return OnBoardViewHolder(LayoutInflater.from(context).inflate(R.layout.onboard_viewpager_layout, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: OnBoardViewHolder, position: Int) {
        var onboardItem :OnboardItem? = items.get(position)
        if (onboardItem != null) {
            holder.onboardImg?.setImageResource(onboardItem.image)
            holder.descriptionHeader?.setText(onboardItem.header)
            holder.descriptionBody?.setText(onboardItem.description)
        }


    }

    class OnBoardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var onboardImg : ImageView? = null
        var descriptionHeader : TextView? = null
        var descriptionBody : TextView? = null

        init {
            onboardImg = itemView.findViewById(R.id.onboardImg)
            descriptionHeader = itemView.findViewById(R.id.descriptionHeader)
            descriptionBody = itemView.findViewById(R.id.descriptionBody)

        }
    }
}