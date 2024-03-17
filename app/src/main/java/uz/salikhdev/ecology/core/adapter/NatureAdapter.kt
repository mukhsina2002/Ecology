package uz.salikhdev.ecology.core.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.salikhdev.ecology.core.model.nature.NatureResponseItem
import uz.salikhdev.ecology.databinding.ItemNatureBinding

class NatureAdapter : RecyclerView.Adapter<NatureAdapter.ViewHolder>() {


    private val data = ArrayList<NatureResponseItem>()
    var onClickItem: ((pdf: String) -> Unit)? = null

    fun setData(data: ArrayList<NatureResponseItem>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemNatureBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindData(data: NatureResponseItem) {

            binding.title.text = data.title
            binding.desc.text = data.about

            itemView.setOnClickListener {
                onClickItem?.invoke(data.pdfFile)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemNatureBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(data[position])
    }
}