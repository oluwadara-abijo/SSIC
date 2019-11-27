package ng.com.ssic.course

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item_course.view.*
import ng.com.ssic.R
import ng.com.ssic.model.Course

class CoursesListAdapter(
    private var courses: List<Course>, val context: Context
) :
    RecyclerView.Adapter<CoursesListAdapter.CourseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_item_course, parent, false)
        return CourseViewHolder(view)

    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val currentCourse = courses[position]
        holder.bind(currentCourse)

    }

    inner class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(course: Course) {
            itemView.tv_course_name.text = course.name
            itemView.tv_course_code.text = course.code
            itemView.tv_course_units.text =
                context.resources.getString(R.string.course_units, course.unit)
        }
    }

    internal fun setCourses(courses: List<Course>) {
        this.courses = courses
        notifyDataSetChanged()
    }

    override fun getItemCount() = courses.size
}