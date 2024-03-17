package uz.salikhdev.ecology.core.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import uz.salikhdev.ecology.core.model.plant.PlantResponseItem
import uz.salikhdev.ecology.databinding.ItemAnimalBinding

class PlantAdapter : RecyclerView.Adapter<PlantAdapter.ViewHolder>() {


    private val data = ArrayList<PlantResponseItem>()
    var onClickItem: ((id: Int) -> Unit)? = null

    fun setData(data: ArrayList<PlantResponseItem>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemAnimalBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindData(data: PlantResponseItem) {

            binding.animalImage.load("https://ecology.salikhdev.uz${data.image}")
            binding.animalName.text = data.title

            itemView.setOnClickListener {
                onClickItem?.invoke(data.id)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemAnimalBinding.inflate(
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