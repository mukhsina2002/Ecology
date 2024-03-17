package uz.salikhdev.ecology.core.model.nature


import com.google.gson.annotations.SerializedName

data class NatureResponseItem(
    @SerializedName("about")
    val about: String, // asd
    @SerializedName("id")
    val id: Int, // 1
    @SerializedName("pdf_file")
    val pdfFile: String, //
    @SerializedName("title")
    val title: String // asd
)