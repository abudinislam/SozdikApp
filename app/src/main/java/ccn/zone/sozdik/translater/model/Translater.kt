package ccn.zone.sozdik.translater.model

data class Translater(
    val kz: String,
    val ru: String,
    val en: String
    )
{
    override fun toString(): String {
        return "$kz - $ru - $en \n"
    }
}