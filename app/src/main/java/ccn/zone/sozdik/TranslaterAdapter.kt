package ccn.zone.sozdik

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item.view.*
import ccn.zone.sozdik.entity.Words

class TranslaterAdapter(
    private val avatarClickListener: (position: Int) -> Unit,
    private val favImgClickListener: (position: Int) -> Unit
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val storeList: MutableList<Words> = mutableListOf<Words>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return UserViewHolder(
            inflater,
            parent
        )
    }
    override fun getItemCount(): Int = storeList.size
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as UserViewHolder).bind(
            storeList[position],
            avatarClickListener,
            favImgClickListener)
    }
    fun addItems(words: MutableList<Words>) {
        storeList.addAll(words)
        notifyDataSetChanged()
    }
    fun delete(position: Int) {
        if(storeList.size > 0) {
            storeList.removeAt(position)
            notifyDataSetChanged()
        }
    }


    fun getItemByPosition(position: Int): Words = storeList[position]

    private class UserViewHolder(inflater: LayoutInflater, parent: ViewGroup):
        RecyclerView.ViewHolder(inflater.inflate(R.layout.item, parent, false)) {
        private val trashIcon = itemView.imgViewTrashCan
        private val items = itemView.textViewItem
        private val starIcon = itemView.imgViewStore
        fun bind(
            words: Words,
            avatarClickListener: (position: Int) -> Unit,
            favImgClickListener: (position: Int) -> Unit
        ) {
            items.text = words.toGether()
            trashIcon.setOnClickListener {
                avatarClickListener(adapterPosition)
            }
            starIcon.setOnClickListener {
                favImgClickListener(adapterPosition)
            }
        }
    }
}