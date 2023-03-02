package id.eureka.hunicompose.core.util

import id.eureka.hunicompose.R
import id.eureka.hunicompose.detailhuni.data.model.Facilities
import id.eureka.hunicompose.detailhuni.data.model.Review
import id.eureka.hunicompose.home.presentation.HuniRentPeriod
import id.eureka.hunicompose.home.presentation.model.Huni
import java.math.RoundingMode
import java.text.DecimalFormat
import java.util.*
import kotlin.random.Random

object Utils {

    fun numberFormatter(number: Double): String {
        val formatted = DecimalFormat("#,###")
        return formatted.format(number)
    }

    fun dummyHuniItem(): List<Huni> {

        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.CEILING

        val dummyFacilities = dummyFacilities()
        val dummyImages = dummyImages()
        val randomIDs = mutableListOf<Int>()

        val huniItems = mutableListOf<Huni>()

        var randomId: Int

        for (i in 0..Random.nextInt(3, 14)) {

            randomId = Random.nextInt(1, 99999)

            while (randomId in randomIDs) {
                randomId = Random.nextInt(1, 99999)
            }

            randomIDs.add(randomId)

            huniItems.add(
                Huni(
                    randomId,
                    name = randomPlaceName(),
                    address = randomAddress(),
                    description = randomShortDescription(),
                    images = dummyImages.shuffled().take(Random.nextInt(3, 6)),
                    rate = df.format(Random.nextDouble(1.0, 5.0)).toDouble(),
                    price = Random.nextDouble(1500000.0, 10000000000.0),
                    rentPeriod = randomPeriod(),
                    facilities = dummyFacilities.shuffled()
                        .take(Random.nextInt(3, dummyFacilities.size)),
                    ownerName = "${randomFirstName()} ${randomLastName()}",
                    reviews = dummyReviews()
                )
            )
        }

        return huniItems
    }

    fun dummyFacilities(): List<Facilities> {
        return listOf(
            Facilities(type = "Gym", count = 1),
            Facilities(type = "Bedrooms", count = 5),
            Facilities(type = "Free Wifi", count = 1),
            Facilities(type = "Parking Spots", count = 1),
            Facilities(type = "Security", count = 1),
            Facilities(type = "Smoke Free", count = 1),
            Facilities(type = "Swimming Pool", count = 1),
        )
    }

    private fun dummyReviews(): List<Review> {
        val reviews = mutableListOf<Review>()
        var review: Review

        for (i in 0..Random.nextInt(10, 30)) {
            review = Review(
                name = UUID.randomUUID().toString(),
                rate = Random.nextInt(1, 5),
                description = UUID.randomUUID().toString(),
            )

            reviews.add(review)
        }

        return reviews
    }

    private fun randomShortDescription() = listOf(
        "Boasting a modern design, spacious living areas, and high-end finishes, this house is the perfect combination of comfort and luxury. Featuring a beautifully appointed kitchen, multiple bedrooms, and plenty of space for entertaining both indoors and out, this home is perfect for families, couples, and anyone who appreciates the finer things in life",
        "This charming house on sale is perfect for those seeking a cozy and comfortable home. Featuring a classic design and plenty of natural light, this property boasts a warm and inviting atmosphere throughout. With spacious living areas, multiple bedrooms, and a beautifully landscaped backyard, this house is perfect for entertaining guests or simply relaxing with family. And with its convenient location, you'll have easy access to all the amenities and attractions that make this neighborhood so desirable",
        "Stylish, two bedroom absolute beachfront family suite offering direct surf and ocean views. Our suite has all of the creature comforts you need while on holidays including an automatic coffee machine, wifi, air con and bluetooth speaker. If you love waking up to the sounds of the waves with a freshly brewed coffee in hand, getting lost in the views and having an aqua blue beach and world class surf break right at your door step, then a stay at The Surf Bingin Beach is a must!",
        "This is an off-grid, self-sufficient, self contained, Earthship studio apartment in the semi-rural Adelaide Hills, 45min drive from the CBD. Guests will experience a unique stay with a tiny eco-footprint. Close to Adelaide Hills wineries, golf course, bush-walks and other tourist attractions. The Freney family live next door in a passive solar strawbale home - close by if you need advice on sailing the \"ship\". A place where you can experience the future of sustainable housing!",
        "This beautiful stone villa is set in an elevated position in the quiet Argaka countryside and commands far reaching views of the surrounding fields and orchards, while attractive gardens filled with a variety of plants and flowers surround the property.  The private pool nestles within a paved sun terrace and lawn and provides the perfect place to relax in the sunshine. Argaka offers a small selection of tavernas scattered along this rustic coastline, with the nearby village of Polis offering further eateries, supermarkets and shops. All bedroom and bathroom linen is included. Bath towels are also provided. This property comes with Free Wi-Fi, Free Air Conditioning, Free Cots, Free Highchairs.",
        "Leafy Greens was built as a retreat center for our family and friends. It is where people would visit to refreshing their souls and mind. We work so hard to make this place to be one of the place that we can live in harmony with nature. That is why the cob houses are the right choice for us. Not only the buildings are eco-friendly but also the garden is organic. Visit here you will be able to take a deep breath and enjoy the fresh air with organic environment. It is a perfect place to getaway!!",
        "Tucked in a coconut grove just 175m from a quiet beach, pet-friendly Hidden Well is a beautifully secluded cottage – and travellers who value connecting with nature and the soul of a place will enjoy spending time in an authentic part of Bali that's off the beaten track. The colourful home has lush gardens and a gorgeous outdoor bathroom, and is well-equipped for a self-catering stay. It is a 10-minute drive from Medewi, and a 5-minute walk from a secret surf spot.",
        "Sheoaks is a 4 bedroom rammed earth home that configures to accommodate 2 guests in a self-contained studio-bedroom, or opens up to accommodate larger family groups. A circular elevated day bed is just made for relaxing with your loved ones, a wood fire to keep you cosy through winter and a screened outdoor patio for insect free dining through summer.",
        "Bussells Bushland Cottages is a 20 hectare \"Land for Wildlife\" property. Our 8 rammed-earth cottages are set apart in order to preserve privacy. Walk trails criss-cross the bush block which has wonderful stands of old-growth trees, as well as being home to a wide variety of bird life and mobs of kangaroos. The quoted room rate for 1 or 2 persons is based on the use of 1 bedroom. If, for any reason, guests require to use the 2nd bedroom, an extra bed charge will be levied payable before arrival."
    ).random()

