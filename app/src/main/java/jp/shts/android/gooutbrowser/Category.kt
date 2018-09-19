package jp.shts.android.gooutbrowser

enum class Category(private val row: String) {
    FASHION("fashion"),
    GEAR("gear"),
    FES_MUSIC("fes_music"),
    CAR("car"),
    LIVIN("livin"),
    ACTIVITY("activity"),
    TECH("tech"),
    INFO("info")
    ;

    companion object {
        private const val DOMAIN = "http://web.goout.jp"
    }

    fun url() = "$DOMAIN/${this.row}/"
}