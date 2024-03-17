package uz.salikhdev.ecology.core.model.animal


import com.google.gson.annotations.SerializedName

data class AnimalDetailResponse(
    @SerializedName("background_image")
    val backgroundImage: String, // /ecalogy/media/animal_backgrounds/Capture11-680x415.jpg
    @SerializedName("description")
    val description: String, // Mushuklar yoki uy mushuklari (Felis Silvestris Catus) — mushuksimonlar oilasiga mansub yirtqich sut emizuvchilar urugʻi. Oldin uy mushugini alohida tur sifatida ham qarashgan. Boʻyi dumgʻaza qismida yagʻriniga nisbatan baland. Tirnoqlari toʻliq tortiladi. Yevrosiyo, Afrika, Shim. va Jan. Amerikada tarqalgan; 30 turi, jumladan, Oʻzbekistonda 6 turi (silovsin, qoraquloq, toʻqay mushugi, manul, qum mushugi, yovvoyi mushuk) bor. Kuyikish davrida erkaklari baland ovoz bilan miyovlaydi va bir-biri bilan urishadi. Yiliga 2 marta 1—7 tadan bolalaydi. Yangi tugʻilgan bolasining koʻzi yumuqboʻladi. Mushuk bolasini 2-2,5 oygacha sut bilan boqadi. Keyin turli oʻljalar keltirib bera boshlaydi. M.ning koʻpchilik turlari mayda sut emizuvchilar (kemiruvchilar) va qushlar bilan, Sumatra M. i oʻsimliklar mevasi, baliqchi M., asosan, baliq bilan oziqlanadi
    @SerializedName("id")
    val id: Int, // 1
    @SerializedName("image")
    val image: String, // /ecalogy/media/animal_images/Cat_on_a_Book.jpg
    @SerializedName("link")
    val link: String, // https://uz.wikipedia.org/wiki/Mushuk
    @SerializedName("name")
    val name: String, // Mushuk
    @SerializedName("type")
    val type: String // Sut Emzuvchi
)