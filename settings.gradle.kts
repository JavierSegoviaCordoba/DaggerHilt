include(":data")
include(":_di").also { project(":_di").name = "di" }.also { include(":di") }
include(":domain")
include(
    ":presentation:app",
    ":presentation:models",
    ":presentation:navigation",
    ":presentation:features:champions",
    ":presentation:features:championDetail"
)
