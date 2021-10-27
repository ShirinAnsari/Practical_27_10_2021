package com.example.shirinansaripracticalapp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shirinansaripracticalapp.R
import com.example.shirinansaripracticalapp.model.Question
import kotlinx.android.synthetic.main.row_question.view.*

class ResultAdapter(private var resultList: ArrayList<Question>) :
    RecyclerView.Adapter<ResultAdapter.MyViewHolder>() {

    class MyViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {

        val tvQueNo = view.tvQueNo!!
        val tvAnswer = view.tvAnswer!!
        val tvCorrect = view.tvCorrect!!
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.row_question, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.tag = position

        holder.tvQueNo.text = "Que " + resultList[position].queNo!!
        holder.tvAnswer.text = resultList[position].correctAns!!

        if (resultList[position].isCorrect!!) {
            holder.tvCorrect.text = holder.itemView.context.getString(R.string.str_right)
        } else {
            holder.tvCorrect.text = holder.itemView.context.getString(R.string.str_wrong)
        }
    }

    override fun getItemCount() = resultList.size
}
