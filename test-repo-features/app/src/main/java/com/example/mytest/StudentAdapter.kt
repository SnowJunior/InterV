package com.example.mytest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mytest.databinding.StudentCardBinding

class StudentAdapter(val testDataList : MutableList<TestDataClass> = mutableListOf()) :
    RecyclerView.Adapter<StudentAdapter.ViewHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.student_card, parent, false)
        return ViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val curStud = testDataList[position]

        holder._name.text = curStud.name
        fun bind(dataClass: TestDataClass){

            holder.apply {
                this._name.text = dataClass.name
                this._age.text = dataClass.age
                this._iq.text = dataClass.iq
                this._gender.text = if (dataClass.gender) "Male" else "Female"
                this._status.text = if (dataClass.maritalStatus) "Married" else "Unmarried"
            }
        }
        bind(curStud)
    }

    override fun getItemCount() = testDataList.size

   inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
       val _name = itemView.findViewById<TextView>(R.id.stName)
       val _age = itemView.findViewById<TextView>(R.id.stAge)
       val _iq = itemView.findViewById<TextView>(R.id.stIq)
       val _gender = itemView.findViewById<TextView>(R.id.stGender)
       val _status = itemView.findViewById<TextView>(R.id.stStatus)
    }


}