    private fun randomPlaceName() = listOf(
        "Veluvana Bali - Owl Bamboo House",
        "The Nude House",
        "The Young Villas Canggu",
        "Camaya Bali Butterfly - Magical Bamboo House",
        "Koko-Beach-Villas, Lovina * Villa Satu",
        "Tropical Garden Lodge Lembongan",
        "Casa Lembang 2",
        "Iris Garden Villa",
        "Griya Cempaka Raya",
        "Askara Kozystay"
    ).random()

    private fun randomAddress() = listOf(
        "Jalan Taman Sari No. 12, Kelurahan Kebon Jeruk, Kecamatan Palmerah, Jakarta Barat, DKI Jakarta, Indonesia.",
        "Jl. Raya Bogor No. 28, Jakarta, Indonesia",
        "Jalan Raya Ubud No. 25, Ubud, Gianyar, Bali 80571, Indonesia.",
        "Jalan Raya Kuta No. 88, Banjar Anyar, Kuta, Kabupaten Badung, Bali, Indonesia.",
        "Jalan Raya Ubud No. 45, Banjar Padang Tegal, Desa Ubud, Kecamatan Ubud, Kabupaten Gianyar, Bali, Indonesia.",
        "Jalan Raya Kedewatan No. 12, Banjar Kedewatan, Desa Kedewatan, Kecamatan Ubud, Kabupaten Gianyar, Bali, Indonesia.",
        "Jalan Raya Seminyak No. 99, Seminyak, Kuta, Badung Regency, Bali, Indonesia.",
        "Jalan Ir. H. Djuanda No. 129, Kelurahan Dago, Kecamatan Coblong, Kota Bandung, Jawa Barat, Indonesia.",
        "Jalan Cipaganti No. 77, Kelurahan Lebakgede, Kecamatan Coblong, Bandung, Jawa Barat, Indonesia.",
        "Jalan Pemuda No. 12, Kelurahan Kranggan, Kecamatan Semarang Tengah, Kota Semarang, Jawa Tengah, Indonesia.",
        "Jalan Sisingamangaraja No. 10, Kelurahan Kranggan, Kecamatan Ambarawa, Kabupaten Semarang, Jawa Tengah, Indonesia."
    ).random()

    private fun dummyImages() = listOf(
        R.drawable.property_1,
        R.drawable.property_2,
        R.drawable.property_3,
        R.drawable.property_4,
        R.drawable.property_5,
        R.drawable.property_6,
        R.drawable.property_7,
        R.drawable.property_8,
        R.drawable.property_9,
        R.drawable.property_10,
        R.drawable.property_11,
    )

    private fun randomFirstName() = listOf(
        "Hanan",
        "Ade",
        "Ammar",
        "Arif",
        "Bambang",
        "Hendra",
        "Ferdi",
        "Sulis",
        "Ayu",
        "Wati",
        "Ajeng",
        "Anne",
        "Nabila"
    ).random()

    private fun randomLastName() = listOf(
        "Pamungkas",
        "Maharani",
        "Sambo",
        "Febiola",
        "Wulan",
        "Afif",
        "Puspa",
        "Yudhoyono",
        "Restu",
        "Kristian",
        "Trafalgar",
        "Newgate"
    ).random()

    private fun randomPeriod(): String {

        return when (Random.nextInt(1, 4)) {
            1 -> HuniRentPeriod.Month.period
            2 -> HuniRentPeriod.Year.period
            3 -> HuniRentPeriod.SixMonths.period
            else -> HuniRentPeriod.Day.period
        }
    }

    fun stringToPeriod(periodString: String): HuniRentPeriod {
        return when (periodString) {
            "year" -> HuniRentPeriod.Year
            "six months" -> HuniRentPeriod.SixMonths
            "month" -> HuniRentPeriod.Month
            else -> HuniRentPeriod.Day
        }
    }
}