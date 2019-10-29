package fr.epita.android.hellogames

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fr.epita.android.ghibliquiz.Film
import fr.epita.android.ghibliquiz.R

class FilmAdapter(
    val context: Context,
    val data: List<Film>,
    val onItemClickListener: View.OnClickListener
) :
    RecyclerView.Adapter<FilmAdapter.ViewHolder>() {

    // the new RecyclerAdapter enforces the use of
    // the ViewHolder class performance pattern
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val charName: TextView = itemView.findViewById(R.id.list_item_film_name)
        val charAge: TextView = itemView.findViewById(R.id.list_item_film_age)
        val charPreview: ImageView = itemView.findViewById(R.id.list_item_film_preview)
    }
    override fun getItemCount(): Int {
        return data.size
    }
    // called when a new viewholder is required to display a row
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : ViewHolder {
        // create the row from a layout inflater
        val rowView = LayoutInflater
            .from(context)
            .inflate(R.layout.list_item_film, parent, false)
        // create a ViewHolder using this rowview
        rowView.setOnClickListener(onItemClickListener)
        // return this ViewHolder. The system will make sure view holders
        // are used and recycled
        return ViewHolder(rowView)
    }
    // called when a row is about to be displayed

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        // retrieve the item at the specified position
        val currentItem = data[position]
        // put the data
        holder.charName.text = currentItem.title
        holder.charAge.text = currentItem.release_date
        //GET IMAGE
        /*Glide.with(context)
            .load()
            .into(holder.charPreview)
        holder.itemView.tag = position*/
    }


}