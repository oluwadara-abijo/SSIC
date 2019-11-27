package ng.com.ssic.faculty

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item_faculty.view.*
import ng.com.ssic.R
import ng.com.ssic.model.Faculty

class FacultiesListAdapter(private var faculties: List<Faculty>, private val clickListener: ItemClickListener) :
    RecyclerView.Adapter<FacultiesListAdapter.FacultyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FacultyViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_item_faculty, parent, false)
        return FacultyViewHolder(view)

    }

    override fun onBindViewHolder(holder: FacultyViewHolder, position: Int) {
        val currentFaculty = faculties[position]
        holder.bind(currentFaculty)

    }

    //Class to handle item clicks
    interface ItemClickListener{
        fun onItemClick(faculty: Faculty)
    }

    inner class FacultyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        override fun onClick(v: View?) {
            val pos = adapterPosition
            val facultyClicked = faculties[pos]
            clickListener.onItemClick(facultyClicked )
        }

        fun bind(faculty: Faculty) {
            itemView.tv_faculty_name.text = faculty.name
            itemView.setOnClickListener(this)
        }
    }

    internal fun setFaculties(faculties: List<Faculty>) {
        this.faculties = faculties
        notifyDataSetChanged()
    }

    override fun getItemCount() = faculties.size
}