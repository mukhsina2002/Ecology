package uz.salikhdev.ecology.core.model.plant


import com.google.gson.annotations.SerializedName

data class PlantDetailResponse(
    @SerializedName("background_image")
    val backgroundImage: String, // /ecalogy/media/plant_backgrounds/920__95_4201820064.jpg
    @SerializedName("description")
    val description: String, // Atirgul – raʼnodoshlar turkumining xushmanzara oʻsimlik sifatida ekiladigan bir qancha turiga xalq tomonidan qoʻyilgan umumiy nom. Bu turkumning yovvoyi holda oʻsadigan turlari xalqda raʼno, naʼmatak, qirq ogʻayni va itburun nomlari bilan maʼlum. Osiyo, Yevropa, Amerika mamlakatlarida keng tarqalgan. Atirgulning Yer yuzida 250 – ZOYU turi, 25 mingdan ortiq navi bor. Oʻzbekistonda 19 turi, 600 dan ortiq navi oʻstiriladi. Bu navlar gulining katta-kichikligi, rangi, hidi, poyada oʻrnashishi, gullash davri, poyaning tikka yoki ilashib oʻsishi kabi xususiyatlari bilan farq qiladi; fasl boʻyi uzluksiz yoki takroriy gullab turadigan va poyasi ilashib oʻsadigan xillari koʻproq ekiladi. Atirgul asosan, payvand qilib, parxishlab va qalamchasini ekib koʻpaytiriladi. Payvand qilish yoʻli bilan koʻpaytirishda iyulda terilgan naʼmatak mevalaridan ajratib olingan urugʻlar nam joyda saqlanib, noyabr oxiri – dekabr boshlarida ochiq dalaga sepiladi. Urugʻ unib chiqqanidan keyin kelasi yili aprel – avg. davomida 4 – 5-marta chopiq qilinadi, 8 – 10-marta sugʻoriladi; avgust – sentabr oylarida Atirgul navi kurtaklari naʼmatakka payvand qilinadi. Kelasi yil erta bahorda payvandtag unga ulangan kurtak hammasi yolgon yuqorisidan kesib tashlanadi, payvandni bogʻlagan chipta boʻshatiladi. Yoz davomida bachki shoxchalar 1 – 2-marta yulib tashlanadi, payvandi gul 15 – 20 sm oʻsgach, uning boʻyiga choʻzilishiga va muddatidan oldin gullashiga yoʻl qoʻyilmaydi. Buning uchun payvandi gulning uchi kesilib, vaqt-vaqti bilan yon shoxlari qisqartib turiladi. Atirgulni maxsus oʻsishni faollashtiruvchi moddalar bilan ishlov berib qalamchalardan ham koʻpaytirish mumkin.
    @SerializedName("id")
    val id: Int, // 1
    @SerializedName("image")
    val image: String, // /ecalogy/media/plant_images/Rosa_Red_Chateau01.jpg
    @SerializedName("link")
    val link: String, // https://uz.wikipedia.org/wiki/Atirgul
    @SerializedName("title")
    val title: String, // AtirGul
    @SerializedName("type")
    val type: String, // gul
    @SerializedName("youtube_url")
    val youtubeUrl: String // https://youtu.be/HLWeCr9_XyA?si=4KObnrZbmy0sS-7m
)