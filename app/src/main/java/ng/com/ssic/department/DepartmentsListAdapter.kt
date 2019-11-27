package ng.com.ssic.department

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item_department.view.*
import ng.com.ssic.R
import ng.com.ssic.model.Department

class DepartmentsListAdapter(
    private var departments: List<Department>,
    private val clickListener: ItemClickListener
) :
    RecyclerView.Adapter<DepartmentsListAdapter.DepartmentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DepartmentViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_item_department, parent, false)
        return DepartmentViewHolder(view)

    }

    override fun onBindViewHolder(holder: DepartmentViewHolder, position: Int) {
        val currentDepartment = departments[position]
        holder.bind(currentDepartment)

    }

    //Class to handle item clicks
    interface ItemClickListener {
        fun onItemClick(Department: Department)
    }

    inner class DepartmentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        override fun onClick(v: View?) {
            val pos = adapterPosition
            val departmentClicked = departments[pos]
            clickListener.onItemClick(departmentClicked)
        }

        fun bind(department: Department) {
            itemView.tv_department_name.text = department.name
            itemView.setOnClickListener(this)
        }
    }

    internal fun setDepartments(departments: List<Department>) {
        this.departments = departments
        notifyDataSetChanged()
    }

    override fun getItemCount() = departments.size
}