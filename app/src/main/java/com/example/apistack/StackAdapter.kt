package com.example.apistack

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import java.text.SimpleDateFormat
import kotlin.text.StringBuilder

class StackAdapter(var items: List<Items> = listOf())
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    enum class ItemType{
        DEFAULT, EMPTY
    }

    fun setNewList(list: List<Items>){
        items = list
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int) = if(items.isNotEmpty())
        ItemType.DEFAULT.ordinal
    else ItemType.EMPTY.ordinal

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if (viewType == ItemType.DEFAULT.ordinal){
            val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.stack_cardview, parent, false)
            return StackViewHolder(itemView)
        }

        return EmptyViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.empty_list, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is StackViewHolder){
            val item = items[position]
            holder.linkText.text = item.link
            if(item.is_answered) {
                holder.respondida.text = "Sim"
            }else{
                holder.respondida.text = "Não"
            }
            holder.titleText.text = item.title
            val sbd = StringBuilder()
            if(item.tags != null) {
                item.tags!!.forEach {
                    sbd.append(it).append(",")
                }
            }
            holder.tags.text = sbd.toString()

            val sdf = SimpleDateFormat("dd/MM/yyyy:HH:mm:SS")

            holder.dtRecuperada.text = sdf.format(item.creation_date)

        }
    }

    override fun getItemCount() = if(items.isNotEmpty()) items.size else 1


    class StackViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val titleText: TextView = itemView.findViewById(R.id.txtTitulo)
        val linkText: TextView = itemView.findViewById(R.id.txLinkRecuperado)
        val tags: TextView = itemView.findViewById(R.id.txtTagsRecuperadas)
        val respondida: TextView = itemView.findViewById(R.id.txtRespondidaRecuperado)
        val dtRecuperada: TextView = itemView.findViewById(R.id.txtDtRecuperada)
    }

    class EmptyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)


}