package uz.salikhdev.ecology.core.model.animal


import com.google.gson.annotations.SerializedName

data class AnimalResponseItem(
    @SerializedName("id")
    val id: Int, // 1
    @SerializedName("image")
    val image: String, // /ecalogy/media/animal_images/Cat_on_a_Book.jpg
    @SerializedName("name")
    val name: String // Mushuk
)