object Projects {
    const val data = ":data"
    const val di = ":di"
    const val domain = ":domain"

    val presentation get() = Presentation

    object Presentation {
        const val app = ":presentation:app"
        const val models = ":presentation:models"
        const val navigation = ":presentation:navigation"

        val features get() = Features

        object Features {
            const val champions = ":presentation:features:champions"
            const val championDetail = ":presentation:features:championDetail"
        }
    }
}
