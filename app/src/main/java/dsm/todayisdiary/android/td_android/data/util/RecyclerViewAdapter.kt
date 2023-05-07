package dsm.todayisdiary.android.td_android.data.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Glide.init
import dsm.todayisdiary.android.td_android.data.response.diary.Diary
import dsm.todayisdiary.android.td_android.databinding.ItemListBinding

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>() {
    var postList = mutableListOf<Diary>()

    interface OnItemClickListener {
        fun onItemClick(position: Int) {}
    }

    var itemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecyclerViewHolder(binding)
    }

    override fun getItemCount(): Int = postList.size

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val item = postList[position]
        holder.bind(item)

    }

    inner class RecyclerViewHolder(private val binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(diary: Diary) {
            Glide.with(binding.profile)
                .load(diary.writerProfile)
                .into(binding.profile)
            binding.title.text = diary.title
            binding.category.text = diary.category
            binding.time.text = diary.data
            binding.view.text = diary.view.toString()
            binding.comment.text = diary.commentCount.toString()
            Glide.with(binding.image)
                .load(diary.imageUrl)
                .into(binding.image)
        }

        init {
            itemView.setOnClickListener {
                itemClickListener?.onItemClick(adapterPosition)
            }
        }
    }

    fun replaceList(newList: MutableList<Diary>) {
        postList = newList.toMutableList()
        notifyDataSetChanged()
    }
}