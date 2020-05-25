package ccn.zone.sozdik


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.forfavitems.view.*
import ccn.zone.sozdik.entity.Words

class NewAdapter (val context: Context,
                  val clickListener: (position: Int) -> Unit):
    RecyclerView.Adapter<NewAdapter.ViewHolder>()
{
    private val items =  mutableListOf<Words>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(
                context
            ).inflate(R.layout.forfavitems, parent, false))
    }
    override fun getItemCount(): Int {
        return items.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.contenFav?.text = items[position].toGether()
        holder.bind(
            items[position],
            clickListener
        )
    }

    fun delete (position: Int) {
        if (items.size > 0) {
            items.removeAt(position)
            notifyDataSetChanged()
        }
    }

    fun getItem(position: Int) = items[position]

    fun addItems(words: List<Words>) {
        items.clear()
        items.addAll(words)
        notifyDataSetChanged()
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view)
    {
        val contenFav = view.textViewFavContent
        val deletefromfav = itemView.imgViewRemove
        fun bind(
            Words: Words,
            clickListener: (position: Int) -> Unit
        )
        {deletefromfav.setOnClickListener{
                clickListener(adapterPosition)}
        }
    }
}