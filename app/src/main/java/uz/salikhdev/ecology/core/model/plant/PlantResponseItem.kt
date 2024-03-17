package uz.salikhdev.ecology.core.model.plant


import com.google.gson.annotations.SerializedName

data class PlantResponseItem(
    @SerializedName("id")
    val id: Int, // 1
    @SerializedName("image")
    val image: String, // /ecalogy/media/plant_images/Rosa_Red_Chateau01.jpg
    @SerializedName("title")
    val title: String // AtirGul
)